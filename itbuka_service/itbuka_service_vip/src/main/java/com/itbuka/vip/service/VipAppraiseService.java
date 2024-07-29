package com.itbuka.vip.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.itbuka.goods.domain.VipAppraise;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【vip_appraise】的数据库操作Service
* @createDate 2024-07-29 20:01:09
*/
public interface VipAppraiseService extends IService<VipAppraise> {

    List<VipAppraise> selectAll();

    int insert(VipAppraise vipAppraise);

    int delete(String ids);

    int update(VipAppraise vipAppraise);
}
