package com.cwctravel.microservice.kafka.constants;

import java.util.HashMap;
import java.util.UUID;

import com.cwctravel.microservice.kafka.serdes.RentalCarEventDetailsSerde;

/*
 * This class contains all the constants used in the micro-service.
 */

public class KafkaConstants {

	/*
	 * Configuration constants used by KafkaProperties for configuring Kafka Producer, Consumer
	 * and Streams
	 */
	private static final String BOOTSTRAP_SERVERS = "localhost:9092";
	private static final String GROUP_ID = UUID.randomUUID().toString();
	private static final String APPLICATION_ID = UUID.randomUUID().toString();
	private static final String AUTO_COMMIT = "false";
	private static final String OFFSET_RESET = "earliest";
	private static final int HEARTBEAT_INTERVAL_MS = 1500;

	/*
	 * These are the four topics which are used by the micro-service to publish, subscribe and 
	 * process data to and from. 
	 * RAW_DATA_TOPIC - contains all the messages which do not have a key
	 * REKEY_DATA_TOPIC - contains all the messages which have keys
	 * PROCESSED_DATA_TOPIC - contains all the messages which gives the current state of an entity after
	 * 						  processing all the events
	 * CART_DATA_TOPIC - (Use case specific) contains all the messages which indicate the current state of the 
	 * 					 entity and also has event type to be "CART_ITEM"
	 */
	private static final String RAW_DATA_TOPIC = "cwct-all-events-no-key-test-1";
	private static final String REKEY_DATA_TOPIC = "cwct-all-events-rekey-test-1";
	private static final String PROCESSED_DATA_TOPIC = "cwct-processed-events-test-1";
	private static final String CART_DATA_TOPIC = "cwct-cart-items-test-1";

	/*
	 * Custom Serializer/Deserializer that is used by KafkaStreams to process data stored in Kafka topics. delayTime
	 * is a map which stores the duration for which a stream process should wait before closing Kafka Streams instance.
	 */
	private static final RentalCarEventDetailsSerde VALUE_SERDE = new RentalCarEventDetailsSerde();
	private static final HashMap<String, Integer> delayTime = new HashMap<String, Integer>() {
		{
			put("RekeyEventsDelayTime", 2300);
			put("ProcessEventsDelayTime", 3050);
		}
	};

	/*
	 * Event type constants which are used by Kafka Streams to process and aggregate the events stored in Kafka
	 * Used in "Streams.ProcessAllEvents" service
	 */
	public static final String CASE_FIND_MY_CAR = "FIND_MY_CAR";
	public static final String CASE_ADD_RENTAL_CAR = "ADDED_RENTAL_CAR";
	public static final String CASE_UPGRADE_RENTAL_CAR = "UPGRADED_RENTAL_CAR";
	public static final String CASE_BOOK_RENTAL_CAR = "BOOKED_RENTAL_CAR";
	public static final String CASE_USER_LOG_IN = "USER_LOGGED_IN";
	public static final String CASE_FINALIZE_BOOKING = "FINALIZED_BOOKING";
	public static final String CASE_CART_ITEM = "CART_ITEM";

	/*
	 * Action constants used in "Consumer" service to decide which Kafka topic to consume data from.
	 */
	private static final String ACTION_LOAD_CART_ITEMS = "LOAD_CART_ITEMS";
	private static final String ACTION_LOAD_ACTIVITIES = "LOAD_ACTIVITIES";

	/*
	 * Action constants used in "WebAppController" to decide which Kafka Streams to invoke.
	 */
	private static final String ACTION_REKEY_EVENTS = "REKEY_EVENTS";
	private static final String ACTION_PROCESS_EVENTS = "PROCESS_EVENTS";

	public static String getBootstrapServers() {
		return BOOTSTRAP_SERVERS;
	}

	public static String getGroupId() {
		return GROUP_ID;
	}

	public static String getApplicationId() {
		return APPLICATION_ID;
	}

	public static String getAutoCommit() {
		return AUTO_COMMIT;
	}

	public static String getOffsetReset() {
		return OFFSET_RESET;
	}

	public static int getHeartbeatIntervalMs() {
		return HEARTBEAT_INTERVAL_MS;
	}

	public static String getRawDataTopic() {
		return RAW_DATA_TOPIC;
	}

	public static String getRekeyDataTopic() {
		return REKEY_DATA_TOPIC;
	}

	public static String getProcessedDataTopic() {
		return PROCESSED_DATA_TOPIC;
	}

	public static String getCartDataTopic() {
		return CART_DATA_TOPIC;
	}

	public static RentalCarEventDetailsSerde getValueSerde() {
		return VALUE_SERDE;
	}

	public static int getDelayTimeValue(String key) {
		return delayTime.get(key);
	}

	public static String getCaseFindMyCar() {
		return CASE_FIND_MY_CAR;
	}

	public static String getCaseAddRentalCar() {
		return CASE_ADD_RENTAL_CAR;
	}

	public static String getCaseUpgradeRentalCar() {
		return CASE_UPGRADE_RENTAL_CAR;
	}

	public static String getCaseBookRentalCar() {
		return CASE_BOOK_RENTAL_CAR;
	}

	public static String getCaseUserLogIn() {
		return CASE_USER_LOG_IN;
	}

	public static String getCaseFinalizeBooking() {
		return CASE_FINALIZE_BOOKING;
	}

	public static String getCaseCartItem() {
		return CASE_CART_ITEM;
	}

	public static String getActionLoadCartItems() {
		return ACTION_LOAD_CART_ITEMS;
	}

	public static String getActionLoadActivities() {
		return ACTION_LOAD_ACTIVITIES;
	}

	public static String getActionRekeyEvents() {
		return ACTION_REKEY_EVENTS;
	}

	public static String getActionProcessEvents() {
		return ACTION_PROCESS_EVENTS;
	}

}
