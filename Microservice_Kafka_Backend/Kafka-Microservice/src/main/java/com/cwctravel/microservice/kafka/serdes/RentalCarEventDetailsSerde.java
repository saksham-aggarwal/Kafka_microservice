package com.cwctravel.microservice.kafka.serdes;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.cwctravel.microservice.kafka.models.RentalCarEventDetails;

public class RentalCarEventDetailsSerde implements Serde<RentalCarEventDetails> {

	private RentalCarEventDetailsSerializer serializer;
	private RentalCarEventDetailsDeserializer deserializer;

	public RentalCarEventDetailsSerde() {
		this.serializer = new RentalCarEventDetailsSerializer();
		this.deserializer = new RentalCarEventDetailsDeserializer();
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		serializer.configure(configs, isKey);
		deserializer.configure(configs, isKey);
	}

	@Override
	public void close() {
		serializer.close();
		deserializer.close();
	}

	@Override
	public Serializer<RentalCarEventDetails> serializer() {
		return serializer;
	}

	@Override
	public Deserializer<RentalCarEventDetails> deserializer() {
		return deserializer;
	}

}
