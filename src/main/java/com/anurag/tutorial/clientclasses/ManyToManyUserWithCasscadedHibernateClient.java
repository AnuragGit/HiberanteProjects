package com.anurag.tutorial.clientclasses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.anurag.tutorial.model.ManyToManyUserWithCasscaded;
import com.anurag.tutorial.model.ManyToManyVehicleWithCasscaded;

public class ManyToManyUserWithCasscadedHibernateClient {
	public static void main(String[] args) {
		 ManyToManyUserWithCasscaded user = new  ManyToManyUserWithCasscaded();  
		 ManyToManyUserWithCasscaded user2 = new  ManyToManyUserWithCasscaded();  
          
		 ManyToManyVehicleWithCasscaded vehicle = new ManyToManyVehicleWithCasscaded();  
		 ManyToManyVehicleWithCasscaded vehicle2 = new ManyToManyVehicleWithCasscaded();  
          
        vehicle.setVehicleName("Car");  
        vehicle.getUser().add(user);  
        vehicle.getUser().add(user2);  
          
        vehicle2.setVehicleName("Jeep");  
        vehicle2.getUser().add(user2);  
        vehicle2.getUser().add(user);  
          
        user.setUserName("First User");  
        user2.setUserName("Second User");  
        user.getVehicle().add(vehicle);  
        user.getVehicle().add(vehicle2);  
        user2.getVehicle().add(vehicle);  
        user2.getVehicle().add(vehicle2);  
          
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();  
        Session session = sessionFactory.openSession();  
        session.beginTransaction();          
        session.save(user);  
        session.save(user2);  
        session.getTransaction().commit();  
        session.close();  
        
        session = sessionFactory.openSession();  
        System.out.println("===================Retriving protions===========================");
        
        user=(ManyToManyUserWithCasscaded) session.get(ManyToManyUserWithCasscaded.class, 1);
        System.out.println(user);
        user=(ManyToManyUserWithCasscaded) session.get(ManyToManyUserWithCasscaded.class, 2);
        System.out.println("==================================================");
        System.out.println(user);
	}
}

/*
Output:-

Hibernate: insert into ManyToManyUserWithCasscaded (USER_NAME) values (?)
Hibernate: insert into ManyToManyVehicleWithCasscaded (VEHICLE_NAME) values (?)
Hibernate: insert into ManyToManyVehicleWithCasscaded (VEHICLE_NAME) values (?)
Hibernate: insert into ManyToManyUserWithCasscaded (USER_NAME) values (?)
Hibernate: insert into Casscaded_MANYUSER_MANYVEHICLE (USER_ID, VEHICLE_ID) values (?, ?)
Hibernate: insert into Casscaded_MANYUSER_MANYVEHICLE (USER_ID, VEHICLE_ID) values (?, ?)
Hibernate: insert into Casscaded_MANYUSER_MANYVEHICLE (USER_ID, VEHICLE_ID) values (?, ?)
Hibernate: insert into Casscaded_MANYUSER_MANYVEHICLE (USER_ID, VEHICLE_ID) values (?, ?)
===================Retriving protions===========================
Hibernate: select manytomany0_.USER_ID as USER1_18_0_, manytomany0_.USER_NAME as USER2_18_0_ from ManyToManyUserWithCasscaded manytomany0_ where manytomany0_.USER_ID=?
Hibernate: select vehicle0_.USER_ID as USER1_18_1_, vehicle0_.VEHICLE_ID as VEHICLE2_1_, manytomany1_.VEHICLE_ID as VEHICLE1_19_0_, manytomany1_.VEHICLE_NAME as VEHICLE2_19_0_ from Casscaded_MANYUSER_MANYVEHICLE vehicle0_ inner join ManyToManyVehicleWithCasscaded manytomany1_ on vehicle0_.VEHICLE_ID=manytomany1_.VEHICLE_ID where vehicle0_.USER_ID=?
ManyToManyUser [userId=1, userName=First User, vehicle=[ManyToManyVehicle [vehicleId=1, vehicleName=Car], ManyToManyVehicle [vehicleId=2, vehicleName=Jeep]]]
Hibernate: select manytomany0_.USER_ID as USER1_18_0_, manytomany0_.USER_NAME as USER2_18_0_ from ManyToManyUserWithCasscaded manytomany0_ where manytomany0_.USER_ID=?
==================================================
Hibernate: select vehicle0_.USER_ID as USER1_18_1_, vehicle0_.VEHICLE_ID as VEHICLE2_1_, manytomany1_.VEHICLE_ID as VEHICLE1_19_0_, manytomany1_.VEHICLE_NAME as VEHICLE2_19_0_ from Casscaded_MANYUSER_MANYVEHICLE vehicle0_ inner join ManyToManyVehicleWithCasscaded manytomany1_ on vehicle0_.VEHICLE_ID=manytomany1_.VEHICLE_ID where vehicle0_.USER_ID=?
ManyToManyUser [userId=2, userName=Second User, vehicle=[ManyToManyVehicle [vehicleId=1, vehicleName=Car], ManyToManyVehicle [vehicleId=2, vehicleName=Jeep]]]

*/