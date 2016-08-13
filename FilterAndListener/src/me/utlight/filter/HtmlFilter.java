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

public class HtmlFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse  response = (HttpServletResponse)arg1;
		
		MyHtmlRequest myrequest = new MyHtmlRequest(request);
		chain.doFilter(myrequest, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	
	}
	
	/**
	 * @author liusha
	 * 用装饰者模式包装request对象。实现html标签转义功能
	 */
	class MyHtmlRequest extends HttpServletRequestWrapper{

		private HttpServletRequest request;
		public MyHtmlRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		@Override
		public String getParameter(String name) {
			String value = this.request.getParameter(name);
			if (value == null) {
				return null;
			}
		
			return filter(value);
		}
		
		/**
		 * 过滤内容中的html标签
		 * @param value
		 * @return
		 */
		public String filter(String value){
			if (value == null) {
				return null;
			}
			
			char content[] = new char[value.length()];
			value.getChars(0, value.length(), content, 0);
			StringBuffer result = new StringBuffer(content.length + 50);
			
			for (int i = 0; i < content.length; i++) {
				switch (content[i]) {
				case '<':
					result.append("&lt;");
					break;
				case '>':
					result.append("&gt;");
					break;
				case '&':
					result.append("&amp;");
					break;
				case '"':
					result.append("&quot");
					break;
				default:
					result.append(content[i]);
				}
			}
			return result.toString();
		}
		
	}
	
}
