package com.itbuka.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.entity.Result;
import com.itbuka.goods.domain.Brand;
import com.itbuka.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.itbuka.entity.PageResult;
import com.itbuka.entity.StatusCode;


import java.util.List;

/**
 * 品牌表
 */
@RestController
@RequestMapping("/Brand")
public class BrandController {
    @Autowired
    private BrandService iBrandService;


    /**
     * 查询全部品牌表
     * @param
     * @return
     */
    @GetMapping("/selectAll")
    public Result< List<Brand> > selectAll(){
        return Result.ok("查询成功",iBrandService.selectAll());
    }


    /**
     * 条件查询品牌表
     * @param iBrand
     * @return
     */

    @PostMapping("/selectList")
    public Result< List<Brand> > select(@RequestBody Brand iBrand){
        return Result.ok("条件查询成功",iBrandService.selectList(iBrand));
    }

    /**
     * 新增品牌表
     * @param iBrand
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Brand iBrand){
        iBrandService.insert(iBrand);
        return Result.ok("新增成功");
    }


    /**
     * 删除品牌表
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        iBrandService.delete(ids);
        return Result.ok("删除成功");
    }


    /**
     * 修改品牌表
     * @param iBrand
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Brand iBrand){
        iBrandService.update(iBrand);
        return Result.ok("修改成功");
    }

    /**
     * 分页搜索实现品牌表
     * @param page 默认值1
     * @param size 默认值10
     * @return
     */
//    @GetMapping(value = "/pageAll" )
//    public Result pageAll(@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
//        Page<Brand> pageList = iBrandService.pageAll(page, size);
//        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getResult());
//        return Result.ok("查询成功",pageResult);
//    }

    /**
     * 分页条件搜索实现品牌表
     * @param iBrand
     * @param page
     * @param size
     * @return
     */
//    @GetMapping(value = "/pageList" )
//    public Result pageList(@RequestBody Brand iBrand, @RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
//        Page<Brand> pageList = iBrandService.pageList(iBrand,page, size);
//        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getResult());
//        return Result.ok("查询成功",pageResult);
//    }
    /**
     * 品牌启用、停用 (status非null ，前端判断，null 后端判断)
     * @return
     */
    @GetMapping("/status")
    public Result status(@RequestParam Long id, @RequestParam Integer status) {
        if (id == null) {
            return Result.fail(StatusCode.ERROR, "查询不到品牌");
        }
        Integer integer = null;
        if (status == null) {
            integer = iBrandService.status(id);
        } else {
            integer = iBrandService.status(id, status);
        }
        if (integer != 1) {
            return Result.fail(StatusCode.ERROR, "品牌状态修改失败");
        }
        return Result.ok("品牌状态修改成功");
    }

}
