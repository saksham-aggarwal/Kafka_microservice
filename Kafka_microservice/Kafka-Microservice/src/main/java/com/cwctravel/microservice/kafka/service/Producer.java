package com.cwctravel.microservice.kafka.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import com.cwctravel.microservice.kafka.constants.KafkaConstants;
import com.cwctravel.microservice.kafka.models.RentalCarEventDetails;
import com.cwctravel.microservice.kafka.properties.KafkaProperties;

/*
 * This class has the methods which create the producer to publish messages to Kafka. This also decides as to which topic should the 
 * message is published.
 */

@Component
public class Producer {

	private org.apache.kafka.clients.producer.Producer<String, RentalCarEventDetails> producer;

	/*
	 * Invokes the createProducer() to create a producer
	 */
	public Producer() {
		this.producer = createProducer();
	}

	/*
	 * Creates a producer using the properties defined in KafkaProperties class
	 */
	private org.apache.kafka.clients.producer.Producer<String, RentalCarEventDetails> createProducer() {
		return new KafkaProducer<>(KafkaProperties.configProducer());
	}

	/* 
	 * Generates a key using sessionId, customerId and workflowId
	 * Param: RentalCarEventDetails event which has event-specific data
	 * Return: String which is the key that is used to publish a message
	 */
	private String keyGenerator(RentalCarEventDetails message) {
		return message.getUserDetails().getCustomerId() + "_" + message.getUserDetails().getSID() + "_"
				+ message.getUserDetails().getWorkflowIdentifier();
	}

	/*
	 * Publishes messages to Kafka 
	 * Param: String topic - Kafka topic to which message should be published
	 * 		  String key - message is associated with this key when published to Kafka
	 * 		  RentalCarEventDetails message - message that should be published to Kafka
	 */
	private void sendDataToKafkaTopic(String topic, String key, RentalCarEventDetails message) {
		this.producer.send(new ProducerRecord<>(topic, key, message));
	}

	/*
	 * Decides the topic to which a message is published based on the type of event
	 */
	public void publishDataToKafka(RentalCarEventDetails message) {

		if(message.getUserDetails().getCustomerId().equals("") || message.getUserDetails().getEventType().equals(KafkaConstants.getCaseUserLogIn())) {
			sendDataToKafkaTopic(KafkaConstants.getRawDataTopic(), message.getUserDetails().getCustomerId(), message);
		}
		else {
			sendDataToKafkaTopic(KafkaConstants.getRekeyDataTopic(), keyGenerator(message), message);
		}
	}

}
