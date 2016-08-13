package me.utlight.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/**
 * @author liusha
 * 统计在线人数    有点像.NET的管道机制
 * 另外：系统提供的监听器不用在web.xml中注册
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
