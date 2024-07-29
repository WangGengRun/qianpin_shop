package com.itbuka.vip.controller;

import com.itbuka.entity.Result;
import com.itbuka.goods.domain.Brand;
import com.itbuka.goods.domain.Vip;
import com.itbuka.vip.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vip")
public class VipController {
    @Autowired
    private VipService vipService;
    @GetMapping("/selAll")
    public Result<List<Vip>> selectAll(){
        return Result.ok("查询成功",vipService.selAll());
    }
    @PostMapping("/selectList")
    public Result<List<Vip>> select(@RequestBody Vip vip){
        return Result.ok("条件查询成功",vipService.selectList(vip));
    }
    @PostMapping("/insert")
    public Result insert(@RequestBody Vip vip) {
        vipService.insert(vip);
        return Result.ok("新增成功");
    }
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids) {
        vipService.delete(ids);
        return Result.ok("删除成功");
    }
    @PostMapping("/update")
    public Result update(@RequestBody Vip vip) {
        vipService.update(vip);
        return Result.ok("修改成功");
    }
    /**
     * 会员启用、停用
     */
    @GetMapping("/status")
    public Result status(@RequestParam Long id, @RequestParam Integer status) {
        Integer integer = vipService.status(id, status);
        if (integer == -1) {
            return Result.fail("该会员id没有数据");
        }
        if (integer == -2) {
            return Result.fail("会员状态输入错误");
        }
        return Result.ok("会员状态修改成功");
    }
}
