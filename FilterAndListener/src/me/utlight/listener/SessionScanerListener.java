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
 * �Զ���sessionɨ���� �ֶ�����session
 */
@WebListener
public class SessionScanerListener implements ServletContextListener, HttpSessionListener {

	//ʹ��Collections.synchronizedList ��LinkedList��װ��һ���̰߳�ȫ�ļ���
	private List<HttpSession> list = Collections.synchronizedList(new LinkedList<HttpSession>());
	private Object lock = new Object();

    public void contextInitialized(ServletContextEvent se)  { 
         System.out.println("webӦ�ó�ʼ��....");
         Timer timer = new Timer();
         //ÿ��30s��ִ������MyTask���run
         timer.schedule(new MyTask(list, lock), 0, 1000*30);
    }


    public void sessionCreated(HttpSessionEvent se)  { 
         System.out.println("Session��������....");
         HttpSession session = se.getSession();
         
         synchronized(lock){
        	 list.add(session);
         }
    }


    public void sessionDestroyed(HttpSessionEvent se)  { 
         System.out.println("session��������....");
    }


    public void contextDestroyed(ServletContextEvent ce)  { 
         System.out.println("webӦ�ùر���....");
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
				System.out.println("��ʱ��ִ��....");
				ListIterator<HttpSession> it = list.listIterator();
				
				while (it.hasNext()) {
					HttpSession session = (HttpSession)it.next();
					
					//�����ǰʱ���������ʱ�����30s ������session
					if (System.currentTimeMillis()-session.getLastAccessedTime() > 1000*30) {
						session.invalidate();
						it.remove();
					}
					
				}
			}
			
		}
    	
    	
    }
	
}
