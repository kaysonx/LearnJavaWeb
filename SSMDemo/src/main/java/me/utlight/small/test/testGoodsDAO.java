package me.utlight.small.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import junit.framework.Assert;
import me.utlight.small.entities.Goods;
import me.utlight.small.mapper.GoodsDAO;
import me.utlight.small.utils.MyBatisUtil;

public class testGoodsDAO {

	@Test
	public void getGoodsPagerTest() {

		int skip = 4;
		int size = 2;

		SqlSession session = MyBatisUtil.getSession();

		try {
			GoodsDAO bookdao = session.getMapper(GoodsDAO.class);
			List<Goods> goods = bookdao.getGoodsPager(skip, size);
			Assert.assertEquals(2, goods.size());
		} finally {
			session.close();
		}
	}
	

    @Test
    public void getGoodsByIdTest() {
        SqlSession session=MyBatisUtil.getSession();
        try {
            GoodsDAO bookdao=session.getMapper(GoodsDAO.class);
            Goods goods=bookdao.getGoodsById(1);
            Assert.assertEquals(1, goods.getId());
        } finally {
            session.close();
        }
    }
    
    @Test
    public void getGoodsCountTest() {
        SqlSession session=MyBatisUtil.getSession();
        try {
            GoodsDAO bookdao=session.getMapper(GoodsDAO.class);
            Assert.assertEquals(9, bookdao.getGoodsCount());
        } finally {
            session.close();
        }
    }
    
    @Test
    public void addTest() {
        SqlSession session=MyBatisUtil.getSession();
        try {
            Goods entity=new Goods();
            entity.setName("Œ“ «test");
            entity.setPrice(250);
            entity.setPicture("666.jpg");
            GoodsDAO bookdao=session.getMapper(GoodsDAO.class);
            Assert.assertEquals(1, bookdao.add(entity));
        } finally {
            session.close();
        }
    }

    @Test
    public void deleteTest() {
        SqlSession session=MyBatisUtil.getSession();
        try {
            GoodsDAO bookdao=session.getMapper(GoodsDAO.class);
            Assert.assertEquals(1, bookdao.delete(12));
        } finally {
            session.close();
        }
    }

    @Test
    public void update() {
        SqlSession session=MyBatisUtil.getSession();
        try {
            GoodsDAO bookdao=session.getMapper(GoodsDAO.class);
            Goods entity=bookdao.getGoodsById(8);
            entity.setName("test changed");
            entity.setPrice(2.5);
            entity.setPicture("666666.jpg");

            Assert.assertEquals(1, bookdao.update(entity));
        } finally {
            session.close();
        }
    }

}
