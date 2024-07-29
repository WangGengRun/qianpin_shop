package com.itbuka.vip.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.itbuka.goods.domain.VipPointHistory;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【vip_point_history】的数据库操作Service
* @createDate 2024-07-29 21:14:21
*/
public interface VipPointHistoryService extends IService<VipPointHistory> {

    List<VipPointHistory> selectAll();

     List<VipPointHistory> selectList(VipPointHistory vipPointsHistory);

    int insert(VipPointHistory vipPointsHistory);

    int delete(String ids);

    int update(VipPointHistory vipPointsHistory);
}
