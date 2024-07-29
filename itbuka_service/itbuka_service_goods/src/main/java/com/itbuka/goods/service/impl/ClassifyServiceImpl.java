package com.itbuka.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.itbuka.goods.domain.Classify;
import com.itbuka.goods.mapper.ClassifyMapper;
import com.itbuka.goods.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【goods_classify(分类表)】的数据库操作Service实现
 * @createDate 2024-07-27 11:34:17
 */
@Service
public class ClassifyServiceImpl implements ClassifyService {
    @Autowired
    private ClassifyMapper iClassifyMapper;

    @Override
    public List<Classify> selectAll() {
        LambdaQueryWrapper<Classify> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Classify::getIsDelete,0);
        return iClassifyMapper.selectList(lqw);
    }

    @Override
    public List<Classify> selectList(Classify iClassify) {
        LambdaQueryWrapper <Classify> lqw = this.lqw(iClassify);
        return iClassifyMapper.selectList(lqw);
    }

    @Override
    public Integer insert(Classify iClassify) {
        return iClassifyMapper.insert(iClassify);
    }

    @Override
    public Integer delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                Classify iClassify = new Classify();
                iClassify.setId(Long.parseLong(id));
                iClassify.setIsDelete(1);
                iClassifyMapper.updateById(iClassify);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public Integer update(Classify iClassify) {
        return iClassifyMapper.updateById(iClassify);
    }

    @Override
    public Page<Classify> pageAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Classify>)this.selectAll();
    }

    @Override
    public Page<Classify> pageList(Classify iClassify, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Classify>)this.selectList(iClassify);
    }

    @Override
    public Integer updateStatus(Long id, Integer status) {
        Classify classify = new Classify();
        classify.setId(id);
        classify.setStatus(status);
        classify.setUpdateTime(new Date());
        return iClassifyMapper.updateById(classify);
    }

    @Override
    public List<Map> findListByCategoryName(String categoryName) {
        return iClassifyMapper.findListByCategoryName(categoryName);
    }
    public LambdaQueryWrapper lqw(Classify iClassify){
        LambdaQueryWrapper <Classify> lqw = new LambdaQueryWrapper<>();
        if (iClassify.getId() != null){
            lqw.eq(Classify::getId,iClassify.getId());
        }
        if (iClassify.getClassifyName() != null){
            lqw.eq(Classify::getClassifyName,iClassify.getClassifyName());
        }
        if (iClassify.getClassifyLogo() != null){
            lqw.eq(Classify::getClassifyLogo,iClassify.getClassifyLogo());
        }
        if (iClassify.getSeq() != null){
            lqw.eq(Classify::getSeq,iClassify.getSeq());
        }
        if (iClassify.getStatus() != null){
            lqw.eq(Classify::getStatus,iClassify.getStatus());
        }
        if (iClassify.getIsDelete() != null){
            lqw.eq(Classify::getIsDelete,iClassify.getIsDelete());
        }
        if (iClassify.getCreateTime() != null){
            lqw.eq(Classify::getCreateTime,iClassify.getCreateTime());
        }
        if (iClassify.getUpdateTime() != null){
            lqw.eq(Classify::getUpdateTime,iClassify.getUpdateTime());
        }
        if (iClassify.getCommission() != null){
            lqw.eq(Classify::getCommission,iClassify.getCommission());
        }
        if (iClassify.getIsuperiorId() != null){
            lqw.eq(Classify::getIsuperiorId,iClassify.getIsuperiorId());
        }
        return lqw;
    }
}
