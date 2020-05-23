package com.cwctravel.microservice.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cwctravel.microservice.kafka.constants.ErrorConstants;
import com.cwctravel.microservice.kafka.constants.KafkaConstants;
import com.cwctravel.microservice.kafka.models.RentalCarEventDetails;
import com.cwctravel.microservice.kafka.models.RentalCarEventList;
import com.cwctravel.microservice.kafka.properties.ConfigProperties;
import com.cwctravel.microservice.kafka.service.Consumer;
import com.cwctravel.microservice.kafka.service.Producer;
import com.cwctravel.microservice.kafka.service.Streams;

// This is the controller of the micro-service. It lists out all the endpoints which are used by the CWCT Consumer application
// to make http calls in order to interact with Kafka. These are the REST end points.

@RestController
public class WebAppController {

	@Autowired
	ConfigProperties configProps;

	// Producer object
	@Autowired
	Producer messageProducer;

	// Consumer object
	@Autowired
	Consumer messageConsumer;

	// Streams.RekeyEvents object
	@Autowired
	Streams.RekeyEvents messageRekeyStream;

	// Streams.ProcessAllEvents object
	@Autowired
	Streams.ProcessAllEvents messageProcessStream;

	/*
	 * Invokes the producer object which makes a call to publishDataToKafka() to publish data to Kafka
	 * Param: RentalCarEventDetails object which has the data related to the event that needs to be published to Kafka
	 * Return: String indicating the status of the REST call
	 */
	@RequestMapping(value = "/kafkaMicroservice/produce", method = RequestMethod.POST, produces = {"application/json"})
	public String sendMessageToKafkaTopic(@RequestBody RentalCarEventDetails event) {
		messageProducer.publishDataToKafka(event);
		return "Publish successful!";
	}

	/*
	 * Invokes the consumer object which makes a call to consumeDataFromKafka() to consume data from Kafka
	 * Param: String userId indicating the customerId whose data needs to be retrieved from Kafka
	 * 		  String actionType indicating whether Activities or Cart Items are to be retrieved from Kafka
	 * Return: RentalCarRecordList which contains the retrieved data that is sent to the CWCT Consumer application in the response body of the HTTP call
	 */
	@RequestMapping(value = "/kafkaMicroservice/consume", method = RequestMethod.POST)
	public RentalCarEventList receiveMessageFromKafkaTopic(@RequestParam(value = "userId") String userId, @RequestParam(value = "actionType") String actionType) {
		return messageConsumer.consumeDataFromKafka(userId, actionType);
	}

	/*
	 * Invokes the stream object which either re-key all the events or aggregate all the events stored in Kafka based on streamType
	 * Param: String streamType indicating the type of stream to be invoked
	 * 		  String sessionId which is used to filter out the data based on the sessionId
	 * 		  String userId used to process all the data related to a specific userId 
	 * Return: String indicating the status of the REST call
	 */
	@RequestMapping(value = "/kafkaMicroservice/stream", method = RequestMethod.POST)
	public String startStreamingData(@RequestParam(value = "streamType") String streamType, @RequestParam(value = "sid", required = false) String sessionId, @RequestParam(value = "userId") String userId) {
		if(streamType.equals(KafkaConstants.getActionRekeyEvents())) {
			messageRekeyStream.rekeyAllEvents(sessionId, userId);
		}
		else if(streamType.equals(KafkaConstants.getActionProcessEvents())) {
			messageProcessStream.processAllEvents(userId);
		}
		else {
			return ErrorConstants.getInvalidStreamType();
		}
		return "Processing successful!";
	}

	@RequestMapping(value = "/kafkaMicroservice/print", method = RequestMethod.GET)
	public void print() {
		System.out.println("Bootstrap Servers: " + configProps.getConfigValue("bootstrap.servers"));
	}
}
