package com.itbuka.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.Result;
import com.itbuka.goods.domain.ProductDetails;
import com.itbuka.goods.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ProductDetails")
public class ProductDetailsController {
    @Autowired
    private ProductDetailsService iProductDetailsService;


    /**
     * 查询全部商品详情表
     * @param
     * @return
     */
    @GetMapping("/selectAll")
    public Result<List<ProductDetails>> selectAll(){
        return Result.ok("查询成功",iProductDetailsService.selectAll());
    }


    /**
     * 条件查询商品详情表
     * @param iProductDetails
     * @return
     */
    @PostMapping("/selectList")
    public Result< List<ProductDetails> > select(@RequestBody ProductDetails iProductDetails){
        return Result.ok("条件查询成功",iProductDetailsService.selectList(iProductDetails));
    }

    /**
     * 新增商品详情表
     * @param iProductDetails
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody ProductDetails iProductDetails){
        iProductDetailsService.insert(iProductDetails);
        return Result.ok("新增成功");
    }


    /**
     * 删除商品详情表
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        iProductDetailsService.delete(ids);
        return Result.ok("删除成功");
    }


    /**
     * 修改商品详情表
     * @param iProductDetails
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody ProductDetails iProductDetails){
        iProductDetailsService.update(iProductDetails);
        return Result.ok("修改成功");
    }

    /**
     * 分页搜索实现商品详情表
     * @param page 默认值1
     * @param size 默认值10
     * @return
     */
    @GetMapping(value = "/pageAll" )
    public Result pageAll(@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<ProductDetails> pageList = iProductDetailsService.pageAll(page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }

    /**
     * 分页条件搜索实现商品详情表
     * @param iProductDetails
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/pageList" )
    public Result pageList(@RequestBody ProductDetails iProductDetails,@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<ProductDetails> pageList = iProductDetailsService.pageList(iProductDetails,page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }


    /**
     * 减库存
     * @param productId ，num
     * @return
     */
    @GetMapping("/reduceStock")
    public Result reduceStock(@RequestParam Long productId,@RequestParam Integer num){
        Integer i = iProductDetailsService.reduceStock(productId, num);
        if(i!=1){
            return Result.fail("库存不足");
        }
        return Result.ok("减库存成功");
    }

}
