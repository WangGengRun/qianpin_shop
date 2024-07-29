package com.itbuka.vip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itbuka.goods.domain.Vip;
import com.itbuka.vip.mapper.VipMapper;
import com.itbuka.vip.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【goods_vip】的数据库操作Service实现
* @createDate 2024-07-29 18:50:16
*/
@Service
public class VipServiceImpl extends ServiceImpl<VipMapper, Vip>
    implements VipService {
@Autowired
private VipMapper vipMapper;
    @Override
    public List<Vip> selectList(Vip vip) {
        LambdaQueryWrapper<Vip> lqw = this.lqw(vip);
        return vipMapper.selectList(lqw);
    }

    @Override
    public List<Vip> selAll() {
        LambdaQueryWrapper<Vip> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Vip::getVipDel, 0);
        return vipMapper.selectList(lqw);
    }

    @Override
    public int insert(Vip vip) {
        return vipMapper.insert(vip);
    }

    @Override
    public void delete(String ids) {

    }

    @Override
    public Integer status(Long id, Integer status) {
        return null;
    }

    @Override
    public int update(Vip vip) {

        return vipMapper.updateById(vip);
    }


    public LambdaQueryWrapper lqw(Vip vip){
        LambdaQueryWrapper <Vip> lqw = new LambdaQueryWrapper<>();
        if (vip.getId() != null){
            lqw.eq(Vip::getId,vip.getId());
        }
        if (vip.getVipName()!= null){
            lqw.eq(Vip::getVipName,vip.getVipName());
        }
        if (vip.getVipNickname() != null){
            lqw.eq(Vip::getVipNickname,vip.getVipNickname());
        }
        if (vip.getVipPhone() != null){
            lqw.eq(Vip::getVipPhone,vip.getVipPhone());
        }
        if (vip.getVipNum() != null){
            lqw.eq(Vip::getVipNum,vip.getVipNum());
        }
        if (vip.getVipTime() != null){
            lqw.eq(Vip::getVipTime,vip.getVipTime());
        }
        if (vip.getVipDel() != null){
            lqw.eq(Vip::getVipDel,vip.getVipDel());
        }
        return lqw;
    }
}




