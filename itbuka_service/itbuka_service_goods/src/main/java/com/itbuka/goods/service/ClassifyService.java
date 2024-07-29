package com.itbuka.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.goods.domain.Classify;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【goods_classify(分类表)】的数据库操作Service
 * @createDate 2024-07-27 11:34:17
 */
public interface ClassifyService {
    /**
     * 查询全部
     * @param
     * @return
     */
    List<Classify> selectAll();

    /**
     * 条件查询
     * @param iClassify
     * @return
     */
    List<Classify> selectList(Classify iClassify);

    /**
     * 插入一条数据
     * @param iClassify
     * @return
     */
    Integer insert(Classify iClassify);

    /**
     * 根据id删除数据
     * @param ids
     * @return
     */
    Integer delete(String ids);

    /**
     * 更新数据
     * @param iClassify
     * @return
     */
    Integer update(Classify iClassify);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Classify> pageAll(Integer page, Integer size);

    /***
     * 多条件分页查询
     * @param iClassify
     * @param page
     * @param size
     * @return
     */
    Page<Classify> pageList(Classify iClassify, Integer page, Integer size);

    /**
     * 启动、停用
     */
    Integer updateStatus(Long id, Integer status);

    /**
     * 根据商品分类名称查询品牌列表
     * @param categoryName
     * @return
     */
    public List<Map> findListByCategoryName(String categoryName);


}
