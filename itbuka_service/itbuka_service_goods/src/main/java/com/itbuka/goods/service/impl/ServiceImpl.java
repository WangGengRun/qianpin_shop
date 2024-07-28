package com.itbuka.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.itbuka.goods.domain.Brand;
import com.itbuka.goods.mapper.BrandMapper;
import com.itbuka.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements BrandService {
    @Autowired
    private BrandMapper iBrandMapper;

    @Override
    public List<Brand> selectAll() {
        LambdaQueryWrapper<Brand> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Brand::getIsDelete,0);
        return iBrandMapper.selectList(lqw);    }

    @Override
    public List<Brand> selectList(Brand iBrand) {
        LambdaQueryWrapper <Brand> lqw = this.lqw(iBrand);
        return iBrandMapper.selectList(lqw);
    }

    @Override
    public Integer insert(Brand iBrand) {
        return iBrandMapper.insert(iBrand);
    }

    @Override
    public Integer delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                Brand iBrand = new Brand();
                iBrand.setId(Long.parseLong(id));
                iBrand.setIsDelete(1);
                iBrandMapper.updateById(iBrand);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public Integer update(Brand iBrand) {
        return iBrandMapper.updateById(iBrand);

    }

    @Override
    public Page<Brand> pageAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Brand>)this.selectAll();
    }

    @Override
    public Page<Brand> pageList(Brand iBrand, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Brand>)this.selectList(iBrand);
    }

    @Override
    public Integer status(Long id, Integer status) {
        Brand brand = new Brand();
        brand.setId(id);
        brand.setStatus(status);
        return iBrandMapper.updateById(brand);
    }

    @Override
    public Integer status(Long id) {
        Brand brand = iBrandMapper.selectById(id);
        if (brand == null){
            return -1;
        }
        if (brand.getStatus() == 1) {
            brand.setStatus(0);
        }else {
            brand.setStatus(1);
        }
        return iBrandMapper.updateById(brand);
    }
    public LambdaQueryWrapper lqw(Brand iBrand){
        LambdaQueryWrapper <Brand> lqw = new LambdaQueryWrapper<>();
        if (iBrand.getId() != null){
            lqw.eq(Brand::getId,iBrand.getId());
        }
        if (iBrand.getBrandName() != null){
            lqw.eq(Brand::getBrandName,iBrand.getBrandName());
        }
        if (iBrand.getBrandLogo() != null){
            lqw.eq(Brand::getBrandLogo,iBrand.getBrandLogo());
        }
        if (iBrand.getStatus() != null){
            lqw.eq(Brand::getStatus,iBrand.getStatus());
        }
        if (iBrand.getIsDelete() != null){
            lqw.eq(Brand::getIsDelete,iBrand.getIsDelete());
        }
        if (iBrand.getCreateTime() != null){
            lqw.eq(Brand::getCreateTime,iBrand.getCreateTime());
        }
        if (iBrand.getUpdateTime() != null){
            lqw.eq(Brand::getUpdateTime,iBrand.getUpdateTime());
        }
        return lqw;
    }
}
