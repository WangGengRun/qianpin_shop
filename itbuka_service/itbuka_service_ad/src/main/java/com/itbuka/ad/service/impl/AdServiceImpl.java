package com.itbuka.ad.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.itbuka.ad.domain.Ad;
import com.itbuka.ad.mapper.AdMapper;
import com.itbuka.ad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【ad(广告表)】的数据库操作Service实现
* @createDate 2024-08-03 09:45:17
*/
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad>
    implements AdService {

    @Autowired
    private AdMapper iAdMapper;
    @Override
    public List<Ad> selectAll() {
        LambdaQueryWrapper <Ad> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Ad::getIsDelete,0);
        return iAdMapper.selectList(lqw);
    }

    @Override
    public List<Ad> selectList(Ad iAd) {
        LambdaQueryWrapper <Ad> lqw = this.lqw(iAd);
        return iAdMapper.selectList(lqw);
    }

    @Override
    public int insert(Ad iAd) {
        return iAdMapper.insert(iAd);
    }

    @Override
    @Transactional
    public int delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                Ad iAd = new Ad();
                iAd.setId(Long.parseLong(id));
                iAd.setIsDelete(1);
                iAdMapper.updateById(iAd);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public int update(Ad iAd) {
        return iAdMapper.updateById(iAd);
    }

    @Override
    public Page<Ad> pageAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Ad>)this.selectAll();
    }

    @Override
    public Page<Ad> pageList(Ad iAd, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Ad>)this.selectList(iAd);
    }
    public LambdaQueryWrapper lqw(Ad iAd){
        LambdaQueryWrapper <Ad> lqw = new LambdaQueryWrapper<>();
        if (iAd.getId() != null){
            lqw.eq(Ad::getId,iAd.getId());
        }
        if (iAd.getName() != null){
            lqw.eq(Ad::getName,iAd.getName());
        }
        if (iAd.getPosition() != null){
            lqw.eq(Ad::getPosition,iAd.getPosition());
        }
        if (iAd.getStartTime() != null){
            lqw.eq(Ad::getStartTime,iAd.getStartTime());
        }
        if (iAd.getEndTime() != null){
            lqw.eq(Ad::getEndTime,iAd.getEndTime());
        }
        if (iAd.getStatus() != null){
            lqw.eq(Ad::getStatus,iAd.getStatus());
        }
        if (iAd.getImage() != null){
            lqw.eq(Ad::getImage,iAd.getImage());
        }
        if (iAd.getUrl() != null){
            lqw.eq(Ad::getUrl,iAd.getUrl());
        }
        if (iAd.getRemarks() != null){
            lqw.eq(Ad::getRemarks,iAd.getRemarks());
        }
        if (iAd.getIsDelete() != null){
            lqw.eq(Ad::getIsDelete,iAd.getIsDelete());
        }
        if (iAd.getCreateTime() != null){
            lqw.eq(Ad::getCreateTime,iAd.getCreateTime());
        }
        if (iAd.getUpdateTime() != null){
            lqw.eq(Ad::getUpdateTime,iAd.getUpdateTime());
        }
        return lqw;
    }
}




