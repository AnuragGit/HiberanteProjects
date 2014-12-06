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
		// Create the model object
		UserDetails user = new UserDetails();
		user.setUserId(1l);
		user.setUserName("Anurag");

		// Create Session Factory Object using Annotation Configuration
		SessionFactory sessionFactory = new AnnotationConfiguration()
				.configure().buildSessionFactory();

		// Create Session object from session factory object
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Use the session to save model objects
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
}
