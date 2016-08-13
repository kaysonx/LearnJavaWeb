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
 * 处理用户注册的Servlet
 */
 //@WebServlet("/RegistServlet")  注意 这个和配置文件中的配置只能存在一个
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RegistServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RegistFormBean formbean = WebUtils.request2Bean(request, RegistFormBean.class);
		
		if (formbean.validate() == false) {
			//验证失败 返回表单页面
			request.setAttribute("formbean", formbean);
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
			return;
		}
		
		User user = new User();
		try {
			//注册字符串到日期的转换器   一定不能导成这个import java.sql.Date;
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			//把表单的数据填充到javabean中
			BeanUtils.copyProperties(user, formbean);
			user.setId(WebUtils.makeId());
			
			IUserInfoService service = new UserServiceImpl();
			//注意xml文件格式。。。
			service.registUser(user);
			
			String message = String.format(
		               "注册成功！！3秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='3;url=%s'/>", 
		              request.getContextPath()+"/log.do");
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		} catch (UserExistException e) {
			formbean.getErrors().put("userName", "注册用户已经存在！");
			request.setAttribute("formbean", formbean);
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "对不起，注册失败！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
