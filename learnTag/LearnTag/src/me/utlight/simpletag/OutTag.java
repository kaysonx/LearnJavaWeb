package me.utlight.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class OutTag extends SimpleTagSupport{

	//要输出的你内容
	private String content;
	//是否进行转义
	private boolean escapeHtml;
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setEscapeHtml(boolean escapeHtml) {
		this.escapeHtml = escapeHtml;
	}
	
	
	@Override
	public void doTag() throws JspException, IOException {
		if (escapeHtml == true) {
			content = filter(content);
			this.getJspContext().getOut().write(content);
		}else {
			this.getJspContext().getOut().write(content);
		}
	}
	

	/**
	 * @param message
	 * @return 转义后的html标签
	 */
	private String filter(String message){
		
		if (message == null) {
			return null;
		}
		
		char content[] = new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuilder result  = new StringBuilder(content.length+50);
		for (int i = 0; i < content.length; i++) {
			switch (content[i]){
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
					result.append("&quot;");
					break;
				default:
					result.append(content[i]);
			}
			
		}
		return result.toString();
	}
}
