package me.utlight.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


public class WebSourceCachedFilter implements Filter {

	//����web��Դ��map����
	private Map<String, byte[]> map = new HashMap<String, byte[]>();
	
    public WebSourceCachedFilter() {
        
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//1.�õ��û��������uri
		String uri = req.getRequestURI();
		//2.�鿴�������Ƿ��ж�Ӧ������
		byte[] b = map.get(uri);
		
		if (b != null) {
			String webResourceHtmlStr = new String(b,res.getCharacterEncoding());
			System.out.println(webResourceHtmlStr);
			res.getOutputStream().write(b);
			return;
		}
		//��� û�� �򴴽�
		BufferResponse myResponse = new BufferResponse(res);
		chain.doFilter(req, myResponse);
		
		byte[]  out = myResponse.getBuffer();
		map.put(uri, out);
		
		res.getOutputStream().write(out);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	class BufferResponse extends HttpServletResponseWrapper{

		private ByteArrayOutputStream bout = new ByteArrayOutputStream();
		private PrintWriter pw;
		private HttpServletResponse response;
		
		public BufferResponse(HttpServletResponse response) {
			super(response);
			this.response = response;
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			return new MyServletOutputStream(bout);
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			pw = new PrintWriter(new OutputStreamWriter(bout,this.response.getCharacterEncoding()));
			return pw;
		}
		
		public byte[] getBuffer(){
			try {
				if (pw != null) {
					pw.close();
				}
				
				return bout.toByteArray();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	class MyServletOutputStream extends ServletOutputStream{

		private ByteArrayOutputStream bout;
		public MyServletOutputStream(ByteArrayOutputStream bout) {
			this.bout = bout;
		}
		
		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setWriteListener(WriteListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void write(int b) throws IOException {
			bout.write(b);
		}
		
	}

}
