package com.itbuka.vip.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.Result;
import com.itbuka.goods.domain.VipPointHistory;
import com.itbuka.vip.service.VipPointHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/VipPointHistory")
public class VipPointHistoryController {
    @Autowired
    private VipPointHistoryService vipPointHistoryService;
    /**
     * 查询全部
     */
    @GetMapping("/selectAll")
    public Result<List<VipPointHistory>> selectAll() {
        return Result.ok("查询成功", vipPointHistoryService.selectAll());
    }


    /**
     * 条件查询
     */
    @PostMapping("/selectList")
    public Result<List<VipPointHistory>> select(@RequestBody VipPointHistory vipPointsHistory) {
        return Result.ok("条件查询成功", vipPointHistoryService.selectList(vipPointsHistory));
    }

    /**
     * 新增
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody VipPointHistory vipPointsHistory) {
        vipPointHistoryService.insert(vipPointsHistory);
        return Result.ok("新增成功");
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids) {
        vipPointHistoryService.delete(ids);
        return Result.ok("删除成功");
    }


    /**
     * 修改
     */
    @PostMapping("/update")
    public Result update(@RequestBody VipPointHistory vipPointsHistory) {
        vipPointHistoryService.update(vipPointsHistory);
        return Result.ok("修改成功");
    }

    /**
     * 分页搜索实现
     */
//    @GetMapping(value = "/pageAll")
//    public Result pageAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
//        Page<VipPointHistory> pageList = vipPointHistoryService.pageAll(page, size);
//        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
//        return Result.ok("查询成功", pageResult);
//    }
//
//    /**
//     * 分页条件搜索实现
//     */
//    @GetMapping(value = "/pageList")
//    public Result pageList(@RequestBody VipPointsHistory vipPointsHistory, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
//        Page<VipPointsHistory> pageList = vipPointsHistoryService.pageList(vipPointsHistory, page, size);
//        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
//        return Result.ok("查询成功", pageResult);
//    }

}
