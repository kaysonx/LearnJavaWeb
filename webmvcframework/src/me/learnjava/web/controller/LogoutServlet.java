package me.learnjava.web.controller;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    //�Ƴ��洢��session�е�user����ʵ��ע������
        request.getSession().removeAttribute("user");
        //�����ַ����а����е����ţ������������ʹ��MessageFormat.format����ƴ���ַ���ʱ�ͻ�������
        //MessageFormat.format����ֻ�ǰ��ַ����еĵ�����ȥ�������Ὣ������䵽ָ����ռλ����
        String tempStr1 = MessageFormat.format(
                "ע���ɹ�����3���Ϊ���Զ�������¼ҳ�棡��<meta http-equiv='refresh' content='3;url={0}'/>", 
                request.getContextPath()+"/log.do");
        System.out.println(tempStr1);//��������ע���ɹ�����3���Ϊ���Զ�������¼ҳ�棡��<meta http-equiv=refresh content=3;url={0}/>
        System.out.println("---------------------------------------------------------");
        /**
         * Ҫ����"���Ҫƴ�ӵ��ַ��������е����ţ���ôMessageFormat.format������ֻ�ǰ��ַ����еĵ�����ȥ�������Ὣ������䵽ָ����ռλ����"������⣬
         * ��ô������Ҫʹ�õ��������������ַ�����ʹ��2�������������������磺"<meta http-equiv=''refresh'' content=''3;url={0}''/>"
         * ����MessageFormat.format("<meta http-equiv=''refresh'' content=''3;url={0}''/>","index.jsp")�Ϳ�����������
         * <meta http-equiv=''refresh'' content=''3;url=index.jsp'/>
         */
        String tempStr2 = MessageFormat.format(
                "ע���ɹ�����3���Ϊ���Զ�������¼ҳ�棡��<meta http-equiv=''refresh'' content=''3;url={0}''/>", 
                request.getContextPath()+"/log.do");
        /**
         * ��������
         * ע���ɹ�����3���Ϊ���Զ�������¼ҳ�棡��
         * <meta http-equiv='refresh' content='3;url=/webmvcframework/LoginUIServlet'/>
         */
        System.out.println(tempStr2);
        
        String message = String.format(
                "ע���ɹ�����3���Ϊ���Զ�������¼ҳ�棡��<meta http-equiv='refresh' content='3;url=%s'/>", 
                request.getContextPath()+"/log.do");
        request.setAttribute("message",message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
