package me.utlight.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;


/**
 * @author liusha
 * ʹ��Filter�����get post����ʽ�µ�������������
 */
	public class CharacterEncodingFilter implements Filter {
	
	private FilterConfig filterConfig = null;
	//����Ĭ�ϵ��ַ�����
	private String defaultCharset = "UTF-8";
  


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//HttpServletRequest �� ServletRequest ���ӽӿڣ������ڴ���httpЭ��
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		//�õ���web.xml�����õ��ַ�����
		String charset = filterConfig.getInitParameter("charset");
		if (charset == null) {
			charset = defaultCharset;
		}
		
		req.setCharacterEncoding(charset);
		res.setCharacterEncoding(charset);
		res.setContentType("text/html;charset="+charset);
		
		MyCharacterEncodingRequest requestWrapper =  new MyCharacterEncodingRequest(req);
		chain.doFilter(requestWrapper, res);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
	}
	
	/**
	 * @author liusha
	 * ʹ��װ����ģʽ����ǿrequest�Ĺ���
	 */
	class MyCharacterEncodingRequest extends HttpServletRequestWrapper{
		//��Ҫ����ǿ�Ķ���
		private HttpServletRequest request;
		
		public MyCharacterEncodingRequest(HttpServletRequest request) {
			super(request); //������ʵ����request���ܵļ򵥷�װ��
			this.request = request;
		}
		
		//��Ҫ��ǿ�ķ���
		@Override
		public String getParameter(String name) {
			try {
				
				String value = this.request.getParameter(name);
				if (value == null) {
					return null;
				}
				
				if (!this.request.getMethod().equalsIgnoreCase("get")) {
					return value;
				}else {
					//������get��ʽ�ύ����������
					//���������Ѿ� ����ת���� ������
					value = new String(value.getBytes("ISO8859-1"),this.request.getCharacterEncoding());
					return value;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		
		
	}
	
	

}
