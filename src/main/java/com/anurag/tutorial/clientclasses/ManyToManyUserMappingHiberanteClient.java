package com.anurag.tutorial.clientclasses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.anurag.tutorial.model.ManyToManyUser;
import com.anurag.tutorial.model.ManyToManyVehicle;

public class ManyToManyUserMappingHiberanteClient {
	public static void main(String[] args) {
			
		ManyToManyUser user = new ManyToManyUser();  
		ManyToManyUser user2 = new ManyToManyUser();  
          
		ManyToManyVehicle vehicle = new ManyToManyVehicle();  
		ManyToManyVehicle vehicle2 = new ManyToManyVehicle();  
          
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
        session.save(vehicle);  
        session.save(vehicle2);  
        session.save(user);  
        session.save(user2);  
        session.getTransaction().commit();  
        session.close();  
        
        session = sessionFactory.openSession();  
        System.out.println("===================Retriving protions===========================");
        
        user=(ManyToManyUser) session.get(ManyToManyUser.class, 1);
        System.out.println(user);
        user=(ManyToManyUser) session.get(ManyToManyUser.class, 2);
        System.out.println("==================================================");
        System.out.println(user);
        
        
        
	}
}
/*
Output:-

Hibernate: insert into ManyToManyVehicle (VEHICLE_NAME) values (?)
Hibernate: insert into ManyToManyVehicle (VEHICLE_NAME) values (?)
Hibernate: insert into ManyToManyUser (USER_NAME) values (?)
Hibernate: insert into ManyToManyUser (USER_NAME) values (?)
Hibernate: insert into MANYUSER_MANYVEHICLE (USER_ID, VEHICLE_ID) values (?, ?)
Hibernate: insert into MANYUSER_MANYVEHICLE (USER_ID, VEHICLE_ID) values (?, ?)
Hibernate: insert into MANYUSER_MANYVEHICLE (USER_ID, VEHICLE_ID) values (?, ?)
Hibernate: insert into MANYUSER_MANYVEHICLE (USER_ID, VEHICLE_ID) values (?, ?)
===================Retriving protions===========================
Hibernate: select manytomany0_.USER_ID as USER1_14_0_, manytomany0_.USER_NAME as USER2_14_0_ from ManyToManyUser manytomany0_ where manytomany0_.USER_ID=?
Hibernate: select vehicle0_.USER_ID as USER1_14_1_, vehicle0_.VEHICLE_ID as VEHICLE2_1_, manytomany1_.VEHICLE_ID as VEHICLE1_15_0_, manytomany1_.VEHICLE_NAME as VEHICLE2_15_0_ from MANYUSER_MANYVEHICLE vehicle0_ inner join ManyToManyVehicle manytomany1_ on vehicle0_.VEHICLE_ID=manytomany1_.VEHICLE_ID where vehicle0_.USER_ID=?
ManyToManyUser [userId=1, userName=First User, vehicle=[ManyToManyVehicle [vehicleId=1, vehicleName=Car], ManyToManyVehicle [vehicleId=2, vehicleName=Jeep]]]
Hibernate: select manytomany0_.USER_ID as USER1_14_0_, manytomany0_.USER_NAME as USER2_14_0_ from ManyToManyUser manytomany0_ where manytomany0_.USER_ID=?
==================================================
Hibernate: select vehicle0_.USER_ID as USER1_14_1_, vehicle0_.VEHICLE_ID as VEHICLE2_1_, manytomany1_.VEHICLE_ID as VEHICLE1_15_0_, manytomany1_.VEHICLE_NAME as VEHICLE2_15_0_ from MANYUSER_MANYVEHICLE vehicle0_ inner join ManyToManyVehicle manytomany1_ on vehicle0_.VEHICLE_ID=manytomany1_.VEHICLE_ID where vehicle0_.USER_ID=?
ManyToManyUser [userId=2, userName=Second User, vehicle=[ManyToManyVehicle [vehicleId=1, vehicleName=Car], ManyToManyVehicle [vehicleId=2, vehicleName=Jeep]]]
*/
