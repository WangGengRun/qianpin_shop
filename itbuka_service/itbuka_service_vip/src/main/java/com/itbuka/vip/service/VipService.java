package com.itbuka.vip.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.itbuka.goods.domain.Vip;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【goods_vip】的数据库操作Service
* @createDate 2024-07-29 18:50:16
*/
public interface VipService extends IService<Vip> {


    List<Vip> selectList(Vip vip);

    List<Vip> selAll();

    int insert(Vip vip);

    void delete(String ids);

    Integer status(Long id, Integer status);

    int update(Vip vip);
}
