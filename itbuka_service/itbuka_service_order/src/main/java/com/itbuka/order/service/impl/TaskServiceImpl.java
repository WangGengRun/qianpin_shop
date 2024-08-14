package com.itbuka.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.itbuka.order.domain.Task;
import com.itbuka.order.mapper.TaskMapper;
import com.itbuka.order.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【tb_task】的数据库操作Service实现
* @createDate 2024-08-14 10:29:20
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService {
@Autowired
private TaskMapper iTaskMapper;
    @Override
    public List<Task> selectAll() {
        LambdaQueryWrapper <Task> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Task::getStatus,"0");
        return iTaskMapper.selectList(lqw);

    }

    @Override
    public List<Task> selectList(Task iTask) {
        LambdaQueryWrapper <Task> lqw = this.lqw(iTask);
        return iTaskMapper.selectList(lqw);

    }

    @Override
    public Integer insert(Task iTask) {
        return iTaskMapper.insert(iTask);

    }

    @Override
    @Transactional
    public Integer delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                Task iTask = new Task();
                iTask.setId(Long.parseLong(id));
                iTask.setStatus("1");
                iTaskMapper.updateById(iTask);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }

    }

    @Override
    public Integer update(Task iTask) {
        return iTaskMapper.updateById(iTask);

    }

    @Override
    public Page<Task> pageAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Task>)this.selectAll();

    }

    @Override
    public Page<Task> pageList(Task iTask, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Task>)this.selectList(iTask);

    }
    public LambdaQueryWrapper lqw(Task iTask) {
        LambdaQueryWrapper<Task> lqw = new LambdaQueryWrapper<>();
        if (iTask.getId() != null) {
            lqw.eq(Task::getId, iTask.getId());
        }
        if (iTask.getCreateTime() != null) {
            lqw.eq(Task::getCreateTime, iTask.getCreateTime());
        }
        if (iTask.getUpdateTime() != null) {
            lqw.eq(Task::getUpdateTime, iTask.getUpdateTime());
        }
        if (iTask.getDeleteTime() != null) {
            lqw.eq(Task::getDeleteTime, iTask.getDeleteTime());
        }
        if (iTask.getTaskType() != null) {
            lqw.eq(Task::getTaskType, iTask.getTaskType());
        }
        if (iTask.getMqExchange() != null) {
            lqw.eq(Task::getMqExchange, iTask.getMqExchange());
        }
        if (iTask.getMqRoutingkey() != null) {
            lqw.eq(Task::getMqRoutingkey, iTask.getMqRoutingkey());
        }
        if (iTask.getRequestBody() != null) {
            lqw.eq(Task::getRequestBody, iTask.getRequestBody());
        }
        if (iTask.getStatus() != null) {
            lqw.eq(Task::getStatus, iTask.getStatus());
        }
        if (iTask.getErrormsg() != null) {
            lqw.eq(Task::getErrormsg, iTask.getErrormsg());
        }
        return lqw;
    }
    }




