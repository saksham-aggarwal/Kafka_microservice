package com.cwctravel.microservice.kafka.models;

import java.util.ArrayList;
import java.util.List;

public class RentalCarEventList {

	private List<RentalCarEventDetails> consumerRecordList;

	public RentalCarEventList() {
		this.consumerRecordList = new ArrayList<>();
	}

	public List<RentalCarEventDetails> getConsumerRecordsList() {
		return consumerRecordList;
	}

	public void setConsumerRecordsList(List<RentalCarEventDetails> consumerRecords) {
		this.consumerRecordList = consumerRecords;
	}

	public void addToList(RentalCarEventDetails record) {
		this.addToList(record);
	}
}