package com.anurag.tutorial.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FOUR_WHEELER")
@DiscriminatorValue("Car")
public class FourWheeler extends InheritanceVehicle {
	@Column(name = "STEERING_TYPE")
	private String steeringFourWheeler;

	public String getSteeringFourWheeler() {
		return steeringFourWheeler;
	}

	public void setSteeringFourWheeler(String steeringFourWheeler) {
		this.steeringFourWheeler = steeringFourWheeler;
	}

	@Override
	public String toString() {
		return "FourWheeler [steeringFourWheeler=" + steeringFourWheeler
				+ ", toString()=" + super.toString() + "]";
	}

	

	
	
	
	
}
