package me.learnjava.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.learnjava.domain.User;
import me.learnjava.service.IUserInfoService;
import me.learnjava.service.impl.UserServiceImpl;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public LoginServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("username");
		String userpwd = request.getParameter("password");

		IUserInfoService service = new UserServiceImpl();
		User user = service.loginUser(userName, userpwd);
			
		if (user == null) {
			String message = String.format("对不起，用户名或密码有误！！请重新登录！2秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='2;url=%s'", 
					request.getContextPath()+"/log.do");
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		//登录成功 将用户信息写入session
		request.getSession().setAttribute("user", user);
		 String message = String.format(
				                "恭喜：%s,登陆成功！本页将在3秒后跳到首页！！<meta http-equiv='refresh' content='3;url=%s'", 
				                user.getUserName(),
				                request.getContextPath()+"/index.jsp");
		 request.setAttribute("message", message);
		 request.getRequestDispatcher("/message.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
