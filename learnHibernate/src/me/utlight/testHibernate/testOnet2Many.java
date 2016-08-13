package me.utlight.testHibernate;


import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import me.utlight.entity.Group;
import me.utlight.entity.User;


//不要把表里的字段设置和表名一样。。。会报错
public class testOnet2Many {
		public static void main(String[] args) {
			//获取hibernate配置信息
			Configuration configuration = new Configuration().configure();
			//根据configuration建立sessionFactory
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			//开启session
			Session session = sessionFactory.openSession();
			
			session.beginTransaction();
			
			Group group = new Group();
			group.setGroupname("TTP");
			
			Set<User> users = new HashSet<User>();
			User user1 = new User();
		
			user1.setUsername("user1");
			user1.setPassword("user1pwd");
			
			
			User user2 = new User();
			
			user2.setUsername("user2");
			user2.setPassword("user2pwd");
			
			users.add(user1);
			users.add(user2);
			
			
			group.setUsers(users);
			
			for(User user : users){
				session.save(user);
			}
			
			session.save(group);
			
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
		}	
}
