package me.utlight.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author liusha
 * when��ǩ��otherwise�ĸ���ǩ Ŀ���ǹ������ݻ򷽷�
 */
public class ChooseTag extends SimpleTagSupport{

	private boolean isExecute;
	
	public void setExecute(boolean isExecute) {
		this.isExecute = isExecute;
	}
	
	public boolean isExecute(){
		return this.isExecute;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		//ֱ�������ǩ���е�����
		this.getJspBody().invoke(null);
	}
	
	
}
