package com.anurag.tutorial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * @author Anurag
 * 
 *    Concept:-  OneToOne mapping having following concept
 *                  1. By default fetch type is eager here.
 *                  2. OneToOne  mapping  is a has a relation ship with another class
 *                  3. If we will provide oneToOne mapping in class it means it is the owner class
 *                  4. If we provide one to one mapping with mapped by it means it in nonowner class.
 *
 */
@Entity
@Table(name = "OneToOneUser")
public class OneToOneUserMapping {
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@OneToOne(fetch=FetchType.LAZY)
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
