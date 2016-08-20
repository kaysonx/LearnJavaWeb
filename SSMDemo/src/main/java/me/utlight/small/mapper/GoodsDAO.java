package me.utlight.small.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.utlight.small.entities.Goods;

public interface GoodsDAO {
	
	//��ҳ��ȡ��Ʒ��Ϣ
	public List<Goods> getGoodsPager(@Param("skip") int skip,@Param("size")int size);
	
	//������Ʒid�����Ʒ
	public Goods getGoodsById(int id);
	
	//�����Ʒ����
	public int getGoodsCount();
	
	//������Ʒ
	public int add(Goods entity);
	
	//ɾ����Ʒ
	public int delete(int id);
	
	//�޸���Ʒ
	public int update(Goods entity);
}
