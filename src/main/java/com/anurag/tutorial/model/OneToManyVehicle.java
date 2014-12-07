package com.anurag.tutorial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OneToMany_VEHICLE")
public class OneToManyVehicle {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="VEHICLE_ID")
    private int vehicleId;
    
    @Column(name="VEHICLE_NAME")
    private String vehicleName;
    
        
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
    
	@Override
	public String toString() {
		return "OneToManyVehicle [vehicleId=" + vehicleId + ", vehicleName="
				+ vehicleName + "]";
	}
    
    
}
