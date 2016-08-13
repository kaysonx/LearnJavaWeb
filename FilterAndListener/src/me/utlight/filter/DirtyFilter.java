package me.utlight.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;


public class DirtyFilter implements Filter {

	private FilterConfig config = null;
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		DirtyRequest dirRequest = new DirtyRequest(req);
		
		chain.doFilter(dirRequest, res);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
		config = fConfig;
	}
	
	/**
	 * 从文件中读取敏感词汇  
	 * @return
	 */
	private List<String> getDirtyWords(){
		List<String> dirtyWords = new ArrayList<String>();
		String dirtyWordPath = config.getInitParameter("dirtyWords");
		InputStream inputStream = config.getServletContext().getResourceAsStream(dirtyWordPath);
		
		InputStreamReader is = null;
		
		try {
			//测试时 读出中文乱码。。。使用GBK解决
			is = new InputStreamReader(inputStream, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(is);
		String line;
		
		try {
			while ((line = reader.readLine()) != null) {
				dirtyWords.add(line);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dirtyWords;
	}
	
	class DirtyRequest extends HttpServletRequestWrapper{

		private HttpServletRequest request;
		private List<String> dirtyWords = getDirtyWords();
		
		public DirtyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		@Override
		public String getParameter(String name) {
			
			String value = this.request.getParameter(name);
			if(value == null){
				return null;
			}
				
			for(String dirtyWord : dirtyWords){
				if (value.contains(dirtyWord)) {
					System.out.println("内容中包含敏感词汇： "+dirtyWord + "将会被替换成*****");
					value = value.replace(dirtyWord, "***");
				}
			}
			return value;
		}
		
		
		
	}

	@Override
	public void destroy() {
		
	}

}
