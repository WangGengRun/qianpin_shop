package com.itbuka.search.controller;

import com.itbuka.entity.Result;
import com.itbuka.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;
    @RequestMapping("/search")
    public Result search(@RequestBody Map map) throws Exception {
        Map search = searchService.search(map);
        return Result.ok("查询成功",search);
    }
}
