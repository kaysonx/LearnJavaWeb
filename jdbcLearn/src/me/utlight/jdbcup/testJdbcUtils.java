package me.utlight.jdbcup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.utlight.domain.Account;

public class testJdbcUtils {
	
	public static void main(String[] args) {
		AccountDao dao = new AccountDao();
		Account account = new Account();
		account.setName("test");
		account.setMoney(666);
		
		try {
			
//			dao.add(account);
//			System.out.println("添加成功...");
//			Account findRs = dao.find(1);
//			if (findRs != null) {
//				System.out.println("找到了...name:"+findRs.getName()+",money: "+findRs.getMoney());
//			}
			
			dao.delete(2);
			System.out.println("删除成功...");
			List<Account> list = null;
			list = dao.findAll();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Account accountTmp = (Account) iterator.next();
				System.out.println("找到了...name:"+accountTmp.getName()+",money: "+accountTmp.getMoney());
			}
			
			
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
