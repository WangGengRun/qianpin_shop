package com.itbuka.vip.controller;

import com.itbuka.entity.Result;
import com.itbuka.goods.domain.Brand;
import com.itbuka.goods.domain.Vip;
import com.itbuka.vip.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vip")
public class VipController {
    @Autowired
    private VipService vipService;
    @PostMapping("/selectList")
    public Result<List<Vip>> select(@RequestBody Vip vip){
        return Result.ok("条件查询成功",vipService.selectList(vip));
    }
}
