package com.anurag.tutorial.clientclasses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.anurag.tutorial.model.ManyToOneUser;
import com.anurag.tutorial.model.ManyToOneVehicle;

public class ManyToOneUserMappingHibernateClient {

	public static void main(String[] args) {
		ManyToOneUser user = new ManyToOneUser(); // create an user entity

		ManyToOneVehicle vehicle = new ManyToOneVehicle(); // create a vehicle
															// entity
		ManyToOneVehicle vehicle2 = new ManyToOneVehicle(); // create second
															// vehicle entity

		vehicle.setVehicleName("BMW Car"); // set BMW car
		vehicle.setUser(user); // set user for that car

		vehicle2.setVehicleName("AUDI Car"); // set second car Audi
		vehicle2.setUser(user);// set user for that car

		user.setUserName("Anurag"); // set user property

		SessionFactory sessionFactory = new AnnotationConfiguration()
				.configure().buildSessionFactory(); // create the session
													// factory object
		Session session = sessionFactory.openSession(); // create the session
														// object
		session.beginTransaction(); // create the transaction object
		session.save(user);
		session.save(vehicle);
		session.save(vehicle2);
		
		session.getTransaction().commit();
		session.close();


		System.out.println("==================== Saving first vehicle then two update query will be fire ===========================");
		
		session = sessionFactory.openSession();
		user = new ManyToOneUser();
		vehicle = new ManyToOneVehicle(); // create a vehicle entity
		vehicle2 = new ManyToOneVehicle(); // create second vehicle entity

		vehicle.setVehicleName("Best Car"); // set BMW car
		vehicle.setUser(user); // set user for that car

		vehicle2.setVehicleName("Special Car"); // set second car Audi
		vehicle2.setUser(user);// set user for that car

		user.setUserName("Mohan"); // set user property
		session.beginTransaction();
		session.save(vehicle);
		session.save(vehicle2);
		session.save(user);
		session.getTransaction().commit();
		session.close();

		System.out.println("==================Retriving the Record========================");
		session = sessionFactory.openSession();
		user = (ManyToOneUser) session.get(ManyToOneUser.class, 1);
		System.out.println(user);
		
		
		user = (ManyToOneUser) session.get(ManyToOneUser.class, 2);
		System.out.println(user);
		

	}
}
/*
Output:-

Hibernate: insert into ManyToOneUser (USER_NAME) values (?)
Hibernate: insert into ManyToOneVehicle (USER_ID, VEHICLE_NAME) values (?, ?)
Hibernate: insert into ManyToOneVehicle (USER_ID, VEHICLE_NAME) values (?, ?)
==================== Saving first vehicle then two update query will be fire ===========================
Hibernate: insert into ManyToOneVehicle (USER_ID, VEHICLE_NAME) values (?, ?)
Hibernate: insert into ManyToOneVehicle (USER_ID, VEHICLE_NAME) values (?, ?)
Hibernate: insert into ManyToOneUser (USER_NAME) values (?)
Hibernate: update ManyToOneVehicle set USER_ID=?, VEHICLE_NAME=? where VEHICLE_ID=?
Hibernate: update ManyToOneVehicle set USER_ID=?, VEHICLE_NAME=? where VEHICLE_ID=?
==================Retriving the Record========================
Hibernate: select manytooneu0_.USER_ID as USER1_12_0_, manytooneu0_.USER_NAME as USER2_12_0_ from ManyToOneUser manytooneu0_ where manytooneu0_.USER_ID=?
Hibernate: select vehicle0_.USER_ID as USER3_12_1_, vehicle0_.VEHICLE_ID as VEHICLE1_1_, vehicle0_.VEHICLE_ID as VEHICLE1_13_0_, vehicle0_.USER_ID as USER3_13_0_, vehicle0_.VEHICLE_NAME as VEHICLE2_13_0_ from ManyToOneVehicle vehicle0_ where vehicle0_.USER_ID=?
ManyToOneUser [userId=1, userName=Anurag, vehicle=[ManyToOneVehicle [vehicleId=1, vehicleName=BMW Car], ManyToOneVehicle [vehicleId=2, vehicleName=AUDI Car]]]
Hibernate: select manytooneu0_.USER_ID as USER1_12_0_, manytooneu0_.USER_NAME as USER2_12_0_ from ManyToOneUser manytooneu0_ where manytooneu0_.USER_ID=?
Hibernate: select vehicle0_.USER_ID as USER3_12_1_, vehicle0_.VEHICLE_ID as VEHICLE1_1_, vehicle0_.VEHICLE_ID as VEHICLE1_13_0_, vehicle0_.USER_ID as USER3_13_0_, vehicle0_.VEHICLE_NAME as VEHICLE2_13_0_ from ManyToOneVehicle vehicle0_ where vehicle0_.USER_ID=?
ManyToOneUser [userId=2, userName=Mohan, vehicle=[ManyToOneVehicle [vehicleId=3, vehicleName=Best Car], ManyToOneVehicle [vehicleId=4, vehicleName=Special Car]]]

*/