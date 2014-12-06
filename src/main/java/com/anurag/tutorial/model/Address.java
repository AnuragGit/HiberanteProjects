package com.anurag.tutorial.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Anurag
 * 
 *         Concept:- for value object it is not is entity object. Value object
 *         means does not have real meaning for self individually.
 */
@Embeddable
public class Address {
	@Column(name = "STREET_NAME")
	private String street;
	@Column(name = "CITY_NAME")
	private String city;
	@Column(name = "STATE_NAME")
	private String state;
	@Column(name = "PIN_CODE")
	private String pincode;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String toString() {
		return " {Street: " + street + " City: " + city + " State: " + state
				+ " Pincode: " + pincode + " }";
	}
}
