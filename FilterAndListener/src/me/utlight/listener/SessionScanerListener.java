package me.utlight.listener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/**
 * @author liusha
 * 自定义session扫描器 手动销毁session
 */
@WebListener
public class SessionScanerListener implements ServletContextListener, HttpSessionListener {

	//使用Collections.synchronizedList 将LinkedList包装成一个线程安全的集合
	private List<HttpSession> list = Collections.synchronizedList(new LinkedList<HttpSession>());
	private Object lock = new Object();

    public void contextInitialized(ServletContextEvent se)  { 
         System.out.println("web应用初始化....");
         Timer timer = new Timer();
         //每隔30s就执行任务MyTask里的run
         timer.schedule(new MyTask(list, lock), 0, 1000*30);
    }


    public void sessionCreated(HttpSessionEvent se)  { 
         System.out.println("Session被创建了....");
         HttpSession session = se.getSession();
         
         synchronized(lock){
        	 list.add(session);
         }
    }


    public void sessionDestroyed(HttpSessionEvent se)  { 
         System.out.println("session被销毁了....");
    }


    public void contextDestroyed(ServletContextEvent ce)  { 
         System.out.println("web应用关闭了....");
    }
    
    class MyTask extends TimerTask{
    	private List<HttpSession> list;
    	private Object lock;
    	
    	public MyTask(List<HttpSession> list,Object lock) {
			this.list = list;
			this.lock = lock;
		}

		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("定时器执行....");
				ListIterator<HttpSession> it = list.listIterator();
				
				while (it.hasNext()) {
					HttpSession session = (HttpSession)it.next();
					
					//如果当前时间距最后访问时间相差30s 就销毁session
					if (System.currentTimeMillis()-session.getLastAccessedTime() > 1000*30) {
						session.invalidate();
						it.remove();
					}
					
				}
			}
			
		}
    	
    	
    }
	
}
