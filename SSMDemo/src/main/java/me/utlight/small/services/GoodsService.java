package me.utlight.small.services;

import java.util.List;

import me.utlight.small.entities.Goods;

public interface GoodsService {

    //��ҳ
    List<Goods> getGoodsPager(int pageNO, int size);

    //��õ�����Ʒ����
    Goods getGoodsById(int id);
    
    //�����Ʒ����
    int getGoodsCount();

    //���
    int add(Goods entity);

    //ɾ������
    int delete(int id);

    //ɾ�����
    int deletes(int[] ids);

    //����
    int update(Goods entity);
}
