package com.itbuka.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.Result;
import com.itbuka.order.domain.Task;
import com.itbuka.order.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Task")
public class TaskController {
    @Autowired
    private TaskService iTaskService;


    /**
     * 查询全部
     * @param
     * @return
     */
    @GetMapping("/selectAll")
    public Result<List<Task>> selectAll(){
        return Result.ok("查询成功",iTaskService.selectAll());
    }


    /**
     * 条件查询
     * @param iTask
     * @return
     */
    @PostMapping("/selectList")
    public Result< List<Task> > select(@RequestBody Task iTask){
        return Result.ok("条件查询成功",iTaskService.selectList(iTask));
    }

    /**
     * 新增
     * @param iTask
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Task iTask){
        iTaskService.insert(iTask);
        return Result.ok("新增成功");
    }


    /**
     * 删除
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        iTaskService.delete(ids);
        return Result.ok("删除成功");
    }


    /**
     * 修改
     * @param iTask
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Task iTask){
        iTaskService.update(iTask);
        return Result.ok("修改成功");
    }

    /**
     * 分页搜索实现
     * @param page 默认值1
     * @param size 默认值10
     * @return
     */
    @GetMapping(value = "/pageAll" )
    public Result pageAll(@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Task> pageList = iTaskService.pageAll(page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }

    /**
     * 分页条件搜索实现
     * @param iTask
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/pageList" )
    public Result pageList(@RequestBody Task iTask,@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Task> pageList = iTaskService.pageList(iTask,page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }
}
