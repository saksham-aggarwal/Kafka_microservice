package com.cwctravel.microservice.kafka.service;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Component;

import com.cwctravel.microservice.kafka.constants.KafkaConstants;
import com.cwctravel.microservice.kafka.models.BookingDetails;
import com.cwctravel.microservice.kafka.models.CarSearchDetails;
import com.cwctravel.microservice.kafka.models.RentalCarEventDetails;
import com.cwctravel.microservice.kafka.models.RentalCarInfo;
import com.cwctravel.microservice.kafka.models.UserDetails;
import com.cwctravel.microservice.kafka.properties.KafkaProperties;
import com.cwctravel.microservice.kafka.utilities.KafkaUtil;

/*
 * This class contains two subclasses based on the two scenarios that are implemented in the micro-service. 
 */

@Component
public class Streams {

	KafkaStreams streams;
	StreamsBuilder builder;

	/*
	 * RekeyEvents class is used to re-key all the messages that are stored in the RawDataTopic where most messages 
	 * are not associated with a key.
	 */
	@Component
	public class RekeyEvents {

		/*
		 * Associates all the messages specific to a session with a customized key. It filters the messages on the bases 
		 * of the SessionID and associated the messages to a new key which is made of the userID, SessionID and WorkflowID. 
		 * Then the Re-keyed messages are published to RekeyDataTopic in Kafka which can later be used for processing.
		 * Param: String sessionId indicating the sessionId which is used to filter the messages in Kafka
		 * 		  String userId used to generate a new key to associated the messages without a key
		 */
		public void rekeyAllEvents(String sessionId, String userId) {

			builder = new StreamsBuilder();

			KStream<String, RentalCarEventDetails> assignKeysToEvents = builder.stream(KafkaConstants.getRawDataTopic(), Consumed.with(Serdes.String(), KafkaConstants.getValueSerde()));

			KStream<String, RentalCarEventDetails> rekeyedEvents = assignKeysToEvents.filter((key, value) -> value.getUserDetails().getSID().equalsIgnoreCase(sessionId)) //
					.map((key, value) -> {
						KeyValue<String, RentalCarEventDetails> keyValue;
						keyValue = new KeyValue<>(userId + "_" + value.getUserDetails().getSID() + "_"
								+ value.getUserDetails().getWorkflowIdentifier(), value);
						return keyValue;
					});

			rekeyedEvents.to(KafkaConstants.getRekeyDataTopic(), Produced.with(Serdes.String(), KafkaConstants.getValueSerde()));

			executeStream();

		}

		/*
		 * Starts an instance of KafkaStreams which consumes data from the input Kafka topic and publishes streamed data to the 
		 * output Kafka topic. A delay of few seconds is used before closing the instance of KafkaStreams
		 */
		public void executeStream() {
			KafkaStreams streams;
			streams = new KafkaStreams(builder.build(), KafkaProperties.configStream());
			streams.start();
			KafkaUtil.delayExecution(KafkaConstants.getDelayTimeValue("RekeyEventsDelayTime"));
			streams.close();
		}
	}

	/*
	 * ProcessAllEvents class is used to process all the messages (or events) stored in the RekeyDataTopic in order to
	 * create the current state of an entity
	 */
	@Component
	public class ProcessAllEvents {

