package me.utlight.testHibernate;


import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import me.utlight.entity.Group;
import me.utlight.entity.User;


//��Ҫ�ѱ�����ֶ����úͱ���һ���������ᱨ��
public class testOnet2Many {
		public static void main(String[] args) {
			//��ȡhibernate������Ϣ
			Configuration configuration = new Configuration().configure();
			//����configuration����sessionFactory
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			//����session
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
