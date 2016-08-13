package me.utlight.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author liusha
 *
 */
public class IFTag extends SimpleTagSupport{

	//��ǩ���Զ�������
	private boolean test;
	
	public void setTest(boolean test) {
		this.test = test;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		
		if (test) {
			//�������
			this.getJspBody().invoke(null);
		}
	}
	
}
