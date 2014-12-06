package com.anurag.tutorial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OneToOneVehicle")
public class OneToOneVehicalMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VEHICLE_ID")
	private int vehicleId;

	@Column(name = "VEHICLE_NAME")
	private String vehicleName;
	
	/*@OneToOne(mappedBy="vehicle")
	private OneToOneUserMapping user;
*/
	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
	/*public OneToOneUserMapping getUser() {
		return user;
	}
	
	public void setUser(OneToOneUserMapping user) {
		this.user = user;
	}*/

	@Override
	public String toString() {
		return "OneToOneVehicalMapping [vehicleId=" + vehicleId
				+ ", vehicleName=" + vehicleName + "]";
	}
	
	
}
