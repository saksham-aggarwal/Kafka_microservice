package com.cwctravel.microservice.kafka.service;

import java.time.Duration;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import com.cwctravel.microservice.kafka.constants.KafkaConstants;
import com.cwctravel.microservice.kafka.models.RentalCarEventDetails;
import com.cwctravel.microservice.kafka.models.RentalCarEventList;
import com.cwctravel.microservice.kafka.properties.KafkaProperties;

/*
 * This is Consumer class which has the methods utilized to consume data which could be easily sent to CWCT Consumer application.
 */

@Component
public class Consumer {

	private KafkaConsumer<String, RentalCarEventDetails> consumer;
	private RentalCarEventList consumedData = new RentalCarEventList();

	/*
	 * Creates a Kafka Consumer which is used to consume data from Kafka Topic
	 */
	private static KafkaConsumer<String, RentalCarEventDetails> createConsumer() {
		return new KafkaConsumer<>(KafkaProperties.configConsumer());
	}

	/*
	 * Utilizes KafkaConsumer library in order to read messages from Kafka. Subscribes to a topic in order to consume data. 
	 * Filters the data according to the userId. Commits the offset manually so that if a consumer fails, then the next 
	 * consumer can consumer data after the last successfully read message.
	 * Param: String topic from which data needs to be consumed
	 * 		  String userId for which data needs to be consumed
	 * Return: ConsumerRecordList which contains the data consumed from Kafka topic
	 */
	public RentalCarEventList receivedDataFromKafkaTopic(String topic, String userId) {
		consumer = createConsumer();
		consumer.subscribe(Arrays.asList(topic));
		RentalCarEventList recordList = new RentalCarEventList();
		int bufferSize = 0;

		do {
			ConsumerRecords<String, RentalCarEventDetails> allRecords = consumer.poll(Duration.ofMillis(5000));

			for(ConsumerRecord<String, RentalCarEventDetails> record: allRecords) {
				if(record.key().equals(userId)) {
					recordList.getConsumerRecordsList().add(new RentalCarEventDetails(record.value()));
					bufferSize++;
				}
			}

			if(bufferSize > 0) {
				consumer.commitSync();
				for(RentalCarEventDetails bufferRecord: recordList.getConsumerRecordsList()) {
					consumedData.getConsumerRecordsList().add(bufferRecord);
				}
				bufferSize = 0;
				recordList.getConsumerRecordsList().clear();
			}
			consumer.close();
			return consumedData;
		}while(bufferSize != 0);
	}

	/*
	 * Decides which topic the data should be read from.
	 * Param: String userId for which data needs to be consumed
	 * 		  String actionType indicating whether activities or cart items are to be consumed from Kafka
	 * Return: ConsumerRecordList contains the data that is consumed from Kafka topic
	 */
	public RentalCarEventList consumeDataFromKafka(String userId, String actionType) {
		if(actionType.equals(KafkaConstants.getActionLoadCartItems())) {
			return this.receivedDataFromKafkaTopic(KafkaConstants.getCartDataTopic(), userId);
		}
		else if(actionType.equals(KafkaConstants.getActionLoadActivities())) {
			return this.receivedDataFromKafkaTopic(KafkaConstants.getRekeyDataTopic(), userId);
		}
		else {
			return null;
		}
	}

	public RentalCarEventList getConsumedData() {
		return this.consumedData;
	}

	public void setConsumerRecordList(RentalCarEventList list) {
		this.consumedData = list;
	}
}
