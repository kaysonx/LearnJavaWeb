package me.utlight.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author liusha
 * when标签和otherwise的父标签 目的是共享数据或方法
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
		//直接输出标签体中的内容
		this.getJspBody().invoke(null);
	}
	
	
}
