package com.anurag.tutorial.clientclasses;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.anurag.tutorial.model.Address;
import com.anurag.tutorial.model.EmbededUserDetails;
import com.anurag.tutorial.model.UserDetails;

public class EmbededUserDetailsModelHibernateClient {
	public static void main(String[] args) {
		EmbededUserDetails user1 = new EmbededUserDetails(); // create first
																// user
		EmbededUserDetails user2 = new EmbededUserDetails(); // create second
																// user

		user1.setUserName("Anurg");
		user2.setUserName("Ajay");

		Address address1 = new Address(); // create first value type object for
											// entity type object user1
		address1.setStreet("K Block House No. 403");
		address1.setCity("Mangol Puri");
		address1.setState("New Delhi");
		address1.setPincode("110083");
		user1.setAddress(address1);

		Address address2 = new Address();// create second value type object for
											// entity type object user2
		address2.setStreet("Film City");
		address2.setCity("Noida");
		address2.setState("UP");
		address2.setPincode("201301");
		user2.setAddress(address2);

		user1.setDob(new Date());
		user1.setPhone("+91-9953423462");

		user2.setDob(new Date());
		user2.setPhone("+91-9973423462");

		SessionFactory sessionFactory = new AnnotationConfiguration()
				.configure().buildSessionFactory(); // create a session factory
													// object
		Session session = sessionFactory.openSession(); // create session object
														// from session factory
		session.beginTransaction(); // begin transaction for this session

		session.save(user1); // save the first user
		session.save(user2); // save the second user

		session.getTransaction().commit(); // commit the transaction the session
		session.close(); // close the session
		
		
		
		user1 = null;  //Now getting a user object from database table from session object
        user2=null;
		session = sessionFactory.openSession(); //Creating a new session object for fetching user object
        session.beginTransaction(); //Again Open the transaction of the session object
        
        user1 = (EmbededUserDetails) session.get(EmbededUserDetails.class, 1); //we get user object from session object using method session.get(Class arg1, Serializable arg2) here arg2 is primary key or id of the fetching object and arg1 is the what the model object we want to retrieve from database.
        user2 = (EmbededUserDetails) session.get(EmbededUserDetails.class, 2);
        System.out.println(user1);
        System.out.println("===================================");
        System.out.println(user2);
	}

}
/*
Output:-


Hibernate: insert into USER_TABLE (CITY_NAME, PIN_CODE, STATE_NAME, STREET_NAME, DOB, USER_PHONE, USER_NAME) values (?, ?, ?, ?, ?, ?, ?)
Hibernate: insert into USER_TABLE (CITY_NAME, PIN_CODE, STATE_NAME, STREET_NAME, DOB, USER_PHONE, USER_NAME) values (?, ?, ?, ?, ?, ?, ?)
Hibernate: select embededuse0_.USER_ID as USER1_2_0_, embededuse0_.CITY_NAME as CITY2_2_0_, embededuse0_.PIN_CODE as PIN3_2_0_, embededuse0_.STATE_NAME as STATE4_2_0_, embededuse0_.STREET_NAME as STREET5_2_0_, embededuse0_.DOB as DOB2_0_, embededuse0_.USER_PHONE as USER7_2_0_, embededuse0_.USER_NAME as USER8_2_0_ from USER_TABLE embededuse0_ where embededuse0_.USER_ID=?
Hibernate: select embededuse0_.USER_ID as USER1_2_0_, embededuse0_.CITY_NAME as CITY2_2_0_, embededuse0_.PIN_CODE as PIN3_2_0_, embededuse0_.STATE_NAME as STATE4_2_0_, embededuse0_.STREET_NAME as STREET5_2_0_, embededuse0_.DOB as DOB2_0_, embededuse0_.USER_PHONE as USER7_2_0_, embededuse0_.USER_NAME as USER8_2_0_ from USER_TABLE embededuse0_ where embededuse0_.USER_ID=?
[User Name: AnurgUser Id: 1 User Address  {Street: K Block House No. 403 City: Mangol Puri State: New Delhi Pincode: 110083 } Use phone +91-9953423462 ]
===================================
[User Name: AjayUser Id: 2 User Address  {Street: Film City City: Noida State: UP Pincode: 201301 } Use phone +91-9973423462 ]

*/