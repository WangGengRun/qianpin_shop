package com.itbuka.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.goods.domain.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> selectAll();

    /**
     * 条件查询
     * @param iBrand
     * @return
     */
    List<Brand> selectList(Brand iBrand);

    /**
     * 插入一条数据
     * @param iBrand
     * @return
     */
    Integer insert(Brand iBrand);

    /**
     * 根据id删除数据
     * @param ids
     * @return
     */
    Integer delete(String ids);

    /**
     * 更新数据
     * @param iBrand
     * @return
     */
    Integer update(Brand iBrand);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Brand> pageAll(Integer page, Integer size);

    /***
     * 多条件分页查询
     * @param iBrand
     * @param page
     * @param size
     * @return
     */
    Page<Brand> pageList(Brand iBrand, Integer page, Integer size);
    /**
     * 品牌启用、停用
     * @param id
     */
    Integer status(Long id,Integer status);
    /***
     * 品牌启用、停用
     * @param id
     */
    Integer status(Long id);
}
