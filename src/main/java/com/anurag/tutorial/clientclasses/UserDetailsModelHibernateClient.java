package com.anurag.tutorial.clientclasses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.anurag.tutorial.model.UserDetails;

/**
 * @author Anurag
 * 
 *    Concept:-
 *    Using the Hibernate API
		Create a session factory
		create a session from the session factory
		Use the session to save model objects
 *
 */
public class UserDetailsModelHibernateClient {
	public static void main(String[] args) {
        UserDetails user = new UserDetails(); //Creating first user
        user.setUserId(1l);
        user.setUserName("Anurag Soni");
        
        UserDetails user2 = new UserDetails();//Creating second user
        user2.setUserId(2l);
        user2.setUserName("Virat Soni");
        
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory(); //Creating a session factory object
        Session session = sessionFactory.openSession(); //Creating a session object for inserting users  object to the database table USER_TABLE
        session.beginTransaction(); //Open the transaction of session object to do something
        
        session.save(user); //Inserting or Saving the first user object        session.save(user2);  //Inserting or Saving the second user object
        
        session.getTransaction().commit();//Close the transaction of session object after to do something
        session.close(); //Close the session object performing saving event to database
        
        user = null;  //Now getting a user object from database table from session object
        session = sessionFactory.openSession(); //Creating a new session object for fetching user object
        session.beginTransaction(); //Again Open the transaction of the session object
        
        user = (UserDetails) session.get(UserDetails.class, 1l); //we get user object from session object using method session.get(Class arg1, Serializable arg2) here arg2 is primary key or id of the fetching object and arg1 is the what the model object we want to retrieve from database.
        
        System.out.println(user);
        
        user = (UserDetails) session.get(UserDetails.class, 1l); //Retrieving object which we want to update
        user.setUserName("Prakash soni"); //Set the updated userName to the model field
        session.update(user); //Update to the database table
        session.getTransaction().commit();

        System.out.println("Updated User ->"+user);
    
	}
}

/*
Output:-

Hibernate: insert into UserDetails (userName, userId) values (?, ?)
Hibernate: select userdetail0_.userId as userId1_0_, userdetail0_.userName as userName1_0_ from UserDetails userdetail0_ where userdetail0_.userId=?
UserDetails [userId=1, userName=Anurag Soni]
Hibernate: update UserDetails set userName=? where userId=?
Updated User ->UserDetails [userId=1, userName=Prakash soni]

*/