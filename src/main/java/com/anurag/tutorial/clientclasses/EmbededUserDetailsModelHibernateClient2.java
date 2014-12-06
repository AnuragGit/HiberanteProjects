package com.anurag.tutorial.clientclasses;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.anurag.tutorial.model.Address;
import com.anurag.tutorial.model.EmbededUserDetails;
import com.anurag.tutorial.model.EmbededUserDetails2;

public class EmbededUserDetailsModelHibernateClient2 {

public static void main(String[] args) {
	EmbededUserDetails2 user = new EmbededUserDetails2(); //create an user is entity type object
    //user.setUserId(1);
    user.setUserName("Anurag");
    
    Address address = new Address(); //create an value type object of address class for home address
    address.setStreet("Block House No");
    address.setCity("MangolaPuri");
    address.setState("New Delhi");
    address.setPincode("110089");
    user.setHomeAddress(address); //set the home address
    
    Address address1 = new Address();//create another value type object for the permanent address
    address1.setStreet("Film City");
    address1.setCity("Noida");
    address1.setState("UP");
    address1.setPincode("201301");
    user.setPermanentAddress(address1);//set the permanent address
    
    user.setDob(new Date());
    user.setPhone("9999222211");
    
    SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory(); //create a session factory object
    Session session = sessionFactory.openSession(); //create a session object
    session.beginTransaction(); //transaction object start
    session.save(user); //  save the entity type object user to the database
    session.getTransaction().commit(); //commit the transaction object
    session.close(); //close the session
    
    
    user = null;  //Now getting a user object from database table from session object
	session = sessionFactory.openSession(); //Creating a new session object for fetching user object
    session.beginTransaction(); //Again Open the transaction of the session object
    
    user = (EmbededUserDetails2) session.get(EmbededUserDetails2.class, 1); //we get user object from session object using method session.get(Class arg1, Serializable arg2) here arg2 is primary key or id of the fetching object and arg1 is the what the model object we want to retrieve from database.
    System.out.println(user);
   }
}
/*
Output:-

Hibernate: insert into USER_TABLE_2 (HOME_CITY_NAME, HOME_PIN_CODE, HOME_STATE_NAME, HOME_STREET_NAME, DOB, CITY_NAME, PIN_CODE, STATE_NAME, STREET_NAME, Phone, USER_NAME) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
Hibernate: select embededuse0_.USER_ID as USER1_3_0_, embededuse0_.HOME_CITY_NAME as HOME2_3_0_, embededuse0_.HOME_PIN_CODE as HOME3_3_0_, embededuse0_.HOME_STATE_NAME as HOME4_3_0_, embededuse0_.HOME_STREET_NAME as HOME5_3_0_, embededuse0_.DOB as DOB3_0_, embededuse0_.CITY_NAME as CITY7_3_0_, embededuse0_.PIN_CODE as PIN8_3_0_, embededuse0_.STATE_NAME as STATE9_3_0_, embededuse0_.STREET_NAME as STREET10_3_0_, embededuse0_.Phone as Phone3_0_, embededuse0_.USER_NAME as USER12_3_0_ from USER_TABLE_2 embededuse0_ where embededuse0_.USER_ID=?
[User Name: Anurag
 Permanent Address:  {Street: Film City City: Noida State: UP Pincode: 201301 }
 Home Address:  {Street: Block House No City: MangolaPuri State: New Delhi Pincode: 110089 }
 Date of Birth: 2014-12-06 14:47:44.0
 Phone: 9999222211]

*/