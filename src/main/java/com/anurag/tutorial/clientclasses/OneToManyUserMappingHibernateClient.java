package com.anurag.tutorial.clientclasses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.anurag.tutorial.model.OneToManyUser;
import com.anurag.tutorial.model.OneToManyVehicle;

public class OneToManyUserMappingHibernateClient {
    public static void main(String[] args) {
    	OneToManyUser user = new OneToManyUser(); //create the user entity object
        
    	OneToManyVehicle vehicle = new OneToManyVehicle(); //create the first vehicle entity object
    	OneToManyVehicle vehicle2 = new OneToManyVehicle(); //create the second vehicle entity
       
        vehicle.setVehicleName("BMW Car"); //set the value to the vehicle entity
        vehicle2.setVehicleName("AUDI Car");
       
        user.setUserName("Anurag Sonit"); //Set the value to the user entity
        user.getVehicle().add(vehicle); //add vehicle to the list of the vehicle
        user.getVehicle().add(vehicle2);
       
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory(); //create session factory object
        Session session = sessionFactory.openSession(); //create the session object
        session.beginTransaction(); //start the transaction of the session object
       
        session.save(vehicle); //saving the vehicle to the database
        session.save(vehicle2);
        session.save(user); //save the user to the database
        
        session.getTransaction().commit(); //close the transaction
        session.close(); //close the session
        
        
        System.out.println("=====================================================");
        session = sessionFactory.openSession();
        user=(OneToManyUser) session.get(OneToManyUser.class, 1);
        System.out.println(user.getUserName());
	}		
}

/*
Output:-

Hibernate: insert into OneToMany_VEHICLE (VEHICLE_NAME) values (?)
Hibernate: insert into OneToMany_VEHICLE (VEHICLE_NAME) values (?)
Hibernate: insert into OneToMany_USER (USER_NAME) values (?)
Hibernate: insert into USER_VEHICLE (USER_ID, VEHICLE_ID) values (?, ?)
Hibernate: insert into USER_VEHICLE (USER_ID, VEHICLE_ID) values (?, ?)
=====================================================
Hibernate: select onetomanyu0_.USER_ID as USER1_10_0_, onetomanyu0_.USER_NAME as USER2_10_0_ from OneToMany_USER onetomanyu0_ where onetomanyu0_.USER_ID=?
Anurag Sonit

*/
