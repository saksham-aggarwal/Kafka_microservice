package com.cwctravel.microservice.kafka.models;

import java.io.Serializable;

public class RentalCarEventDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -144496038578781206L;

	private UserDetails userDetails;
	private CarSearchDetails carSearchDetails;
	private RentalCarInfo rentalCarInfo;
	private BookingDetails bookingDetails;

	public RentalCarEventDetails() {

	}

	public RentalCarEventDetails(UserDetails passedUserDetails, CarSearchDetails passedCarSearchDetails, RentalCarInfo passedRentalCarInfo, BookingDetails passedBookingDetails) {
		this.setUserDetails(passedUserDetails);
		this.setCarSearchDetails(passedCarSearchDetails);
		this.setRentalCarInfo(passedRentalCarInfo);
		this.setBookingDetails(passedBookingDetails);
	}

	public RentalCarEventDetails(RentalCarEventDetails other) {
		this.setUserDetails(other.getUserDetails());
		this.setCarSearchDetails(other.getCarSearchDetails());
		this.setRentalCarInfo(other.getRentalCarInfo());
		this.setBookingDetails(other.getBookingDetails());
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public CarSearchDetails getCarSearchDetails() {
		return carSearchDetails;
	}

	public RentalCarInfo getRentalCarInfo() {
		return rentalCarInfo;
	}

	public void setRentalCarInfo(RentalCarInfo rentalCarInfo) {
		this.rentalCarInfo = rentalCarInfo;
	}

	public void setCarSearchDetails(CarSearchDetails carSearchDetails) {
		this.carSearchDetails = carSearchDetails;
	}

	public BookingDetails getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(BookingDetails bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	@Override
	public String toString() {
		if(this.getCarSearchDetails() == null && this.getRentalCarInfo() == null) {
			return "RentalCarEventDetails: [" + this.getUserDetails().toString() + ", CarSearchDetails: " + "null" + ", RentalCarInfo: " + "null"
					+ "]";
		}
		else if(this.getCarSearchDetails() == null) {
			return "RentalCarEventDetails: [" + this.getUserDetails().toString() + ", CarSearchDetails: " + "null" + ", "
					+ this.getRentalCarInfo().toString() + "]";
		}
		else if(this.getRentalCarInfo() == null) {
			return "RentalCarEventDetails: [" + this.getUserDetails().toString() + ", " + this.getCarSearchDetails().toString() + ", RentalCarInfo: "
					+ "null" + "]";
		}
		return "RentalCarEventDetails: [" + this.getUserDetails() + ", " + this.getCarSearchDetails() + ", " + this.getRentalCarInfo() + "]";
	}
}
