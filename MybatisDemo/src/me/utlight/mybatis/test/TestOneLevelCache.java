package me.utlight.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import me.utlight.entity.User;
import me.utlight.util.MyBatisUtil;

//测试一级缓存
//	正如大多数持久层框架一样，MyBatis 同样提供了一级缓存和二级缓存的支持
//	一级缓存: 基于PerpetualCache 的 HashMap本地缓存，其存储作用域为 Session，当 Session flush 或 close 之后，该Session中的所有 Cache 就将清空。
//　  二级缓存与一级缓存其机制相同，默认也是采用 PerpetualCache，HashMap存储，不同在于其存储作用域为 Mapper(Namespace)，并且可自定义存储源，如 Ehcache。
//
//　  对于缓存数据更新机制，当某一个作用域(一级缓存Session/二级缓存Namespaces)的进行了 C/U/D 操作后，默认该作用域下所有 select 中的缓存将被clear。
public class TestOneLevelCache {
	@Test
	public void testCache1() {
		SqlSession session = MyBatisUtil.getSqlSession();
		String statement = "me.utlight.mapping.userMapper.getUser";
		User user = session.selectOne(statement, 1);
		System.out.println(user);

		/*
		 * 一级缓存默认就会被使用
		 */
		user = session.selectOne(statement, 1);
		System.out.println(user);
		session.close();
		/*
		 * 1. 必须是同一个Session,如果session对象已经close()过了就不可能用了
		 */
		session = MyBatisUtil.getSqlSession();
		user = session.selectOne(statement, 1);
		System.out.println(user);

		/*
		 * 2. 查询条件是一样的
		 */
		user = session.selectOne(statement, 2);
		System.out.println(user);

		/*
		 * 3. 没有执行过session.clearCache()清理缓存
		 */
		// session.clearCache();
		user = session.selectOne(statement, 2);
		System.out.println(user);

		/*
		 * 4. 没有执行过增删改的操作(这些操作都会清理缓存)
		 */
		session.update("me.utlight.mapping.userMapper.updateUser", new User(2, "user", 1));
		user = session.selectOne(statement, 2);
		System.out.println(user);

	}
}