		/*
		 * Processes all the events stored in the input Kafka topic and creates a current state by aggregating all the events into one and
		 * this processed event is published to the output Kafka topic.
		 */
		public void processAllEvents(String userId) {

			builder = new StreamsBuilder();

			/*
			 * All the events in the RekeyDataTopic is consumed into KStream object using the String (key) deserializer and RentalCarEventDetails (event)
			 * deserializer
			 */
			KStream<String, RentalCarEventDetails> allEvents = builder.stream(KafkaConstants.getRekeyDataTopic(), Consumed.with(Serdes.String(), KafkaConstants.getValueSerde()));

			/*
			 * KTable object is populated by using the filter method on the KStream to get data associated to a specific userId. Then, the data
			 * is grouped by key in order to apply aggregation on a certain set of events. aggregate() method is applied on the grouped by data
			 * and then a switch case is used to populate the aggregate (RentalCarEventDetails) object to generate the current state of the entity.
			 * The event types are used to make separate cases in the switch and then populate the aggregate object with data from different events.
			 * Since the data was grouped by key so there is a possibility that a "BOOKED_RENTAL_CAR" event might exist for a set of events. On the
			 * basis of that possibility, two cases are creates -
			 * 1) FinalizeBooking - where the event type of the aggregate object is set to "FINALIZED_BOOKING" so that different processed events can
			 * 	  be published to separate topics.
			 * 2) CartItem - where the event type is set to "CART_ITEM" so that this aggregated object to published to CartDataTopic
			 */
			KTable<String, RentalCarEventDetails> groupedEvents = allEvents.filter((key, value) -> key.contains(userId)) //
					.groupByKey() //
					.aggregate(() -> new RentalCarEventDetails(new UserDetails(), new CarSearchDetails(), new RentalCarInfo(), new BookingDetails()), (aggKey, value, aggregate) -> {
						switch(value.getUserDetails().getEventType()) {
							case KafkaConstants.CASE_FIND_MY_CAR: {
								if(value.getUserDetails().isLoginFlag()) {
									aggregate.setCarSearchDetails(value.getCarSearchDetails());
									aggregate.setUserDetails(value.getUserDetails());
								}
								else {
									aggregate.setCarSearchDetails(value.getCarSearchDetails());
								}
								break;
							}
							case KafkaConstants.CASE_ADD_RENTAL_CAR: {
								if(value.getUserDetails().isLoginFlag()) {
									aggregate.setRentalCarInfo(value.getRentalCarInfo());
									aggregate.setUserDetails(value.getUserDetails());
								}
								else {
									aggregate.setRentalCarInfo(value.getRentalCarInfo());
								}
								break;
							}
							case KafkaConstants.CASE_UPGRADE_RENTAL_CAR: {
								if(value.getUserDetails().isLoginFlag()) {
									aggregate.setRentalCarInfo(value.getRentalCarInfo());
									aggregate.setUserDetails(value.getUserDetails());
								}
								else {
									aggregate.setRentalCarInfo(value.getRentalCarInfo());
								}
								aggregate.setRentalCarInfo(value.getRentalCarInfo());
								break;
							}
							case KafkaConstants.CASE_BOOK_RENTAL_CAR: {
								aggregate.setBookingDetails(value.getBookingDetails());
								break;
							}
							case KafkaConstants.CASE_USER_LOG_IN: {
								aggregate.setUserDetails(value.getUserDetails());
								break;
							}
						}
						if(aggregate.getBookingDetails().getBookingId() != null) {
							aggregate.getUserDetails().setEventType(KafkaConstants.CASE_FINALIZE_BOOKING);
						}
						else {
							aggregate.getUserDetails().setEventType(KafkaConstants.CASE_CART_ITEM);
						}
						return aggregate;
					});

			/*
			 * Once the aggregation is complete, the KTable object is converted to KStream because we wanted to associate the processed
			 * events to a new key, that is, the userID. So that, all the data related to a specific user is published to the same partition
			 * if we create more than one partitions in Kafka topics.
			 */
			KStream<String, RentalCarEventDetails> writeEvents = groupedEvents.toStream()//
					.map((key, value) -> {
						KeyValue<String, RentalCarEventDetails> keyValue;
						keyValue = new KeyValue<>(value.getUserDetails().getCustomerId(), value);
						return keyValue;
					});

			/*
			 * All the processed data is written to ProcessedDataTopic so that we have all the processed events in one place
			 * irrespective of the fact that whether it is a FinalizedBooking event (data) or CartItem event (data)
			 */
			writeEvents.to(KafkaConstants.getProcessedDataTopic(), Produced.with(Serdes.String(), KafkaConstants.getValueSerde()));

			/*
			 * All the processed events with event type "CART_ITEM" are published to a separate Kafka topic (CartDataTopic) from where
			 * we can consume data for the cart without filtering out the data that should not be in the cart. The processed events
			 * (data) is filtered according to event type.
			 * The events (data) is published to output Kafka Topic with String (key) serializer and RentalCarEventDetails (message) serializer.
			 */
			writeEvents.filter(new Predicate<String, RentalCarEventDetails>() {
				@Override
				public boolean test(String key, RentalCarEventDetails value) {
					return(value.getRentalCarInfo().getCarCategoryCode() != 0 && value.getCarSearchDetails().getPickupDate() != null
							&& value.getUserDetails().getEventType().equals(KafkaConstants.CASE_CART_ITEM));
				}
			}).to(KafkaConstants.getCartDataTopic(), Produced.with(Serdes.String(), KafkaConstants.getValueSerde()));

			/*
			 * Starts an instance of KafkaStreams which consumes data from the input Kafka topic and publishes streamed data to the 
			 * output Kafka topic. A delay of few seconds is used before closing the instance of KafkaStreams.
			 */
			executeStream();
		}

		public void executeStream() {
			KafkaStreams streams;
			streams = new KafkaStreams(builder.build(), KafkaProperties.configStream());
			streams.start();
			KafkaUtil.delayExecution(KafkaConstants.getDelayTimeValue("ProcessEventsDelayTime"));
			streams.close();
		}
	}
}