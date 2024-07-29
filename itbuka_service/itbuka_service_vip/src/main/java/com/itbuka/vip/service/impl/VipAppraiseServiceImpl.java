package com.itbuka.vip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.itbuka.goods.domain.VipAppraise;
import com.itbuka.vip.mapper.VipAppraiseMapper;
import com.itbuka.vip.service.VipAppraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【vip_appraise】的数据库操作Service实现
* @createDate 2024-07-29 20:01:09
*/
@Service
public class VipAppraiseServiceImpl extends ServiceImpl<VipAppraiseMapper, VipAppraise>
    implements VipAppraiseService {
@Autowired
private VipAppraiseMapper vipAppraiseMapper;
    @Override
    public List<VipAppraise> selectAll() {
        LambdaQueryWrapper<VipAppraise> lqw = new LambdaQueryWrapper<>();
        lqw.eq(VipAppraise::getIsDelete, 0);
        return vipAppraiseMapper.selectList(lqw);
    }

    @Override
    public int insert(VipAppraise vipAppraise) {
        return vipAppraiseMapper.insert(vipAppraise);
    }

    @Override
    public int delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                VipAppraise vipEvaluate = new VipAppraise();
                vipEvaluate.setId((int) Long.parseLong(id));
                vipEvaluate.setIsDelete(1);
                vipAppraiseMapper.updateById(vipEvaluate);
            }
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int update(VipAppraise vipAppraise) {
        return vipAppraiseMapper.updateById(vipAppraise);
    }
}




