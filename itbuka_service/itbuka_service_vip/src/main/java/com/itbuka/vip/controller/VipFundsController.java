package com.itbuka.vip.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.Result;
import com.itbuka.goods.domain.VipFunds;
import com.itbuka.vip.service.VipFundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/VipFunds")
public class VipFundsController {
    @Autowired
    private VipFundsService vipFundsService;
    /**
     * 查询全部
     */
//    @GetMapping("/selectAll")
//    public Result<List<VipFunds>> selectAll(){
//        return Result.ok("查询成功",vipFundsService.selectAll());
//    }

    /**
     * 条件查询
     */
    @PostMapping("/selectList")
    public Result< List<VipFunds> > select(@RequestBody VipFunds vipFunds){
        return Result.ok("条件查询成功",vipFundsService.selectList(vipFunds));
    }

    /**
     * 新增
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody VipFunds vipFunds){
        vipFundsService.insert(vipFunds);
        return Result.ok("新增成功");
    }


    /**
     * 删除
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        vipFundsService.delete(ids);
        return Result.ok("删除成功");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Result update(@RequestBody VipFunds vipFunds){
        vipFundsService.update(vipFunds);
        return Result.ok("修改成功");
    }

    /**
     * 分页搜索实现
     */
    @GetMapping(value = "/pageAll" )
    public Result pageAll(@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<VipFunds> pageList = vipFundsService.pageAll(page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }

    /**
     * 分页条件搜索实现
     */
    @GetMapping(value = "/pageList" )
    public Result pageList(@RequestBody VipFunds vipFunds,@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<VipFunds> pageList = vipFundsService.pageList(vipFunds,page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }
}

