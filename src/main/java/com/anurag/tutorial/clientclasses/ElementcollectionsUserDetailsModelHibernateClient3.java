package com.anurag.tutorial.clientclasses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.anurag.tutorial.model.Address;
import com.anurag.tutorial.model.ElementcollectionsUserDetails3;
import com.anurag.tutorial.model.UserDetails;

/**
 * @author Anurag
 * 
 *   Concept:- This program show how hibernate use proxy object in case of loading another list of relations.
 *             proxy class load relational data when it require .
 *             it work only under the session if we close the session and try to get the relation you will get below
 *             lazi initilize exception.
 *              
 *
 */
public class ElementcollectionsUserDetailsModelHibernateClient3 {
	
	public static void main(String[] args) {
		ElementcollectionsUserDetails3 user = new ElementcollectionsUserDetails3();  // create user object
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
	         user = (ElementcollectionsUserDetails3) session.get(ElementcollectionsUserDetails3.class, 1); // retrieved the user from the database for particular user which user id = 2 this object it is proxy user object.
	         System.out.println(user.getLisOfAddresses().size());
	         
	         
	         session = sessionFactory.openSession(); // again create another session object  
	         user = null;
	         user = (ElementcollectionsUserDetails3) session.get(ElementcollectionsUserDetails3.class, 1); // retrieved the user from the database for particular user which user id = 2 this object it is proxy user object.
	         session.close();
	         System.out.println(user.getLisOfAddresses().size());
	    
	}

}
/*
Output:-

Hibernate: insert into USERS_DETAILS_1 (USER_NAME) values (?)
Hibernate: insert into USER_ADDRESS_1 (USER_ID, CITY_NAME, PIN_CODE, STATE_NAME, STREET_NAME) values (?, ?, ?, ?, ?)
Hibernate: insert into USER_ADDRESS_1 (USER_ID, CITY_NAME, PIN_CODE, STATE_NAME, STREET_NAME) values (?, ?, ?, ?, ?)
Hibernate: select elementcol0_.USER_ID as USER1_6_0_, elementcol0_.USER_NAME as USER2_6_0_ from USERS_DETAILS_1 elementcol0_ where elementcol0_.USER_ID=?
Hibernate: select lisofaddre0_.USER_ID as USER1_6_0_, lisofaddre0_.CITY_NAME as CITY2_0_, lisofaddre0_.PIN_CODE as PIN3_0_, lisofaddre0_.STATE_NAME as STATE4_0_, lisofaddre0_.STREET_NAME as STREET5_0_ from USER_ADDRESS_1 lisofaddre0_ where lisofaddre0_.USER_ID=?
2
Hibernate: select elementcol0_.USER_ID as USER1_6_0_, elementcol0_.USER_NAME as USER2_6_0_ from USERS_DETAILS_1 elementcol0_ where elementcol0_.USER_ID=?
Exception in thread "main" org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.anurag.tutorial.model.ElementcollectionsUserDetails3.lisOfAddresses, no session or session was closed
	at org.hibernate.collection.AbstractPersistentCollection.throwLazyInitializationException(AbstractPersistentCollection.java:383)
	at org.hibernate.collection.AbstractPersistentCollection.throwLazyInitializationExceptionIfNotConnected(AbstractPersistentCollection.java:375)
	at org.hibernate.collection.AbstractPersistentCollection.readSize(AbstractPersistentCollection.java:122)
	at org.hibernate.collection.PersistentBag.size(PersistentBag.java:248)
	at com.anurag.tutorial.clientclasses.ElementcollectionsUserDetailsModelHibernateClient3.main(ElementcollectionsUserDetailsModelHibernateClient3.java:57)

*/
