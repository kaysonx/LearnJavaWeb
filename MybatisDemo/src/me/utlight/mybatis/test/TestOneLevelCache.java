package me.utlight.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import me.utlight.entity.User;
import me.utlight.util.MyBatisUtil;

//����һ������
//	���������־ò���һ����MyBatis ͬ���ṩ��һ������Ͷ��������֧��
//	һ������: ����PerpetualCache �� HashMap���ػ��棬��洢������Ϊ Session���� Session flush �� close ֮�󣬸�Session�е����� Cache �ͽ���ա�
//��  ����������һ�������������ͬ��Ĭ��Ҳ�ǲ��� PerpetualCache��HashMap�洢����ͬ������洢������Ϊ Mapper(Namespace)�����ҿ��Զ���洢Դ���� Ehcache��
//
//��  ���ڻ������ݸ��»��ƣ���ĳһ��������(һ������Session/��������Namespaces)�Ľ����� C/U/D ������Ĭ�ϸ������������� select �еĻ��潫��clear��
public class TestOneLevelCache {
	@Test
	public void testCache1() {
		SqlSession session = MyBatisUtil.getSqlSession();
		String statement = "me.utlight.mapping.userMapper.getUser";
		User user = session.selectOne(statement, 1);
		System.out.println(user);

		/*
		 * һ������Ĭ�Ͼͻᱻʹ��
		 */
		user = session.selectOne(statement, 1);
		System.out.println(user);
		session.close();
		/*
		 * 1. ������ͬһ��Session,���session�����Ѿ�close()���˾Ͳ���������
		 */
		session = MyBatisUtil.getSqlSession();
		user = session.selectOne(statement, 1);
		System.out.println(user);

		/*
		 * 2. ��ѯ������һ����
		 */
		user = session.selectOne(statement, 2);
		System.out.println(user);

		/*
		 * 3. û��ִ�й�session.clearCache()������
		 */
		// session.clearCache();
		user = session.selectOne(statement, 2);
		System.out.println(user);

		/*
		 * 4. û��ִ�й���ɾ�ĵĲ���(��Щ��������������)
		 */
		session.update("me.utlight.mapping.userMapper.updateUser", new User(2, "user", 1));
		user = session.selectOne(statement, 2);
		System.out.println(user);

	}
}
