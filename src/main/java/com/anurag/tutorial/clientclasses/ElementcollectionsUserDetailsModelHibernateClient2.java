package com.anurag.tutorial.clientclasses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.anurag.tutorial.model.Address;
import com.anurag.tutorial.model.ElementcollectionsUserDetails2;

public class ElementcollectionsUserDetailsModelHibernateClient2 {
	public static void main(String[] args) {
		ElementcollectionsUserDetails2 user = new ElementcollectionsUserDetails2();//Create user object
        user.setUserName("Anurag"); //Set user name
       
        Address address1 = new Address(); // create first embedded object address
        address1.setStreet("First Street");
        address1.setCity("First City");
        address1.setState("First State");
        address1.setPincode("First Pin");
       
        Address address2 = new Address(); // create second embedded object address
        address2.setStreet("Second Street");
        address2.setCity("Second City");
        address2.setState("Second State");
        address2.setPincode("Second Pin");
        //adding addresses object to the list of address
        user.getLisOfAddresses().add(address1);
        user.getLisOfAddresses().add(address2);
       
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory(); //create session factory object
        Session session = sessionFactory.openSession(); //create session object from the session factory
        session.beginTransaction(); //initialize the transaction object from session
        session.save(user); // save the user
        session.getTransaction().commit(); //commit the transaction 
        session.close(); //closing session
        
        
        user = null;  //Now getting a user object from database table from session object
    	session = sessionFactory.openSession(); //Creating a new session object for fetching user object
        session.beginTransaction(); //Again Open the transaction of the session object
        
        user = (ElementcollectionsUserDetails2) session.get(ElementcollectionsUserDetails2.class, 1); //we get user object from session object using method session.get(Class arg1, Serializable arg2) here arg2 is primary key or id of the fetching object and arg1 is the what the model object we want to retrieve from database.
        System.out.println(user);
	}

}
/*
Output:-

Hibernate: insert into USERS_DETAILS (USER_NAME) values (?)
Hibernate: insert into USER_ADDRESS (USER_ID, CITY_NAME, PIN_CODE, STATE_NAME, STREET_NAME) values (?, ?, ?, ?, ?)
Hibernate: insert into USER_ADDRESS (USER_ID, CITY_NAME, PIN_CODE, STATE_NAME, STREET_NAME) values (?, ?, ?, ?, ?)
Hibernate: select elementcol0_.USER_ID as USER1_5_0_, elementcol0_.USER_NAME as USER2_5_0_ from USERS_DETAILS elementcol0_ where elementcol0_.USER_ID=?
Hibernate: select lisofaddre0_.USER_ID as USER1_5_0_, lisofaddre0_.CITY_NAME as CITY2_0_, lisofaddre0_.PIN_CODE as PIN3_0_, lisofaddre0_.STATE_NAME as STATE4_0_, lisofaddre0_.STREET_NAME as STREET5_0_ from USER_ADDRESS lisofaddre0_ where lisofaddre0_.USER_ID=?
ElementcollectionsUserDetails2 [userId=1, userName=Anurag, lisOfAddresses=[ {Street: First Street City: First City State: First State Pincode: First Pin },  {Street: Second Street City: Second City State: Second State Pincode: Second Pin }]]

*/