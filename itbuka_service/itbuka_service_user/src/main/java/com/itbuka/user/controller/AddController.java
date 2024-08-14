package com.itbuka.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.Result;
import com.itbuka.entity.StatusCode;
import com.itbuka.goods.domain.Address;
import com.itbuka.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户地址
 */
@RestController
@RequestMapping("/Address")
public class AddController {
    @Autowired
    private AddressService iAddressService;


    /**
     * 查询全部
     * @param
     * @return
     */
    @GetMapping("/selectAll")
    public Result<List<Address>> selectAll(){
        return new Result<>("查询成功",iAddressService.selectAll());
    }


    /**
     * 条件查询
     * @param iAddress
     * @return
     */
    @PostMapping("/selectList")
    public Result< List<Address> > select(@RequestBody Address iAddress){
        return new Result<>("条件查询成功",iAddressService.selectList(iAddress));
    }

    /**
     * 新增
     * @param iAddress
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Address iAddress){
        iAddressService.insert(iAddress);
        return new Result<>("新增成功");
    }


    /**
     * 删除
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        iAddressService.delete(ids);
        return new Result<>("删除成功");
    }


    /**
     * 修改
     * @param iAddress
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Address iAddress){
        iAddressService.update(iAddress);
        return new Result<>("修改成功");
    }

    /**
     * 分页搜索实现
     * @param page 默认值1
     * @param size 默认值10
     * @return
     */
    @GetMapping(value = "/pageAll" )
    public Result pageAll(@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Address> pageList = iAddressService.pageAll(page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }

    /**
     * 分页条件搜索实现
     * @param iAddress
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/pageList" )
    public Result pageList(@RequestBody Address iAddress,@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Address> pageList = iAddressService.pageList(iAddress,page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
}
