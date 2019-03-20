package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;

public class BiDirectionDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			int theId = 2;
			System.out.println("Search about to begin instructorDetail: ");
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			System.out.println("Found instructorDetail: " + tempInstructorDetail);
			System.out.println("For Instructor: " + tempInstructorDetail.getInstructor());
			session.getTransaction().commit();
		} 
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
