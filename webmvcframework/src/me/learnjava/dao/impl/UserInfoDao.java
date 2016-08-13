/**
 * 
 */
package me.learnjava.dao.impl;

import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.Element;


import me.learnjava.dao.IUserDao;
import me.learnjava.domain.User;
import me.learnjava.util.XmlUtils;

/**
 * @author liusha
 *
 */
public class UserInfoDao implements IUserDao {

	/* 
	 * @see me.learnjava.dao.IUserDao#Find(java.lang.String, java.lang.String)
	 */
	@Override
	public User Find(String userName, String userPwd) {
		try {
			Document document = XmlUtils.getDocument();
			Element e = (Element)document.selectSingleNode("//user[@userName='" +userName + "' and @userPwd='"+ userPwd +"']");
			if (e == null) {
				return null;
			}
			
			User user = new User();
			user.setId(e.attributeValue("id"));
			user.setEmail(e.attributeValue("eamil"));
			user.setUserName(e.attributeValue("userName"));
			user.setUserPwd(e.attributeValue("userPwd"));
			String birth = e.attributeValue("birthday");
			SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
			user.setBirthday(sdf.parse(birth));
			
			System.out.println(user.getUserName()+"  " +user.getUserPwd());
			return user;
		} catch (Exception e) { 
			throw new RuntimeException(e);
		}
	}

	/* 
	 * @see me.learnjava.dao.IUserDao#Add(me.learnjava.domain.User)
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void Add(User user) {
		
		try {
			Document document = XmlUtils.getDocument();
			Element root = document.getRootElement();
			Element user_node = root.addElement("user");
			user_node.setAttributeValue("userName", user.getUserName());
			user_node.setAttributeValue("userPwd", user.getUserPwd());
			user_node.setAttributeValue("email", user.getEmail());
			user_node.setAttributeValue("id", user.getId());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			user_node.setAttributeValue("birthday", sdf.format(user.getBirthday()));
			
			XmlUtils.write2Xml(document);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/* 
	 * @see me.learnjava.dao.IUserDao#Find(java.lang.String)
	 */
	@Override
	public User Find(String username) {
		
		try {
			Document document = XmlUtils.getDocument();
			Element e = (Element) document.selectSingleNode("//user[@userName='"+username+"']");
			if (e == null) {
				return null;
			}
			
			User user = new User();
			user.setId(e.attributeValue("id"));
			user.setEmail(e.attributeValue("email"));
			user.setUserPwd(e.attributeValue("userPwd"));
			user.setUserName(e.attributeValue("userName"));
			String birth = e.attributeValue("birthday");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			user.setBirthday(sdf.parse(birth));
			
			return user;
					
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
