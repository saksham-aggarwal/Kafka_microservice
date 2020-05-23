package com.cwctravel.microservice.kafka.serdes;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.cwctravel.microservice.kafka.models.RentalCarEventDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RentalCarEventDetailsDeserializer implements Deserializer<RentalCarEventDetails> {

	@Override
	public RentalCarEventDetails deserialize(String arg0, byte[] rentalCarEventDetailsBytes) {

		ObjectMapper mapper = new ObjectMapper();
		RentalCarEventDetails rentalCarEventDetails = null;
		try {
			rentalCarEventDetails = mapper.readValue(rentalCarEventDetailsBytes, RentalCarEventDetails.class);
		}
		catch(Exception e) {

			e.printStackTrace();
		}

		return rentalCarEventDetails;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

}