package me.utlight.serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import me.utlight.entity.Users;
import me.utlight.service.UsersDAO;
import me.utlight.utils.MyHibernateSessionFactory;

public class UsersDAOImpl implements UsersDAO{

	@Override
	public boolean usersLogin(Users user) {
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users where username=:username and password=:password";
			Query query = session.createQuery(hql);
			//query.setProperties(user);
			query.setParameter("username", user.getUsername());
			query.setParameter("password", user.getPassword());
			
			List list = query.list();
			tx.commit();
			
			if (list.size() > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if(tx != null){
				tx = null;
			}
		}
	}
	
}
