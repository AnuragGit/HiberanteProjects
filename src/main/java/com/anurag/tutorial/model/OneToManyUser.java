package com.anurag.tutorial.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="OneToMany_USER")
public class OneToManyUser {
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@OneToMany
	@JoinTable(name = "OneToMany_USER_VEHICLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "VEHICLE_ID"))
	// its optional using for name configuration of the join table
	private Collection<OneToManyVehicle> vehicle = new ArrayList<OneToManyVehicle>();

	public int getUserId() {
		return userId;
	}

	public Collection<OneToManyVehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Collection<OneToManyVehicle> vehicle) {
		this.vehicle = vehicle;
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
		return "OneToManyUser [userId=" + userId + ", userName=" + userName
				+ ", vehicle=" + vehicle + "]";
	}
	
	
}
