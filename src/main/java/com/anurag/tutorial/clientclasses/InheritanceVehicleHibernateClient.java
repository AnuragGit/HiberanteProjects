package com.anurag.tutorial.clientclasses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.anurag.tutorial.model.FourWheeler;
import com.anurag.tutorial.model.InheritanceVehicle;
import com.anurag.tutorial.model.TwoWheeler;

public class InheritanceVehicleHibernateClient {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new AnnotationConfiguration()
				.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		InheritanceVehicle vehicle = new InheritanceVehicle();
		vehicle.setVehicleName("Car");

		TwoWheeler twoWheeler = new TwoWheeler();
		twoWheeler.setVehicleName("Bike");
		twoWheeler.setSteeringTwoWheeler("Bike Steering Handle");

		FourWheeler fourWheeler = new FourWheeler();
		fourWheeler.setVehicleName("Alto");
		fourWheeler.setSteeringFourWheeler("Alto Steering Wheel");

		session.save(vehicle);
		session.save(twoWheeler);
		session.save(fourWheeler);

		session.getTransaction().commit();
		session.close();
		
		session = sessionFactory.openSession();
		
		vehicle=(InheritanceVehicle) session.get(InheritanceVehicle.class, 1);
		System.out.println(vehicle);
		
		twoWheeler=(TwoWheeler) session.get(TwoWheeler.class, 2);
		System.out.println(twoWheeler);
		
		fourWheeler=(FourWheeler) session.get(FourWheeler.class, 3);
		System.out.println(fourWheeler);
		
		
	}
}

/*
Ouptup:-

Hibernate: insert into InheritanceVehicle  (VEHICLE_NAME, VEHICLE_TYPE) values (?, 'InheritanceVehicle')
Hibernate: insert into InheritanceVehicle  (VEHICLE_NAME, STEERING_TYPE, VEHICLE_TYPE) values (?, ?, 'Bike')
Hibernate: insert into InheritanceVehicle  (VEHICLE_NAME, STEERING_TYPE, VEHICLE_TYPE) values (?, ?, 'Car')
Hibernate: select inheritanc0_.VEHICLE_ID as VEHICLE2_20_0_, inheritanc0_.VEHICLE_NAME as VEHICLE3_20_0_, inheritanc0_.STEERING_TYPE as STEERING4_20_0_, inheritanc0_.VEHICLE_TYPE as VEHICLE1_20_0_ from InheritanceVehicle  inheritanc0_ where inheritanc0_.VEHICLE_ID=?
InheritanceVehicle [vehicleId=1, vehicleName=Car]
Hibernate: select twowheeler0_.VEHICLE_ID as VEHICLE2_20_0_, twowheeler0_.VEHICLE_NAME as VEHICLE3_20_0_, twowheeler0_.STEERING_TYPE as STEERING4_20_0_ from InheritanceVehicle  twowheeler0_ where twowheeler0_.VEHICLE_ID=? and twowheeler0_.VEHICLE_TYPE='Bike'
TwoWheeler [steeringTwoWheeler=Bike Steering Handle, toString()=InheritanceVehicle [vehicleId=2, vehicleName=Bike]]
Hibernate: select fourwheele0_.VEHICLE_ID as VEHICLE2_20_0_, fourwheele0_.VEHICLE_NAME as VEHICLE3_20_0_, fourwheele0_.STEERING_TYPE as STEERING4_20_0_ from InheritanceVehicle  fourwheele0_ where fourwheele0_.VEHICLE_ID=? and fourwheele0_.VEHICLE_TYPE='Car'
FourWheeler [steeringFourWheeler=Alto Steering Wheel, toString()=InheritanceVehicle [vehicleId=3, vehicleName=Alto]]

*/