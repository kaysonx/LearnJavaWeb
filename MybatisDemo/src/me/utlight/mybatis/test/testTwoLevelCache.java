package me.utlight.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import me.utlight.entity.User;
import me.utlight.util.MyBatisUtil;

/*����
	   1. ӳ������ļ��е�����select��佫�ᱻ���档
	
	����2. ӳ������ļ��е�����insert��update��delete����ˢ�»��档
	
	����3. �����ʹ��Least Recently Used��LRU���������ʹ�õģ��㷨���ջء�
	
	����4. ��������ָ����ʱ������ˢ�¡�
	
	����5. �����洢1024������

	cache��ǩ�������ԣ�
	
	<cache 
	eviction="FIFO"  <!--���ղ���Ϊ�Ƚ��ȳ�-->
	flushInterval="60000" <!--�Զ�ˢ��ʱ��60s-->
	size="512" <!--��໺��512�����ö���-->
	readOnly="true"/> <!--ֻ��-->
*/

public class testTwoLevelCache {
	  /*
     * ���Զ�������
     * ʹ��������ͬ��SqlSession����ȥִ����ͬ��ѯ�����Ĳ�ѯ���ڶ��β�ѯʱ�����ٷ���SQL��䣬����ֱ�Ӵӻ�����ȡ������
     */
    @Test
    public void testCache2() {
        String statement = "me.utlight.mapping.userMapper.getUser";
        SqlSessionFactory factory = MyBatisUtil.getSqlSeesionFactory();
        //����������ͬ��SqlSession
        SqlSession session1 = factory.openSession();
        SqlSession session2 = factory.openSession();
        //ʹ�ö�������ʱ��User�����ʵ��һ��Serializable�ӿ�===> User implements Serializable
        User user = session1.selectOne(statement, 1);
        session1.commit();//����Ϊɶ������ط�һ��Ҫ�ύ����֮���������Ż�������
        System.out.println("user="+user);
        
        //����ʹ�õ���������ͬ��SqlSession�������Լ�ʹ��ѯ������ͬ��һ������Ҳ���Ὺ��ʹ��
        User user2 = session2.selectOne(statement, 1);
        //session2.commit();
        System.out.println("user2="+user2);
        
        System.out.println(user==user2);
    }
}
