package com.cwctravel.microservice.kafka.models;

import java.io.Serializable;

public class UserDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String SID;
	private String transactionId;
	private String eventType;
	private String customerId;
	private boolean loginFlag;
	private Long eventTimestamp;
	private int workflowIdentifier;

	public UserDetails() {

	}

	public UserDetails(UserDetails other) {
		this.setSID(other.getSID());
		this.setCustomerId(other.getCustomerId());
		this.setEventType(other.getEventType());
		this.setTransactionId(other.getTransactionId());
		this.setLoginFlag(other.isLoginFlag());
		this.setEventTimestamp(other.getEventTimestamp());
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public boolean isLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(boolean loginFlag) {
		this.loginFlag = loginFlag;
	}

	public Long getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(long l) {
		this.eventTimestamp = l;
	}

	public int getWorkflowIdentifier() {
		return workflowIdentifier;
	}

	public void setWorkflowIdentifier(int workflowIdentifier) {
		this.workflowIdentifier = workflowIdentifier;
	}

	@Override
	public String toString() {
		return "UserDetails: [SID: " + this.getSID() + ", transactionId: " + this.getTransactionId() + ", eventType: " + this.getEventType()
				+ ", customerId: " + this.getCustomerId() + ", workflowIdentifier: " + this.getWorkflowIdentifier() + ", loginFlag: "
				+ this.isLoginFlag() + ", eventTimestamp: " + this.getEventTimestamp() + "]";
	}

}