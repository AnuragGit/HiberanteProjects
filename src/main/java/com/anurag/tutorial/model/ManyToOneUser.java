package com.anurag.tutorial.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * @author Anurag
 * 
 *               Concept:-
 *               
 *               @ManyToOne :-This mapping don't have mapped by things
 *               
 *               but @OneToMany mapping have mapped by things
 *
 */
@Entity
@Table(name = "ManyToOneUser")
public class ManyToOneUser {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;
	
	@OneToMany(mappedBy="user")
	private  List<ManyToOneVehicle> vehicle = new ArrayList<ManyToOneVehicle>();

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

	public List<ManyToOneVehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(List<ManyToOneVehicle> vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "ManyToOneUser [userId=" + userId + ", userName=" + userName
				+ ", vehicle=" + vehicle + "]";
	}
	
	
	

	

	
	
	
	
}
