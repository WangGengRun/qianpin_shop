package com.itbuka.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.Result;
import com.itbuka.goods.domain.Classify;
import com.itbuka.goods.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 分类表
 */
@RestController
@RequestMapping("/Classify")
public class ClassifyController {
    @Autowired
    private ClassifyService iClassifyService;


    /**
     * 查询全部分类表
     * @param
     * @return
     */
    @GetMapping("/selectAll")
    public Result< List<Classify> > selectAll(){
        return Result.ok("查询成功",iClassifyService.selectAll());
    }


    /**
     * 条件查询分类表
     * @param iClassify
     * @return
     */
    @PostMapping("/selectList")
    public Result< List<Classify> > select(@RequestBody Classify iClassify){
        return Result.ok("条件查询成功",iClassifyService.selectList(iClassify));
    }

    /**
     * 新增分类表
     * @param iClassify
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Classify iClassify){
        iClassifyService.insert(iClassify);
        return Result.ok("新增成功");
    }


    /**
     * 删除分类表
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        iClassifyService.delete(ids);
        return Result.ok("删除成功");
    }


    /**
     * 修改分类表
     * @param iClassify
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Classify iClassify){
        iClassifyService.update(iClassify);
        return Result.ok("修改成功");
    }

    /**
     * 分页搜索实现分类表
     * @param page 默认值1
     * @param size 默认值10
     * @return
     */
    @GetMapping(value = "/pageAll" )
    public Result pageAll(@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Classify> pageList = iClassifyService.pageAll(page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }

    /**
     * 分页条件搜索实现分类表
     * @param iClassify
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/pageList" )
    public Result pageList(@RequestBody Classify iClassify,@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Classify> pageList = iClassifyService.pageList(iClassify,page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }

    /**
     * 修改状态（启用、停用）
     * @param id
     * @param status
     * @return
     */
    @GetMapping("/status")
    public Result status(@RequestParam Long id,@RequestParam Integer status){
        Integer i = iClassifyService.updateStatus(id, status);
        if (i != 1) {
            return Result.fail("修改状态失败");
        }
        return Result.ok("修改状态成功");
    }

    /**
     * 根据分类名称查询品牌列表
     * @param category
     * @return
     */
    @GetMapping("/category")
    public Result  findListByCategoryName(@RequestParam String category){
        List<Map> brandList = iClassifyService.findListByCategoryName(category);
        return Result.ok("查询成功",brandList);
    }


}
