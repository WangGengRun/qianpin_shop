package com.itbuka.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.itbuka.seckill.domain.Seckill;
import com.itbuka.seckill.mapper.SeckillMapper;
import com.itbuka.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【itbuka_seckill(秒杀表)】的数据库操作Service实现
* @createDate 2024-09-07 11:20:44
*/
@Service
public class SeckillServiceImpl extends ServiceImpl<SeckillMapper, Seckill>
    implements SeckillService {
    @Autowired
    private SeckillMapper iSeckillMapper;

    @Override
    public List<Seckill> selectAll() {
        LambdaQueryWrapper <Seckill> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Seckill::getIsDelete,0);
        return iSeckillMapper.selectList(lqw);

    }

    @Override
    public List<Seckill> selectList(Seckill iSeckill) {
        LambdaQueryWrapper <Seckill> lqw = this.lqw(iSeckill);
        return iSeckillMapper.selectList(lqw);

    }

    @Override
    public Integer insert(Seckill iSeckill) {
        return iSeckillMapper.insert(iSeckill);

    }

    @Override
    @Transactional
    public Integer delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                Seckill iSeckill = new Seckill();
                iSeckill.setId(Long.parseLong(id));
                iSeckill.setIsDelete(1);
                iSeckillMapper.updateById(iSeckill);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }

    }

    @Override
    public Integer update(Seckill iSeckill) {
        return iSeckillMapper.updateById(iSeckill);

    }

    @Override
    public Page<Seckill> pageAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Seckill>)this.selectAll();

    }

    @Override
    public Page<Seckill> pageList(Seckill iSeckill, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Seckill>)this.selectList(iSeckill);

    }
    public LambdaQueryWrapper lqw(Seckill iSeckill){
        LambdaQueryWrapper <Seckill> lqw = new LambdaQueryWrapper<>();
        if (iSeckill.getId() != null){
            lqw.eq(Seckill::getId,iSeckill.getId());
        }
        if (iSeckill.getSeckillName() != null){
            lqw.eq(Seckill::getSeckillName,iSeckill.getSeckillName());
        }
        if (iSeckill.getStartTime() != null){
            lqw.eq(Seckill::getStartTime,iSeckill.getStartTime());
        }
        if (iSeckill.getEndTime() != null){
            lqw.eq(Seckill::getEndTime,iSeckill.getEndTime());
        }
        if (iSeckill.getStatus() != null){
            lqw.eq(Seckill::getStatus,iSeckill.getStatus());
        }
        if (iSeckill.getSeckillRule() != null){
            lqw.eq(Seckill::getSeckillRule,iSeckill.getSeckillRule());
        }
        if (iSeckill.getFrequency() != null){
            lqw.eq(Seckill::getFrequency,iSeckill.getFrequency());
        }
        if (iSeckill.getIsDelete() != null){
            lqw.eq(Seckill::getIsDelete,iSeckill.getIsDelete());
        }
        if (iSeckill.getCreateTime() != null){
            lqw.eq(Seckill::getCreateTime,iSeckill.getCreateTime());
        }
        if (iSeckill.getUpdateTime() != null){
            lqw.eq(Seckill::getUpdateTime,iSeckill.getUpdateTime());
        }
        return lqw;
    }

}




