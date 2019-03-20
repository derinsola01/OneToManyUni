package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;
import com.luv2code.hibernate.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.addAnnotatedClass(Review.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			Course tempCourse = new Course("PacMan - How to score a million points");
			tempCourse.addReview(new Review("Great course ... loved every second!"));
			tempCourse.addReview(new Review("It was an okay course."));
			tempCourse.addReview(new Review("Cool!"));
			tempCourse.addReview(new Review("I wasted money and time on that course, hoping for a refund!"));
			
			List<Course> courseList = new ArrayList<Course>();
			courseList.add(tempCourse);
			
			Instructor tempInstructor = new Instructor("Derin", "Gbadebo", "derin@derinworld.com");
			InstructorDetail tempIntructorDetails = new InstructorDetail("http://javaBrains.com/youtube", "Walking");
			
			tempInstructor.setCourses(courseList);
			tempInstructor.setInstructorDetail(tempIntructorDetails);
			tempCourse.setInstructor(tempInstructor);
			
			
			session.save(tempInstructor);
			session.save(tempCourse);
			
			
			session.getTransaction().commit();
			
		} finally {
			session.close();
			factory.close();
		}
	}
}
