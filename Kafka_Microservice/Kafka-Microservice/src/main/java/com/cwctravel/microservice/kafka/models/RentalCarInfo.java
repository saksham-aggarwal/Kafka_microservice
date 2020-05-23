package com.cwctravel.microservice.kafka.models;

import java.io.Serializable;

import com.cwctravel.search.product.item.CarAgencyItem;
import com.cwctravel.search.product.item.CarCategoryItem;
import com.cwctravel.search.product.item.CarCompanyItem;

public class RentalCarInfo implements Serializable {

	private int carCategoryCode;
	private String carCategoryName;
	private String carCompanyCode;
	private String carCompanyName;
	private String carAgencyCode;
	private String carAgencyName;
	private String carMake;
	private String sippCode;

	private final static long serialVersionUID = 8341772925578862891L;

	public RentalCarInfo() {
	}

	/**
	 * @param carCategoryCode
	 * @param carCategoryName
	 * @param carCompanyCode
	 * @param carCompanyName
	 * @param carAgencyCode
	 * @param carAgencyName
	 * @param carMake
	 * @param sippCode
	 */
	public RentalCarInfo(CarCategoryItem carCategoryItem, CarCompanyItem carCompanyItem, CarAgencyItem carAgencyItem) {
		this.carCategoryCode = carCategoryItem.getCategoryCode();
		this.carCategoryName = carCategoryItem.getCategoryName();
		this.carCompanyCode = carCompanyItem.getCompanyCode();
		this.carCompanyName = carCompanyItem.getCompanyName();
		this.carAgencyCode = carAgencyItem.getAgencyCode();
		this.carAgencyName = carAgencyItem.getAgencyName();
		this.carMake = carCategoryItem.getMake();
		this.sippCode = carCategoryItem.getSippCode();
	}

	public RentalCarInfo(RentalCarInfo other) {
		this.setCarCategoryCode(other.getCarCategoryCode());
		this.setCarCategoryName(other.getCarCategoryName());
		this.setCarCompanyCode(other.getCarCompanyCode());
		this.setCarCompanyName(other.getCarCompanyName());
		this.setCarAgencyCode(other.getCarAgencyCode());
		this.setCarAgencyName(other.getCarAgencyName());
		this.setCarMake(other.getCarMake());
		this.setSippCode(other.getSippCode());
	}

	public int getCarCategoryCode() {
		return carCategoryCode;
	}

	public void setCarCategoryCode(int carCategoryCode) {
		this.carCategoryCode = carCategoryCode;
	}

	public String getCarCategoryName() {
		return carCategoryName;
	}

	public void setCarCategoryName(String carCategoryName) {
		this.carCategoryName = carCategoryName;
	}

	public String getCarCompanyCode() {
		return carCompanyCode;
	}

	public void setCarCompanyCode(String carCompanyCode) {
		this.carCompanyCode = carCompanyCode;
	}

	public String getCarCompanyName() {
		return carCompanyName;
	}

	public void setCarCompanyName(String carCompanyName) {
		this.carCompanyName = carCompanyName;
	}

	public String getCarAgencyCode() {
		return carAgencyCode;
	}

	public void setCarAgencyCode(String carAgencyCode) {
		this.carAgencyCode = carAgencyCode;
	}

	public String getCarAgencyName() {
		return carAgencyName;
	}

	public void setCarAgencyName(String carAgencyName) {
		this.carAgencyName = carAgencyName;
	}

	public String getCarMake() {
		return carMake;
	}

	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}

	public String getSippCode() {
		return sippCode;
	}

	public void setSippCode(String sippCode) {
		this.sippCode = sippCode;
	}

	@Override
	public String toString() {
		return "RentalCarDetails: [carCategoryCode: " + this.getCarCategoryCode() + ", carCategoryName: " + this.getCarCategoryName()
				+ ", carCompanyCode: " + this.getCarCompanyCode() + ", carCompanyName: " + this.getCarCompanyName() + ", carAgencyCode: "
				+ this.getCarAgencyCode() + ", carAgencyName: " + this.getCarAgencyName() + ", carMake: " + this.getCarMake() + ", sippCode: "
				+ this.getSippCode() + "]";
	}
}
