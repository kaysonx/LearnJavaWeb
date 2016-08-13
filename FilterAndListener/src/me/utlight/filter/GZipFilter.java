package me.utlight.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;



public class GZipFilter implements Filter {


    public GZipFilter() {
       
    }


	public void destroy() {
	
	}

	//ѹ��webӦ���е��ı�
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletResponse res = (HttpServletResponse)response;
		
		BufferResponse myBufferResponse = new BufferResponse(res);
		chain.doFilter(request, myBufferResponse);
		//�ó������е�����   ����ѹ��������ٷ��������
		byte[] out = myBufferResponse.getBuffer();
		System.out.println("ԭʼ��С�� " + out.length);
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		gout.write(out);
		gout.close();
		
		byte[] gzip = bout.toByteArray();
		System.out.println("ѹ����Ĵ�С�� " + gzip.length);
		
		res.setHeader("content-encoding", "gzip");
		res.setContentLength(gzip.length);
		res.getOutputStream().write(gzip);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	class BufferResponse extends HttpServletResponseWrapper{

		private HttpServletResponse response;
		private ByteArrayOutputStream bout = new ByteArrayOutputStream();
		private PrintWriter pw;
	
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
				
				if (bout != null) {
					bout.flush();
					return bout.toByteArray();
				}
				
				
				return null;
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
		public void write(int b) throws IOException {
			this.bout.write(b);
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
		
	}
	

}
