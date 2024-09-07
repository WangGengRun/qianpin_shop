package com.itbuka.seckill.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.Result;
import com.itbuka.entity.StatusCode;
import com.itbuka.seckill.domain.SeckillProduct;
import com.itbuka.seckill.service.SeckillProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 秒杀商品表
 */
@RestController
@RequestMapping("/SeckillProduct")
public class SeckillProductController {
    @Autowired
    private SeckillProductService iSeckillProductService;


    /**
     * 查询全部秒杀商品表
     * @param
     * @return
     */
    @GetMapping("/selectAll")
    public Result<List<SeckillProduct>> selectAll(){
        return new Result<>("查询成功",iSeckillProductService.selectAll());
    }


    /**
     * 条件查询秒杀商品表
     * @param iSeckillProduct
     * @return
     */
    @PostMapping("/selectList")
    public Result< List<SeckillProduct> > select(@RequestBody SeckillProduct iSeckillProduct){
        return new Result<>("条件查询成功",iSeckillProductService.selectList(iSeckillProduct));
    }

    /**
     * 新增秒杀商品表
     * @param iSeckillProduct
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody SeckillProduct iSeckillProduct){
        iSeckillProductService.insert(iSeckillProduct);
        return new Result<>("新增成功");
    }


    /**
     * 删除秒杀商品表
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        iSeckillProductService.delete(ids);
        return new Result<>("删除成功");
    }


    /**
     * 修改秒杀商品表
     * @param iSeckillProduct
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody SeckillProduct iSeckillProduct){
        iSeckillProductService.update(iSeckillProduct);
        return new Result<>("修改成功");
    }

    /**
     * 分页搜索实现秒杀商品表
     * @param page 默认值1
     * @param size 默认值10
     * @return
     */
    @GetMapping(value = "/pageAll" )
    public Result pageAll(@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<SeckillProduct> pageList = iSeckillProductService.pageAll(page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }

    /**
     * 分页条件搜索实现秒杀商品表
     * @param iSeckillProduct
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/pageList" )
    public Result pageList(@RequestBody SeckillProduct iSeckillProduct,@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<SeckillProduct> pageList = iSeckillProductService.pageList(iSeckillProduct,page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
}

