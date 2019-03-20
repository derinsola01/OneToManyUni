package com.luv2code.hibernate.demo;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		String[] someCourseTitles = { "Air Guitar - The Ultimate Guide",
		                             "the Pinball masterclass",
		                             "Derin's Masterclass...",
		                             "Learn to speak Yoruba"};
				
		
		try {
			session.beginTransaction();
			int instructorId = 1;
			int courseId = 11;
//			Instructor instructor = session.get(Instructor.class, theId);
			
//			ArrayList<Course> courseLoader = addCourses(someCourseTitles);
//			for (Course course : courseLoader)
//				instructor.add(course);
//			
//			for (Course course : courseLoader)
//				session.save(course);
			
			// to display the courses.
			getInstructorCourses(session, instructorId);
			
			// to delete a course
//			deleteCourse(session, courseId);
			
			session.getTransaction().commit();
			
			
			
		} finally {
			session.close();
			factory.close();
		}
	}
	
	public static ArrayList<Course> addCourses(String[] courseTitles) {
		
		ArrayList<Course> newCourses = new ArrayList<Course>();
		for (String title : courseTitles) {
			Course tempCourse1 = new Course(title);
			newCourses.add(tempCourse1);
		}
		return newCourses;
	}
	
	public static void getInstructorCourses(Session session, int id) {	
		Instructor instructor = session.get(Instructor.class, id);
		System.out.println("Derin's Debugger: The instructor is: " + instructor);
		System.out.println("Derin's Debugger: The instructorDetail are: " + instructor.getInstructorDetail());
		System.out.println("Derin's Debugger: The instructor Courses before closing session are: " + instructor.getCourses());
		
		session.close();
		System.out.println("Derin's Debugger: The instructor Courses after closing session are: " + instructor.getCourses());
	}
	
	public static void deleteCourse(Session session, int id) {	
		Course course = session.get(Course.class, id);
		session.delete(course);
	}
	

}
