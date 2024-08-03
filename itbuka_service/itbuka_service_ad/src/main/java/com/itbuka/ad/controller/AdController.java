package com.itbuka.ad.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.ad.domain.Ad;
import com.itbuka.ad.service.AdService;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ad")
public class AdController {
    @Autowired
    private AdService iAdService;
    /**
     * 查询全部广告表
     * @param
     * @return
     */
    @GetMapping("/selectAll")
    public Result<List<Ad>> selectAll(){
        return Result.ok("查询成功",iAdService.selectAll());
    }


    /**
     * 条件查询广告表
     * @param iAd
     * @return
     */
    @PostMapping("/selectList")
    public Result< List<Ad> > select(@RequestBody Ad iAd){
        return Result.ok("条件查询成功",iAdService.selectList(iAd));
    }

    /**
     * 新增广告表
     * @param iAd
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Ad iAd){
        iAdService.insert(iAd);
        return Result.ok("新增成功");
    }


    /**
     * 删除广告表
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        iAdService.delete(ids);
        return Result.ok("删除成功");
    }


    /**
     * 修改广告表
     * @param iAd
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Ad iAd){
        iAdService.update(iAd);
        return Result.ok("修改成功");
    }

    /**
     * 分页搜索实现广告表
     * @param page 默认值1
     * @param size 默认值10
     * @return
     */
    @GetMapping(value = "/pageAll" )
    public Result pageAll(@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Ad> pageList = iAdService.pageAll(page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }

    /**
     * 分页条件搜索实现广告表
     * @param iAd
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/pageList" )
    public Result pageList(@RequestBody Ad iAd,@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Ad> pageList = iAdService.pageList(iAd,page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }
}
