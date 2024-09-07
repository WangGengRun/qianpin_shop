package com.itbuka.seckill.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbuka.seckill.domain.SeckillProduct;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【itbuka_seckill_product(秒杀商品表)】的数据库操作Service
* @createDate 2024-09-07 11:23:03
*/
public interface SeckillProductService extends IService<SeckillProduct> {
    /**
     * 查询全部
     * @param
     * @return
     */
    List<SeckillProduct> selectAll();

    /**
     * 条件查询
     * @param iSeckillProduct
     * @return
     */
    List<SeckillProduct> selectList(SeckillProduct iSeckillProduct);

    /**
     * 插入一条数据
     * @param iSeckillProduct
     * @return
     */
    Integer insert(SeckillProduct iSeckillProduct);

    /**
     * 根据id删除数据
     * @param ids
     * @return
     */
    Integer delete(String ids);

    /**
     * 更新数据
     * @param iSeckillProduct
     * @return
     */
    Integer update(SeckillProduct iSeckillProduct);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<SeckillProduct> pageAll(Integer page, Integer size);

    /***
     * 多条件分页查询
     * @param iSeckillProduct
     * @param page
     * @param size
     * @return
     */
    Page<SeckillProduct> pageList(SeckillProduct iSeckillProduct, Integer page, Integer size);

}
