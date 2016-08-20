package me.utlight.small.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.utlight.small.entities.Goods;

public interface GoodsDAO {
	
	//分页获取商品信息
	public List<Goods> getGoodsPager(@Param("skip") int skip,@Param("size")int size);
	
	//根据商品id获得商品
	public Goods getGoodsById(int id);
	
	//获得商品总数
	public int getGoodsCount();
	
	//增加商品
	public int add(Goods entity);
	
	//删除商品
	public int delete(int id);
	
	//修改商品
	public int update(Goods entity);
}
