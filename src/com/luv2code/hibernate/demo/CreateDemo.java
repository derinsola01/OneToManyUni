package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			Instructor tempInstructor1 = new Instructor("Koushik", "Kokathal", "javabrains.com");
			InstructorDetail tempIntructorDetails1 = new InstructorDetail("http://javaBrains.com/youtube", "Fishing");
			Instructor tempInstructor2 = new Instructor("Chad", "Darby", "luv2code.com");
			InstructorDetail tempIntructorDetails2 = new InstructorDetail("http://luv2code.com/youtube", "Loves to code!");
			tempInstructor1.setInstructorDetail(tempIntructorDetails1);
			tempInstructor2.setInstructorDetail(tempIntructorDetails2);
			session.beginTransaction();
			session.save(tempInstructor1);
			session.save(tempInstructor2);
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
