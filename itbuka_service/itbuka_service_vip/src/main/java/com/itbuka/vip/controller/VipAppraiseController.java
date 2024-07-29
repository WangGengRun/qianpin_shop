package com.itbuka.vip.controller;

import com.itbuka.entity.Result;
import com.itbuka.goods.domain.VipAppraise;
import com.itbuka.vip.service.VipAppraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/VipAppraise")
public class VipAppraiseController {
    @Autowired
    private VipAppraiseService vipAppraiseService;
    @GetMapping("/selAll")
    public Result<List<VipAppraise>> selectAll() {
        return Result.ok("查询成功", vipAppraiseService.selectAll());
    }
    @PostMapping("/insert")
    public Result insert(@RequestBody VipAppraise vipAppraise) {
        vipAppraiseService.insert(vipAppraise);
        return Result.ok("新增成功");
    }

    @GetMapping("/delete")
    public Result delete(@RequestParam String ids) {
        vipAppraiseService.delete(ids);
        return Result.ok("删除成功");
    }
    @PostMapping("/update")
    public Result update(@RequestBody VipAppraise vipAppraise) {
        vipAppraiseService.update(vipAppraise);
        return Result.ok("修改成功");
    }


}
