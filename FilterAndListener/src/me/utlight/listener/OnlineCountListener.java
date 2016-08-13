package me.utlight.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/**
 * @author liusha
 * ͳ����������    �е���.NET�Ĺܵ�����
 * ���⣺ϵͳ�ṩ�ļ�����������web.xml��ע��
 */
@WebListener
public class OnlineCountListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se)  { 
         ServletContext context = se.getSession().getServletContext();
         Integer onLineCount = (Integer)context.getAttribute("onLineCount");
         if(onLineCount == null){
        	 context.setAttribute("onLineCount", 1);
         }else {
			onLineCount++;
			context.setAttribute("onLineCount", onLineCount);
		}
    }


    public void sessionDestroyed(HttpSessionEvent se)  { 
        ServletContext context = se.getSession().getServletContext();
        Integer onLineCount = (Integer)context.getAttribute("onLineCount");
        if(onLineCount == null){
       	 context.setAttribute("onLineCount", 1);
        }else {
			onLineCount--;
			context.setAttribute("onLineCount", onLineCount);
		}
    }
	
}
