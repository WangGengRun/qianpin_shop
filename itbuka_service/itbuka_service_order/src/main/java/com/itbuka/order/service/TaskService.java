package com.itbuka.order.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbuka.order.domain.Task;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【tb_task】的数据库操作Service
* @createDate 2024-08-14 10:29:20
*/
public interface TaskService {
    /**
     * 查询全部
     * @param
     * @return
     */
    List<Task> selectAll();

    /**
     * 条件查询
     * @param iTask
     * @return
     */
    List<Task> selectList(Task iTask);

    /**
     * 插入一条数据
     * @param iTask
     * @return
     */
    Integer insert(Task iTask);

    /**
     * 根据id删除数据
     * @param ids
     * @return
     */
    Integer delete(String ids);

    /**
     * 更新数据
     * @param iTask
     * @return
     */
    Integer update(Task iTask);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Task> pageAll(Integer page, Integer size);

    /***
     * 多条件分页查询
     * @param iTask
     * @param page
     * @param size
     * @return
     */
    Page<Task> pageList(Task iTask, Integer page, Integer size);

}
