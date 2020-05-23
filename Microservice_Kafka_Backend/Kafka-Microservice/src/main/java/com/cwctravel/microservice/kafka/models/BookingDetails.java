package com.cwctravel.microservice.kafka.models;

public class BookingDetails {

	private double rentalPrice;
	private String bookingId;

	public BookingDetails() {

	}

	public BookingDetails(String bookingId, double rentalPrice) {
		this.setBookingId(bookingId);
		this.setRentalPrice(rentalPrice);
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	@Override
	public String toString() {
		return "BookingDetails: [BookingId: " + this.getBookingId() + ", Rental Price: " + this.getRentalPrice() + "]";
	}
}
