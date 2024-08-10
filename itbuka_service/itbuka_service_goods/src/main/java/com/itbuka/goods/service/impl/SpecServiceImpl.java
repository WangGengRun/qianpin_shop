package com.itbuka.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.itbuka.goods.domain.Spec;
import com.itbuka.goods.mapper.SpecMapper;
import com.itbuka.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【goods_spec(规格表)】的数据库操作Service实现
* @createDate 2024-08-10 12:48:58
*/
@Service
public class SpecServiceImpl extends ServiceImpl<SpecMapper, Spec>
    implements SpecService {
    @Autowired
    private SpecMapper iSpecMapper;

    @Override
    public Page<Spec> pageAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Spec>)this.selectAll();
    }

    @Override
    public List<Spec> selectAll() {
        LambdaQueryWrapper <Spec> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Spec::getIsDelete,0);
        return iSpecMapper.selectList(lqw);
    }

    @Override
    public List<Spec> selectList(Spec iSpec) {
        LambdaQueryWrapper <Spec> lqw = this.lqw(iSpec);
        return iSpecMapper.selectList(lqw);
    }

    @Override
    public int insert(Spec iSpec) {
        return iSpecMapper.insert(iSpec);
    }

    @Override
    @Transactional
    public int delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                Spec iSpec = new Spec();
                iSpec.setId(Long.parseLong(id));
                iSpec.setIsDelete(1);
                iSpecMapper.updateById(iSpec);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public int update(Spec iSpec) {
        return iSpecMapper.updateById(iSpec);
    }

    @Override
    public Page<Spec> pageList(Spec iSpec, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Spec>)this.selectList(iSpec);
    }
    public LambdaQueryWrapper lqw(Spec iSpec){
        LambdaQueryWrapper <Spec> lqw = new LambdaQueryWrapper<>();
        if (iSpec.getId() != null){
            lqw.eq(Spec::getId,iSpec.getId());
        }
        if (iSpec.getSpecName() != null){
            lqw.eq(Spec::getSpecName,iSpec.getSpecName());
        }
        if (iSpec.getSpecValue() != null){
            lqw.eq(Spec::getSpecValue,iSpec.getSpecValue());
        }
        if (iSpec.getIsDelete() != null){
            lqw.eq(Spec::getIsDelete,iSpec.getIsDelete());
        }
        if (iSpec.getCreateTime() != null){
            lqw.eq(Spec::getCreateTime,iSpec.getCreateTime());
        }
        if (iSpec.getUpdateTime() != null){
            lqw.eq(Spec::getUpdateTime,iSpec.getUpdateTime());
        }
        return lqw;
    }
}




