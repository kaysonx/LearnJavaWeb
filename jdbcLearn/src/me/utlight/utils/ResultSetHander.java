package me.utlight.utils;

import java.sql.ResultSet;

public interface ResultSetHander {
	
	/**
	 * ���������ķ���
	 * @param rs
	 * @return
	 */
	public Object handler(ResultSet rs);
}
