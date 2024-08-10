package com.itbuka.goods.controller;

import com.itbuka.entity.Result;
import com.itbuka.goods.domain.Detail;

import com.itbuka.goods.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/detail")
public class DetailController {
  @Autowired
  private DetailService detailService;
    /**
     * 查询商品详情页
     * * @param id
     * * @return
     */
    @GetMapping("/findDetail")
    public Result findDetail(@RequestParam Long id) {
      Detail detail=detailService.findDetail(id);
        return Result.ok("查询成功", detail);
    }
}
