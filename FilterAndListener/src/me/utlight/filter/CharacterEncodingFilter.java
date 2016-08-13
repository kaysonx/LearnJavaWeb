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
 * 使用Filter来解决get post请求方式下的中文乱码问题
 */
	public class CharacterEncodingFilter implements Filter {
	
	private FilterConfig filterConfig = null;
	//设置默认的字符编码
	private String defaultCharset = "UTF-8";
  


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//HttpServletRequest 是 ServletRequest 的子接口，适用于处理http协议
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		//得到在web.xml中配置的字符编码
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
	 * 使用装饰者模式来增强request的功能
	 */
	class MyCharacterEncodingRequest extends HttpServletRequestWrapper{
		//需要被增强的对象
		private HttpServletRequest request;
		
		public MyCharacterEncodingRequest(HttpServletRequest request) {
			super(request); //父类中实现了request功能的简单封装。
			this.request = request;
		}
		
		//需要增强的方法
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
					//处理以get方式提交过来的数据
					//本机测试已经 不用转换了 。。。
					value = new String(value.getBytes("ISO8859-1"),this.request.getCharacterEncoding());
					return value;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		
		
	}
	
	

}
