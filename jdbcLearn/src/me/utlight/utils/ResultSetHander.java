package me.utlight.utils;

import java.sql.ResultSet;

public interface ResultSetHander {
	
	/**
	 * 处理结果集的方法
	 * @param rs
	 * @return
	 */
	public Object handler(ResultSet rs);
}
