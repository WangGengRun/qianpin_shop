package com.itbuka.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.Result;
import com.itbuka.goods.domain.Spec;
import com.itbuka.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 规格表
 */
@RestController
@RequestMapping("/Spec")
public class SpecController {
    @Autowired
    private SpecService iSpecService;


    /**
     * 查询全部规格表
     *
     * @param
     * @return
     */
    @GetMapping("/selectAll")
    public Result<List<Spec>> selectAll() {
        return Result.ok("查询成功", iSpecService.selectAll());
    }


    /**
     * 条件查询规格表
     *
     * @param iSpec
     * @return
     */
    @PostMapping("/selectList")
    public Result<List<Spec>> select(@RequestBody Spec iSpec) {
        return Result.ok("条件查询成功", iSpecService.selectList(iSpec));
    }

    /**
     * 新增规格表
     *
     * @param iSpec
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Spec iSpec) {
        iSpecService.insert(iSpec);
        return Result.ok("新增成功");
    }


    /**
     * 删除规格表
     *
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids) {
        iSpecService.delete(ids);
        return Result.ok("删除成功");
    }


    /**
     * 修改规格表
     *
     * @param iSpec
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Spec iSpec) {
        iSpecService.update(iSpec);
        return Result.ok("修改成功");
    }

    /**
     * 分页搜索实现规格表
     *
     * @param page 默认值1
     * @param size 默认值10
     * @return
     */
    @GetMapping(value = "/pageAll")
    public Result pageAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<Spec> pageList = iSpecService.pageAll(page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getRecords());
        return Result.ok("查询成功", pageResult);
    }

    /**
     * 分页条件搜索实现规格表
     *
     * @param iSpec
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/pageList")
    public Result pageList(@RequestBody Spec iSpec, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<Spec> pageList = iSpecService.pageList(iSpec, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getRecords());
        return Result.ok("查询成功", pageResult);
    }
}