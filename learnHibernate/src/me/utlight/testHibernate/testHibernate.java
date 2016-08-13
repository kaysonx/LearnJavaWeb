package me.utlight.testHibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import me.utlight.entity.Test;
import me.utlight.entity.User;

public class testHibernate {
	public static void main(String[] args) {
		
		//获取hibernate配置信息
		Configuration configuration = new Configuration().configure();
		//根据configuration建立sessionFactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//开启session
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		
		Test test = new Test();
		test.setId(1);
		test.setUsername("admin");
		test.setPassword("111");
		
		session.save(test);
		//提交事务
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		
	}
	
	private static void add(){
		//获取hibernate配置信息
		Configuration configuration = new Configuration().configure();
		//根据configuration建立sessionFactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//开启session
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		
		User user = new User();
		user.setId(1);
		user.setUsername("test");
		user.setPassword("666");
		
		session.save(user);
		//提交事务
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
	
	private static  void search(){
		
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		//拼接查询语句
		StringBuilder hq = new StringBuilder();
		hq.append("from ").append(User.class.getName());
		
		Query query = session.createQuery(hq.toString());
		List<User> users = query.list();
		
		for(User user : users){
			System.out.println( user.getUsername() );
		}
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
	
	private static void update(){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		//拼接查询语句
		StringBuilder hq = new StringBuilder();
		hq.append("from ").append(User.class.getName()).append( " where username=:name");
		
		Query query = session.createQuery(hq.toString());
		query.setString("name", "test");
		List<User> users = query.list();
		
		for(User user : users){
			System.out.println( user.getUsername() );
			user.setPassword("admin");
			session.update(user);
		}
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
	
	private static void delete(){
		
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		//拼接查询语句
		StringBuilder hq = new StringBuilder();
		hq.append("from ").append(User.class.getName()).append( " where username=:name");
		
		Query query = session.createQuery(hq.toString());
		query.setString("name", "test");
		List<User> users = query.list();
		
		for(User user : users){
			System.out.println( user.getUsername() );
			session.delete(user);
		}
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}















