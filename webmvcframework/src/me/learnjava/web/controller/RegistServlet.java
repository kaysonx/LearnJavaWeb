package me.learnjava.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import me.learnjava.domain.User;
import me.learnjava.exception.UserExistException;
import me.learnjava.service.IUserInfoService;
import me.learnjava.service.impl.UserServiceImpl;
import me.learnjava.util.WebUtils;
import me.learnjava.web.formbean.RegistFormBean;


/**
 * @author liusha
 * �����û�ע���Servlet
 */
 //@WebServlet("/RegistServlet")  ע�� ����������ļ��е�����ֻ�ܴ���һ��
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RegistServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RegistFormBean formbean = WebUtils.request2Bean(request, RegistFormBean.class);
		
		if (formbean.validate() == false) {
			//��֤ʧ�� ���ر�ҳ��
			request.setAttribute("formbean", formbean);
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
			return;
		}
		
		User user = new User();
		try {
			//ע���ַ��������ڵ�ת����   һ�����ܵ������import java.sql.Date;
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			//�ѱ���������䵽javabean��
			BeanUtils.copyProperties(user, formbean);
			user.setId(WebUtils.makeId());
			
			IUserInfoService service = new UserServiceImpl();
			//ע��xml�ļ���ʽ������
			service.registUser(user);
			
			String message = String.format(
		               "ע��ɹ�����3���Ϊ���Զ�������¼ҳ�棡��<meta http-equiv='refresh' content='3;url=%s'/>", 
		              request.getContextPath()+"/log.do");
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		} catch (UserExistException e) {
			formbean.getErrors().put("userName", "ע���û��Ѿ����ڣ�");
			request.setAttribute("formbean", formbean);
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "�Բ���ע��ʧ�ܣ�");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
