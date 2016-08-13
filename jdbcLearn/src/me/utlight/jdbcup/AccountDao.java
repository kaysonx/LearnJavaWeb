package me.utlight.jdbcup;

import java.sql.SQLException;
import java.util.List;

import me.utlight.domain.Account;
import me.utlight.utils.Bean2ListHandler;
import me.utlight.utils.BeanHandler;
import me.utlight.utils.JdbcUtils;

/**
 * @author liusha
 * 采用自己编写的JdbcUtils编写Dao
 */
public class AccountDao {
	
	public void add(Account account) throws SQLException {
		String sql = "insert into account(name,money) values(?,?)";
		Object[] params = {account.getName(),account.getMoney()};
		JdbcUtils.update(sql, params);
	}
	
	public void delete(int id) throws SQLException {
		String sql = "delete from account where id=?";
		Object[] params = {id};
		JdbcUtils.update(sql, params);
	}
	
	public Account find(int id) throws SQLException {
		String sql = "select * from account where id=?";
		Object[] params = {id};
		return (Account) JdbcUtils.query(sql, params, new BeanHandler(Account.class));
	}
	

	public List<Account> findAll() throws SQLException {
		String sql = "select * from account";
		Object[] params = {};
		return (List<Account>) JdbcUtils.query(sql, params, new Bean2ListHandler(Account.class));
	}
	
	
	
	
}
