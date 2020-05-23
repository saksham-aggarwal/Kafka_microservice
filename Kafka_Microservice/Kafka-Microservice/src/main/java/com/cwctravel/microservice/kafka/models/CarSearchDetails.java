package com.cwctravel.microservice.kafka.models;

import java.io.Serializable;
import java.util.Calendar;

import com.cwctravel.search.CarSearchInfo;

public class CarSearchDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pickupCityCode;
	private String pickupState;
	private String pickupZipCode;
	private boolean pickupAirport;
	private String pickupName;
	private String pickupCountry;
	private int pickupRadius;
	private String pickupStreetAddress;
	private double pickupLatitude;
	private double pickupLongitude;

	private String dropoffCityCode;
	private String dropoffState;
	private String dropoffZipCode;
	private boolean dropoffAirport;
	private String dropoffName;
	private String dropoffCountry;
	private int dropoffRadius;
	private String dropoffStreetAddress;
	private double dropoffLatitude;
	private double dropoffLongitude;

	private Calendar pickupDate;
	private Calendar dropoffDate;

	public CarSearchDetails() {

	}

	public CarSearchDetails(String pickupCityCode, String pickupState, String pickupZipCode, boolean pickupAirport, String pickupName, String pickupCountry, int pickupRadius, String pickupStreetAddress, double pickupLatitude, double pickupLongitude, String dropoffCityCode, String dropoffState, String dropoffZipCode, boolean dropoffAirport, String dropoffName, String dropoffCountry, int dropoffRadius, String dropoffStreetAddress, double dropoffLatitude, double dropoffLongitude, Calendar pickupDate, Calendar dropoffDate) {
		this.pickupCityCode = pickupCityCode;
		this.pickupState = pickupState;
		this.pickupZipCode = pickupZipCode;
		this.pickupAirport = pickupAirport;
		this.pickupName = pickupName;
		this.pickupCountry = pickupCountry;
		this.pickupRadius = pickupRadius;
		this.pickupStreetAddress = pickupStreetAddress;
		this.pickupLatitude = pickupLatitude;
		this.pickupLongitude = pickupLongitude;

		this.dropoffCityCode = dropoffCityCode;
		this.dropoffState = dropoffState;
		this.dropoffZipCode = dropoffZipCode;
		this.dropoffAirport = dropoffAirport;
		this.dropoffName = dropoffName;
		this.dropoffCountry = dropoffCountry;
		this.dropoffRadius = dropoffRadius;
		this.dropoffStreetAddress = dropoffStreetAddress;
		this.dropoffLatitude = dropoffLatitude;
		this.dropoffLongitude = dropoffLongitude;

		this.pickupDate = pickupDate;
		this.dropoffDate = dropoffDate;
	}

	public CarSearchDetails(CarSearchInfo _carSearchInfo) {
		this.pickupCityCode = _carSearchInfo.getPickupCity().getCityCode();
		this.pickupState = _carSearchInfo.getPickupCity().getState();
		this.pickupZipCode = _carSearchInfo.getPickupCity().getZipCode();
		this.pickupAirport = _carSearchInfo.getPickupCity().isAirport();
		this.pickupName = _carSearchInfo.getPickupCity().getName();
		this.pickupCountry = _carSearchInfo.getPickupCity().getCountry();
		this.pickupRadius = _carSearchInfo.getPickupCity().getRadius();
		this.pickupStreetAddress = _carSearchInfo.getPickupCity().getStreetAddress();
		this.pickupLatitude = _carSearchInfo.getPickupCity().getLatitude();
		this.pickupLongitude = _carSearchInfo.getPickupCity().getLongitude();

		this.dropoffCityCode = _carSearchInfo.getDropoffCity().getCityCode();
		this.dropoffState = _carSearchInfo.getDropoffCity().getState();
		this.dropoffZipCode = _carSearchInfo.getDropoffCity().getZipCode();
		this.dropoffAirport = _carSearchInfo.getDropoffCity().isAirport();
		this.dropoffName = _carSearchInfo.getDropoffCity().getName();
		this.dropoffCountry = _carSearchInfo.getDropoffCity().getCountry();
		this.dropoffRadius = _carSearchInfo.getDropoffCity().getRadius();
		this.dropoffStreetAddress = _carSearchInfo.getDropoffCity().getStreetAddress();
		this.dropoffLatitude = _carSearchInfo.getDropoffCity().getLatitude();
		this.dropoffLongitude = _carSearchInfo.getDropoffCity().getLongitude();

		this.pickupDate = _carSearchInfo.getPickupDate();
		this.dropoffDate = _carSearchInfo.getDropoffDate();
	}

	public CarSearchDetails(CarSearchDetails other) {
		this.setPickupCityCode(other.getPickupCityCode());
		this.setPickupState(other.getPickupState());
		this.setPickupZipCode(other.getPickupZipCode());
		this.setPickupAirport(other.isPickupAirport());
		this.setPickupName(other.getPickupName());
		this.setPickupCountry(other.getPickupCountry());
		this.setPickupRadius(other.getPickupRadius());
		this.setPickupStreetAddress(other.getPickupStreetAddress());
		this.setPickupLatitude(other.getPickupLatitude());
		this.setPickupLongitude(other.getPickupLongitude());

		this.setDropoffCityCode(other.getDropoffCityCode());
		this.setDropoffState(other.getDropoffState());
		this.setDropoffZipCode(other.getDropoffZipCode());
		this.setDropoffAirport(other.isDropoffAirport());
		this.setDropoffName(other.getDropoffName());
		this.setDropoffCountry(other.getDropoffCountry());
		this.setDropoffRadius(other.getDropoffRadius());
		this.setDropoffStreetAddress(other.getDropoffStreetAddress());
		this.setDropoffLatitude(other.getDropoffLatitude());
		this.setDropoffLongitude(other.getDropoffLongitude());

		this.setPickupDate(other.getPickupDate());
		this.setDropoffDate(other.getDropoffDate());
	}

	public String getPickupCityCode() {
		return pickupCityCode;
	}

	public void setPickupCityCode(String pickupCityCode) {
		this.pickupCityCode = pickupCityCode;
	}

	public String getPickupState() {
		return pickupState;
	}

	public void setPickupState(String pickupState) {
		this.pickupState = pickupState;
	}

	public String getPickupZipCode() {
		return pickupZipCode;
	}

	public void setPickupZipCode(String pickupZipCode) {
		this.pickupZipCode = pickupZipCode;
	}

	public boolean isPickupAirport() {
		return pickupAirport;
	}

	public void setPickupAirport(boolean pickupAirport) {
		this.pickupAirport = pickupAirport;
	}

	public String getPickupName() {
		return pickupName;
	}

	public void setPickupName(String pickupName) {
		this.pickupName = pickupName;
	}

	public String getPickupCountry() {
		return pickupCountry;
	}

	public void setPickupCountry(String pickupCountry) {
		this.pickupCountry = pickupCountry;
	}

	public int getPickupRadius() {
		return pickupRadius;
	}

	public void setPickupRadius(int pickupRadius) {
		this.pickupRadius = pickupRadius;
	}

	public String getPickupStreetAddress() {
		return pickupStreetAddress;
	}

	public void setPickupStreetAddress(String pickupStreetAddress) {
		this.pickupStreetAddress = pickupStreetAddress;
	}

	public double getPickupLatitude() {
		return pickupLatitude;
	}

	public void setPickupLatitude(double pickupLatitude) {
		this.pickupLatitude = pickupLatitude;
	}

	public double getPickupLongitude() {
		return pickupLongitude;
	}

	public void setPickupLongitude(double pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}

	public String getDropoffCityCode() {
		return dropoffCityCode;
	}

	public void setDropoffCityCode(String dropoffCityCode) {
		this.dropoffCityCode = dropoffCityCode;
	}

	public String getDropoffState() {
		return dropoffState;
	}

	public void setDropoffState(String dropoffState) {
		this.dropoffState = dropoffState;
	}

	public String getDropoffZipCode() {
		return dropoffZipCode;
	}

	public void setDropoffZipCode(String dropoffZipCode) {
		this.dropoffZipCode = dropoffZipCode;
	}

	public boolean isDropoffAirport() {
		return dropoffAirport;
	}

	public void setDropoffAirport(boolean dropoffAirport) {
		this.dropoffAirport = dropoffAirport;
	}

	public String getDropoffName() {
		return dropoffName;
	}

	public void setDropoffName(String dropoffName) {
		this.dropoffName = dropoffName;
	}

	public String getDropoffCountry() {
		return dropoffCountry;
	}

	public void setDropoffCountry(String dropoffCountry) {
		this.dropoffCountry = dropoffCountry;
	}

	public int getDropoffRadius() {
		return dropoffRadius;
	}

	public void setDropoffRadius(int dropoffRadius) {
		this.dropoffRadius = dropoffRadius;
	}

	public String getDropoffStreetAddress() {
		return dropoffStreetAddress;
	}

	public void setDropoffStreetAddress(String dropoffStreetAddress) {
		this.dropoffStreetAddress = dropoffStreetAddress;
	}

	public double getDropoffLatitude() {
		return dropoffLatitude;
	}

	public void setDropoffLatitude(double dropoffLatitude) {
		this.dropoffLatitude = dropoffLatitude;
	}

	public double getDropoffLongitude() {
		return dropoffLongitude;
	}

	public void setDropoffLongitude(double dropoffLongitude) {
		this.dropoffLongitude = dropoffLongitude;
	}

	public Calendar getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Calendar pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Calendar getDropoffDate() {
		return dropoffDate;
	}

	public void setDropoffDate(Calendar dropoffDate) {
		this.dropoffDate = dropoffDate;
	}

	@Override
	public String toString() {
		return "CarSearchInfo: [pickupName: " + this.getPickupName() + ", pickupStreetAddress: " + this.getPickupStreetAddress() + ", pickupZipCode: "
				+ this.getPickupZipCode() + ", droppoffName: " + this.getDropoffName() + ", dropoffStreetAddress: " + this.getDropoffStreetAddress()
				+ ", dropoffZipCode: " + this.getDropoffZipCode() + ", pickupDate: " + this.getPickupDate() + ", dropoffDate: "
				+ this.getDropoffDate() + "]";
	}

	public String getCalendarDate(Calendar calendar) {
		if(calendar == null) {
			return "";
		}
		StringBuilder calendarDate = new StringBuilder();
		int month = calendar.get(calendar.MONTH) + 1;
		calendarDate.append(calendar.get(calendar.DAY_OF_MONTH) + "-");
		calendarDate.append(calendar.get(calendar.MONTH) + "-");
		calendarDate.append(calendar.get(calendar.YEAR) + "");
		return calendarDate.toString();
	}

	public String getTime(Calendar calendar) {
		if(calendar == null) {
			return "";
		}
		StringBuilder time = new StringBuilder();

		if(calendar.get(calendar.HOUR) == 0) {
			time.append("12:");
		}
		else {
			if(calendar.get(calendar.HOUR) <= 9) {
				time.append("0" + calendar.get(calendar.HOUR) + ":");
			}
			else {
				time.append(calendar.get(calendar.HOUR));
			}
		}

		if(calendar.get(calendar.MINUTE) <= 9) {
			time.append(calendar.get(calendar.MINUTE) + "0");
		}
		else {
			time.append(calendar.get(calendar.MINUTE));
		}

		if(calendar.get(calendar.AM_PM) == 1) {
			time.append(" PM");
		}
		else {
			time.append(" AM");
		}
		return time.toString();
	}
}
