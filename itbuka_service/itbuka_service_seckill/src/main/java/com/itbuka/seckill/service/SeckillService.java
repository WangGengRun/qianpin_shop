package com.itbuka.seckill.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbuka.seckill.domain.Seckill;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【itbuka_seckill(秒杀表)】的数据库操作Service
* @createDate 2024-09-07 11:20:44
*/
public interface SeckillService extends IService<Seckill> {
    /**
     * 查询全部
     * @param
     * @return
     */
    List<Seckill> selectAll();

    /**
     * 条件查询
     * @param iSeckill
     * @return
     */
    List<Seckill> selectList(Seckill iSeckill);

    /**
     * 插入一条数据
     * @param iSeckill
     * @return
     */
    Integer insert(Seckill iSeckill);

    /**
     * 根据id删除数据
     * @param ids
     * @return
     */
    Integer delete(String ids);

    /**
     * 更新数据
     * @param iSeckill
     * @return
     */
    Integer update(Seckill iSeckill);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Seckill> pageAll(Integer page, Integer size);

    /***
     * 多条件分页查询
     * @param iSeckill
     * @param page
     * @param size
     * @return
     */
    Page<Seckill> pageList(Seckill iSeckill, Integer page, Integer size);

}
