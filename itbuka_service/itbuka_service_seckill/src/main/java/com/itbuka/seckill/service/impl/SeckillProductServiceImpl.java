package com.itbuka.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.itbuka.seckill.domain.SeckillProduct;
import com.itbuka.seckill.mapper.SeckillProductMapper;
import com.itbuka.seckill.service.SeckillProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【itbuka_seckill_product(秒杀商品表)】的数据库操作Service实现
* @createDate 2024-09-07 11:23:03
*/
@Service
public class SeckillProductServiceImpl extends ServiceImpl<SeckillProductMapper, SeckillProduct>
    implements SeckillProductService {
    @Autowired
    private SeckillProductMapper iSeckillProductMapper;


    @Override
    public List<SeckillProduct> selectAll() {
        LambdaQueryWrapper <SeckillProduct> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SeckillProduct::getIsDelete,0);
        return iSeckillProductMapper.selectList(lqw);

    }

    @Override
    public List<SeckillProduct> selectList(SeckillProduct iSeckillProduct) {
        LambdaQueryWrapper <SeckillProduct> lqw = this.lqw(iSeckillProduct);
        return iSeckillProductMapper.selectList(lqw);

    }

    @Override
    public Integer insert(SeckillProduct iSeckillProduct) {
        return iSeckillProductMapper.insert(iSeckillProduct);

    }

    @Override
    @Transactional
    public Integer delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                SeckillProduct iSeckillProduct = new SeckillProduct();
                iSeckillProduct.setId(Long.parseLong(id));
                iSeckillProduct.setIsDelete(1);
                iSeckillProductMapper.updateById(iSeckillProduct);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }

    }

    @Override
    public Integer update(SeckillProduct iSeckillProduct) {
        return iSeckillProductMapper.updateById(iSeckillProduct);

    }

    @Override
    public Page<SeckillProduct> pageAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<SeckillProduct>)this.selectAll();

    }

    @Override
    public Page<SeckillProduct> pageList(SeckillProduct iSeckillProduct, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<SeckillProduct>)this.selectList(iSeckillProduct);

    }
    public LambdaQueryWrapper lqw(SeckillProduct iSeckillProduct){
        LambdaQueryWrapper <SeckillProduct> lqw = new LambdaQueryWrapper<>();
        if (iSeckillProduct.getId() != null){
            lqw.eq(SeckillProduct::getId,iSeckillProduct.getId());
        }
        if (iSeckillProduct.getSeckillId() != null){
            lqw.eq(SeckillProduct::getSeckillId,iSeckillProduct.getSeckillId());
        }
        if (iSeckillProduct.getProductName() != null){
            lqw.eq(SeckillProduct::getProductName,iSeckillProduct.getProductName());
        }
        if (iSeckillProduct.getProductId() != null){
            lqw.eq(SeckillProduct::getProductId,iSeckillProduct.getProductId());
        }
        if (iSeckillProduct.getProductMoney() != null){
            lqw.eq(SeckillProduct::getProductMoney,iSeckillProduct.getProductMoney());
        }
        if (iSeckillProduct.getNum() != null){
            lqw.eq(SeckillProduct::getNum,iSeckillProduct.getNum());
        }
        if (iSeckillProduct.getSeckillMoney() != null){
            lqw.eq(SeckillProduct::getSeckillMoney,iSeckillProduct.getSeckillMoney());
        }
        if (iSeckillProduct.getMerchantName() != null){
            lqw.eq(SeckillProduct::getMerchantName,iSeckillProduct.getMerchantName());
        }
        if (iSeckillProduct.getActivityTimes() != null){
            lqw.eq(SeckillProduct::getActivityTimes,iSeckillProduct.getActivityTimes());
        }
        if (iSeckillProduct.getIsDelete() != null){
            lqw.eq(SeckillProduct::getIsDelete,iSeckillProduct.getIsDelete());
        }
        if (iSeckillProduct.getCreateTime() != null){
            lqw.eq(SeckillProduct::getCreateTime,iSeckillProduct.getCreateTime());
        }
        if (iSeckillProduct.getUpdateTime() != null){
            lqw.eq(SeckillProduct::getUpdateTime,iSeckillProduct.getUpdateTime());
        }
        return lqw;
    }

}




