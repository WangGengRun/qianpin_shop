package com.itbuka.vip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.itbuka.goods.domain.VipFunds;
import com.itbuka.vip.mapper.VipFundsMapper;
import com.itbuka.vip.service.VipFundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【vip_funds】的数据库操作Service实现
* @createDate 2024-07-29 20:16:18
*/
@Service
public class VipFundsServiceImpl extends ServiceImpl<VipFundsMapper, VipFunds>
    implements VipFundsService {
@Autowired
private VipFundsMapper vipFundsMapper;

    @Override
    public List<VipFunds> selectList(VipFunds vipFunds) {
        LambdaQueryWrapper<VipFunds> lqw = this.lqw(vipFunds);
        return vipFundsMapper.selectList(lqw);
    }

    @Override
    public int insert(VipFunds vipFunds) {
        return vipFundsMapper.insert(vipFunds);
    }

    @Override
    public int delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                VipFunds vipFunds = new VipFunds();
                vipFunds.setId((int) Long.parseLong(id));
//                vipFunds.;
                vipFundsMapper.updateById(vipFunds);
            }
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int update(VipFunds vipFunds) {
        return vipFundsMapper.updateById(vipFunds);
    }

//    @Override
//    public Page<VipFunds> pageAll(Integer page, Integer size) {
//        return
//    }

    @Override
    public Page<VipFunds> pageList(VipFunds vipFunds, Integer page, Integer size) {
        return null;
    }

    @Override
    public Page<VipFunds> pageAll(Integer page, Integer size) {
        return null;
    }

    public LambdaQueryWrapper<VipFunds> lqw(VipFunds vipFunds) {
        LambdaQueryWrapper<VipFunds> lqw = new LambdaQueryWrapper<>();
        if (vipFunds.getId() != null) {
            lqw.eq(VipFunds::getId, vipFunds.getId());
        }
        if (vipFunds.getVipName() != null) {
            lqw.eq(VipFunds::getVipName, vipFunds.getVipName());
        }

        return lqw;
    }
}




