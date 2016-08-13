package me.utlight.testHibernate;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import me.utlight.entity.Course;
import me.utlight.entity.Student;

public class testManyToMany {
	
	public static void main(String[] args) {
		//获取hibernate配置信息
		Configuration configuration = new Configuration().configure();
		//根据configuration建立sessionFactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//开启session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Set<Course> courses = new HashSet<Course>();
		Course course1 = new Course();
		Course course2 = new Course();
		Course course3 = new Course();
		
		course1.setC_name("Chinese");
		course2.setC_name("Math");
		course3.setC_name("English");
		
		courses.add(course3);
		courses.add(course2);
		courses.add(course1);
		
	    Set<Student> students = new HashSet<Student>();
	    
	    Student s1 = new Student();
	    s1.setStu_name("xiaohong");
	    Student s2 = new Student();
	    s2.setStu_name("xiaoming");
	    Student s3 = new Student();
	    s3.setStu_name("dabai");
	    
	    students.add(s1);
	    students.add(s2);
	    students.add(s3);
	    
	    course1.setStudents(students);
	    course2.setStudents(students);
	    course3.setStudents(students);
	    
		session.save(course1);
		session.save(course2);
		session.save(course3);
		
		session.getTransaction().commit();
		session.clear();
		sessionFactory.close();
	}
}
