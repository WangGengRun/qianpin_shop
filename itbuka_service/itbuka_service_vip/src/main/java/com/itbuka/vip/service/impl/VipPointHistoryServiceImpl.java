package com.itbuka.vip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.itbuka.goods.domain.VipPointHistory;
import com.itbuka.vip.mapper.VipPointHistoryMapper;
import com.itbuka.vip.service.VipPointHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【vip_point_history】的数据库操作Service实现
* @createDate 2024-07-29 21:14:21
*/
@Service
public class VipPointHistoryServiceImpl extends ServiceImpl<VipPointHistoryMapper, VipPointHistory>
    implements VipPointHistoryService {
    @Autowired
    private VipPointHistoryMapper vipPointHistoryMapper;

    @Override
    public List<VipPointHistory> selectAll() {
        LambdaQueryWrapper <VipPointHistory> lqw = new LambdaQueryWrapper<>();
        lqw.eq(VipPointHistory::getIsDelete,0);
        return vipPointHistoryMapper.selectList(lqw);
    }

    @Override
    public List<VipPointHistory> selectList(VipPointHistory vipPointsHistory) {
        LambdaQueryWrapper<VipPointHistory> lqw = this.lqw(vipPointsHistory);
        return vipPointHistoryMapper.selectList(lqw);


    }

    @Override
    public int insert(VipPointHistory vipPointsHistory) {
        return vipPointHistoryMapper.insert(vipPointsHistory);
    }

    @Override
    public int delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                VipPointHistory vipPointsHistory = new VipPointHistory();
                vipPointsHistory.setId((int) Long.parseLong(id));
                vipPointsHistory.setIsDelete(1);
                vipPointHistoryMapper.updateById(vipPointsHistory);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public int update(VipPointHistory vipPointsHistory) {
        return vipPointHistoryMapper.updateById(vipPointsHistory);
    }
    public LambdaQueryWrapper<VipPointHistory> lqw(VipPointHistory vipPointsHistory){
        LambdaQueryWrapper <VipPointHistory> lqw = new LambdaQueryWrapper<>();
        if (vipPointsHistory.getId() != null){
            lqw.eq(VipPointHistory::getId,vipPointsHistory.getId());
        }
        if (vipPointsHistory.getVipName() != null){
            lqw.eq(VipPointHistory::getVipName,vipPointsHistory.getVipName());
        }
        if (vipPointsHistory.getOperateText() != null){
            lqw.eq(VipPointHistory::getOperateText,vipPointsHistory.getOperateText());
        }

        return lqw;
    }

}




