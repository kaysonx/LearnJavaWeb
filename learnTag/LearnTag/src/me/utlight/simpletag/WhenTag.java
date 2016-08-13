package me.utlight.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author liusha
 * when��ǩ
 * ������ǩ��ͨ����ʹ��ʱǶ�׶�����
 */
public class WhenTag extends SimpleTagSupport{

	//testΪtrueʱ֪�����ǩ�������
	private boolean test;
	
	public void setTest(boolean test) {
		this.test = test;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		ChooseTag parentTag = (ChooseTag)this.getParent();
		if (test == true && parentTag.isExecute() == false) {
			
			this.getJspBody().invoke(null);
			parentTag.setExecute(true);
		}
	}
	
}
