package com.cwctravel.microservice.kafka.serdes;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.cwctravel.microservice.kafka.models.RentalCarEventDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RentalCarEventDetailsSerializer implements Serializer<RentalCarEventDetails> {

	@Override
	public byte[] serialize(String arg0, RentalCarEventDetails rentalCarEventData) {
		byte[] serializedBytes = null;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			serializedBytes = objectMapper.writeValueAsString(rentalCarEventData).getBytes();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return serializedBytes;
	}

	@Override
	public void close() {

	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {

	}
}
