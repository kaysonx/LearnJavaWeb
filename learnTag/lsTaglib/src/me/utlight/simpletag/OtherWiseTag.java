package me.utlight.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author liusha
 * OtherwiseTag
 */
public class OtherWiseTag extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		
		ChooseTag parentTag = (ChooseTag)this.getParent();
		if (parentTag.isExecute() == false) {
			
			this.getJspBody().invoke(null);
			parentTag.setExecute(true);
		}
	}
	
}
