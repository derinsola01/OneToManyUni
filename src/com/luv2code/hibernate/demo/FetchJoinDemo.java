package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
				
		
		try {
			session.beginTransaction();
			int instructorId = 1;
			
			Query<Instructor> query = session.createQuery("select i from Instructor i "
					+ "JOIN FETCH i.courses "
					+ "where i.id=:theInstructorId", Instructor.class);

			query.setParameter("theInstructorId", instructorId);
			
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("The Instructor obtained from HQL is: " + tempInstructor);
			
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}
}
