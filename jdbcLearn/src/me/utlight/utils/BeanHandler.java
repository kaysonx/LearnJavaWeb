package me.utlight.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author liusha
 * �������ת����bean����
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
			
			Object bean = clazz.newInstance();//���䴴��bean
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount(); // ��ȡ����
			
			for (int i = 0; i < columnCount; i++) {
				String columnName = metadata.getColumnName(i+1);//ע�������Ǵ�1��ʼ��
				Object columnData = rs.getObject(i+1);
				
				Field f =clazz.getDeclaredField(columnName);//����������� ���Ӷ�Ӧ������
				f.setAccessible(true);
				f.set(bean, columnData);
			}
			
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
