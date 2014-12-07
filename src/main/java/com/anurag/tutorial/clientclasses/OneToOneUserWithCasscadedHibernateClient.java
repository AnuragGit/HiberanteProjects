package com.anurag.tutorial.clientclasses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.anurag.tutorial.model.OneToOneUserWithCasscaded;
import com.anurag.tutorial.model.OneToOneVehicalMapping;

public class OneToOneUserWithCasscadedHibernateClient {
	public static void main(String[] args) {
		OneToOneUserWithCasscaded user = new OneToOneUserWithCasscaded(); //create the user entity
		OneToOneVehicalMapping vehicle = new OneToOneVehicalMapping(); //create the vehicle entity
        
        vehicle.setVehicleName("BMW Car"); //set vehicle name
        user.setUserId(1);
        user.setUserName("Anurag "); //set the user name
        user.setVehicle(vehicle); //set the vehicle entity to the field of the user entity i.e. vehicle entity inside the user entity
        
        
        
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory(); //create session factory object
        Session session = sessionFactory.openSession(); //create the session object
        session.beginTransaction();//create the transaction from the session object
        
        session.save(user); // save the user entity to the database
        
        session.getTransaction().commit(); //close the transaction
        session.close(); //close the session
        System.out.println("=========== =======");
        session = sessionFactory.openSession(); 
        session.beginTransaction();
        user=new OneToOneUserWithCasscaded();
        vehicle= new OneToOneVehicalMapping();
        vehicle.setVehicleName("Normal Car"); //set vehicle name
        user.setUserId(2);
        user.setUserName("Amar "); //set the user name
       
        user.setVehicle(vehicle); //set the vehicle entity to the field of the user entity i.e. vehicle entity inside the user entity
        //vehicle.setUser(user);
        session.save(user); // save the user entity to the database
        session.getTransaction().commit(); //close the transaction
        session.close(); //close the session
        
        
        System.out.println("=============Fetching process==================");
        session = sessionFactory.openSession(); // again create another session object  
        user = null;
        user = (OneToOneUserWithCasscaded) session.get(OneToOneUserWithCasscaded.class, 1); // retrieved the user from the database for particular user which user id = 2 this object it is proxy user object.
        System.out.println(user);
        user = null;
        user = (OneToOneUserWithCasscaded) session.get(OneToOneUserWithCasscaded.class, 2); // retrieved the user from the database for particular user which user id = 2 this object it is proxy user object.
        System.out.println(user);
        session.close();
	}
}

/*
Output:-

Hibernate: insert into OneToOneVehicle (VEHICLE_NAME) values (?)
Hibernate: insert into OneToOneUserWithCasscaded (USER_NAME, VEHICLE_ID) values (?, ?)
=========== =======
Hibernate: insert into OneToOneVehicle (VEHICLE_NAME) values (?)
Hibernate: insert into OneToOneUserWithCasscaded (USER_NAME, VEHICLE_ID) values (?, ?)
=============Fetching process==================
Hibernate: select onetooneus0_.USER_ID as USER1_16_0_, onetooneus0_.USER_NAME as USER2_16_0_, onetooneus0_.VEHICLE_ID as VEHICLE3_16_0_ from OneToOneUserWithCasscaded onetooneus0_ where onetooneus0_.USER_ID=?
Hibernate: select onetooneve0_.VEHICLE_ID as VEHICLE1_9_0_, onetooneve0_.VEHICLE_NAME as VEHICLE2_9_0_ from OneToOneVehicle onetooneve0_ where onetooneve0_.VEHICLE_ID=?
OneToOneUserMapping [userId=1, userName=Anurag , vehicle=OneToOneVehicalMapping [vehicleId=1, vehicleName=BMW Car]]
Hibernate: select onetooneus0_.USER_ID as USER1_16_0_, onetooneus0_.USER_NAME as USER2_16_0_, onetooneus0_.VEHICLE_ID as VEHICLE3_16_0_ from OneToOneUserWithCasscaded onetooneus0_ where onetooneus0_.USER_ID=?
Hibernate: select onetooneve0_.VEHICLE_ID as VEHICLE1_9_0_, onetooneve0_.VEHICLE_NAME as VEHICLE2_9_0_ from OneToOneVehicle onetooneve0_ where onetooneve0_.VEHICLE_ID=?
OneToOneUserMapping [userId=2, userName=Amar , vehicle=OneToOneVehicalMapping [vehicleId=2, vehicleName=Normal Car]]

*/