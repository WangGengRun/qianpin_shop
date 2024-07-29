package com.itbuka.vip.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbuka.goods.domain.VipFunds;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【vip_funds】的数据库操作Service
* @createDate 2024-07-29 20:16:18
*/
public interface VipFundsService extends IService<VipFunds> {
//
//    List<VipFunds> selectAll();

    List<VipFunds> selectList(VipFunds vipFunds);

    int insert(VipFunds vipFunds);

    int delete(String ids);

    int update(VipFunds vipFunds);

//    Page<VipFunds> pageAll(Integer page, Integer size);

    Page<VipFunds> pageList(VipFunds vipFunds, Integer page, Integer size);

    Page<VipFunds> pageAll(Integer page, Integer size);
}
