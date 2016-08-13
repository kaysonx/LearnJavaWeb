
package me.utlight.simpletag;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author liusha
 * ForEach标签
 */
public class ForEachTag extends SimpleTagSupport{
	
	//存储数据
	private Object items;
	//遍历时的变量
	private String var;
	//存储集合
	private Collection collection;
	
	public void setItems(Object items) {
		if (items instanceof Collection) {
			collection = (Collection)items;
		}else if (items instanceof Map) {
			Map map = (Map)items;
			collection = map.entrySet();
		}else if (items.getClass().isArray()) {
			collection = new ArrayList();
			int len = Array.getLength(items);
			for (int i = 0; i < len; i++) {
				Object object = Array.get(items, i);
				collection.add(object);
			}
		}
		this.items = items;
	}
	
	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) this.getJspContext();
		Iterator it = collection.iterator();
		
		while (it.hasNext()) {
			Object object = (Object)it.next();
			pageContext.setAttribute(var, object);
			this.getJspBody().invoke(null);
		}
	}
	
	
}
