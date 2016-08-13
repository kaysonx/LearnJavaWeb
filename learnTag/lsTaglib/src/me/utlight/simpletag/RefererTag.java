package me.utlight.simpletag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author liusha
 * ��������ǩ
 */
public class RefererTag  extends SimpleTagSupport{

	//��վ����
	private String site;
	
	//��תҳ��
	private String page;
	
	public void setSite(String site) {
		this.site = site;
	}
	
	public void setPage(String page) {
		this.page = page;
	}
	
	
	@Override
	public void doTag() throws JspException, IOException {
		
		PageContext pageContext = (PageContext) this.getJspContext();
		
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		String referer = request.getHeader("referer");
		if (referer == null || !referer.startsWith(site)) {
			HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
			String webRoot = request.getContextPath();
			if (page.startsWith(webRoot)) {
				response.sendRedirect(page);
			}else{
				response.sendRedirect(webRoot+page);
			}
			
			//ʹҳ�治��ִ��
			throw new SkipPageException();
		}
		
	}

}
