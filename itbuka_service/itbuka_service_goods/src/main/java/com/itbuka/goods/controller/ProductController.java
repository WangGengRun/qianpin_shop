package com.itbuka.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.Result;
import com.itbuka.goods.domain.Product;
import com.itbuka.goods.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Product")
public class ProductController {
    @Autowired
    private ProductService iProductService;
    /**
     * 查询全部商品表
     * @param
     * @return
     */
    @GetMapping("/selectAll")
    public Result<List<Product>> selectAll(){
        return Result.ok("查询成功",iProductService.selectAll());
    }


    /**
     * 条件查询商品表
     * @param iProduct
     * @return
     */
    @PostMapping("/selectList")
    public Result< List<Product> > select(@RequestBody Product iProduct){
        return Result.ok("条件查询成功",iProductService.selectList(iProduct));
    }

    /**
     * 新增商品表
     * @param iProduct
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Product iProduct){
        iProductService.insert(iProduct);
        return Result.ok("新增成功");
    }


    /**
     * 删除商品表
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        iProductService.delete(ids);
        return Result.ok("删除成功");
    }


    /**
     * 修改商品表
     * @param iProduct
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Product iProduct){
        iProductService.update(iProduct);
        return Result.ok("修改成功");
    }

    /**
     * 分页搜索实现商品表
     * @param page 默认值1
     * @param size 默认值10
     * @return
     */
    @GetMapping(value = "/pageAll" )
    public Result pageAll(@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Product> pageList = iProductService.pageAll(page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }

    /**
     * 分页条件搜索实现商品表
     * @param iProduct
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/pageList" )
    public Result pageList(@RequestBody Product iProduct,@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Product> pageList = iProductService.pageList(iProduct,page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }

    /**
     * 审核
     * @param id
     * @return
     */
    @PostMapping("/audit")
    public Result audit(@RequestParam Long id,@RequestParam Integer audit){
        Integer i = iProductService.audit(id,audit);
        if (i == -1) {
            return Result.fail("审核失败");
        }
        if (i == 2){
            return Result.fail("审核拒绝");
        }
        return Result.ok("审核成功");
    }

    /**
     * 上、下架
     * @param id
     * @return
     */
    @PostMapping("/status")
    public Result status(@RequestParam Long id,@RequestParam Integer status){
        Integer i = iProductService.status(id,status);
        if (i != 1) {
            return Result.fail("上下架失败");
        }
        if (status == 0){
            return Result.ok("下架成功");
        }
        return Result.ok("上架成功");
    }

}
