package me.utlight.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author liusha
 * 将结果集转换成bean对象
 */
public class BeanHandler implements ResultSetHander{

	private Class<?> clazz;
	
	public BeanHandler(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public Object handler(ResultSet rs) {
		
		try {
			if (!rs.next()) {
				return null;
			}
			
			Object bean = clazz.newInstance();//反射创建bean
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount(); // 获取列数
			
			for (int i = 0; i < columnCount; i++) {
				String columnName = metadata.getColumnName(i+1);//注意索引是从1开始的
				Object columnData = rs.getObject(i+1);
				
				Field f =clazz.getDeclaredField(columnName);//反射出类上列 名队对应的属性
				f.setAccessible(true);
				f.set(bean, columnData);
			}
			
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
