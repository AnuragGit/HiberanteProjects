package com.anurag.tutorial.clientclasses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.anurag.tutorial.model.Address;
import com.anurag.tutorial.model.ElementcollectionsUserDetails4;

/**
 * @author Anurag
 * 
 *     Concept:- This program showing when we using fetch type eager then it will fetch all record in
 *               one shot include with relation in one select query. 
 *
 */
public class ElementcollectionsUserDetailsModelHibernateClient4 {

	public static void main(String[] args) {
		ElementcollectionsUserDetails4 user = new ElementcollectionsUserDetails4();  // create user object
	        //user.setUserId(1);
	        user.setUserName("Dinesh Rajput");
	        
	        Address address1 = new Address(); // create address object
	        address1.setStreet("First Street");
	        address1.setCity("First City");
	        address1.setState("First State");
	        address1.setPincode("First Pin");
	        
	        Address address2 = new Address(); // create another address object
	        address2.setStreet("Second Street");
	        address2.setCity("Second City");
	        address2.setState("Second State");
	        address2.setPincode("Second Pin");
	        
	        user.getLisOfAddresses().add(address1); // set the addresses objects to list of the addresses
	        user.getLisOfAddresses().add(address2);
	        
	        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory(); // create session factory object
	        Session session = sessionFactory.openSession(); // create session object
	        session.beginTransaction(); // start transaction object
	        session.save(user); // save the user to database 
	        session.getTransaction().commit(); // commit the transaction
	        session.close(); // closing session
	        
	         session = sessionFactory.openSession(); // again create another session object  
	         user = null;
	         user = (ElementcollectionsUserDetails4) session.get(ElementcollectionsUserDetails4.class, 1); // retrieved the user from the database for particular user which user id = 2 this object it is proxy user object.
	         System.out.println(user.getLisOfAddresses().size());
	         
	         
	         session = sessionFactory.openSession(); // again create another session object  
	         user = null;
	         user = (ElementcollectionsUserDetails4) session.get(ElementcollectionsUserDetails4.class, 1); // retrieved the user from the database for particular user which user id = 2 this object it is proxy user object.
	         session.close();
	         System.out.println(user.getLisOfAddresses().size());
	    
	}
}
/*
Output:-

Hibernate: insert into USERS_DETAILS_2 (USER_NAME) values (?)
Hibernate: insert into USER_ADDRESS_2 (USER_ID, CITY_NAME, PIN_CODE, STATE_NAME, STREET_NAME) values (?, ?, ?, ?, ?)
Hibernate: insert into USER_ADDRESS_2 (USER_ID, CITY_NAME, PIN_CODE, STATE_NAME, STREET_NAME) values (?, ?, ?, ?, ?)
Hibernate: select elementcol0_.USER_ID as USER1_7_0_, elementcol0_.USER_NAME as USER2_7_0_, lisofaddre1_.USER_ID as USER1_7_2_, lisofaddre1_.CITY_NAME as CITY2_2_, lisofaddre1_.PIN_CODE as PIN3_2_, lisofaddre1_.STATE_NAME as STATE4_2_, lisofaddre1_.STREET_NAME as STREET5_2_ from USERS_DETAILS_2 elementcol0_ left outer join USER_ADDRESS_2 lisofaddre1_ on elementcol0_.USER_ID=lisofaddre1_.USER_ID where elementcol0_.USER_ID=?
2
Hibernate: select elementcol0_.USER_ID as USER1_7_0_, elementcol0_.USER_NAME as USER2_7_0_, lisofaddre1_.USER_ID as USER1_7_2_, lisofaddre1_.CITY_NAME as CITY2_2_, lisofaddre1_.PIN_CODE as PIN3_2_, lisofaddre1_.STATE_NAME as STATE4_2_, lisofaddre1_.STREET_NAME as STREET5_2_ from USERS_DETAILS_2 elementcol0_ left outer join USER_ADDRESS_2 lisofaddre1_ on elementcol0_.USER_ID=lisofaddre1_.USER_ID where elementcol0_.USER_ID=?
2
*/