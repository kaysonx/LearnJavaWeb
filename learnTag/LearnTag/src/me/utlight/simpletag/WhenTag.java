package me.utlight.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author liusha
 * when标签
 * 父级标签是通过在使用时嵌套而来的
 */
public class WhenTag extends SimpleTagSupport{

	//test为true时知输出标签体的内容
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
