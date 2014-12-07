package com.anurag.tutorial.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="OneToOneUserWithCasscaded")
public class OneToOneUserWithCasscaded {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name = "VEHICLE_ID")
	private OneToOneVehicalMapping vehicle;

	public OneToOneVehicalMapping getVehicle() {
		return vehicle;
	}

	public void setVehicle(OneToOneVehicalMapping vehicle) {
		this.vehicle = vehicle;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "OneToOneUserMapping [userId=" + userId + ", userName="
				+ userName + ", vehicle=" + vehicle + "]";
	}
	
}
