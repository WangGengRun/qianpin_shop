package com.itbuka.seckill.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.Result;
import com.itbuka.entity.StatusCode;
import com.itbuka.seckill.domain.Seckill;
import com.itbuka.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Seckill")
public class SeckillController {
    @Autowired
    private SeckillService iSeckillService;


    /**
     * 查询全部秒杀表
     * @param
     * @return
     */
    @GetMapping("/selectAll")
    public Result<List<Seckill>> selectAll(){
        return new Result<>("查询成功",iSeckillService.selectAll());
    }


    /**
     * 条件查询秒杀表
     * @param iSeckill
     * @return
     */
    @PostMapping("/selectList")
    public Result< List<Seckill> > select(@RequestBody Seckill iSeckill){
        return new Result<>("条件查询成功",iSeckillService.selectList(iSeckill));
    }

    /**
     * 新增秒杀表
     * @param iSeckill
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Seckill iSeckill){
        iSeckillService.insert(iSeckill);
        return new Result<>("新增成功");
    }


    /**
     * 删除秒杀表
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        iSeckillService.delete(ids);
        return new Result<>("删除成功");
    }


    /**
     * 修改秒杀表
     * @param iSeckill
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Seckill iSeckill){
        iSeckillService.update(iSeckill);
        return new Result<>("修改成功");
    }

    /**
     * 分页搜索实现秒杀表
     * @param page 默认值1
     * @param size 默认值10
     * @return
     */
    @GetMapping(value = "/pageAll" )
    public Result pageAll(@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Seckill> pageList = iSeckillService.pageAll(page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }

    /**
     * 分页条件搜索实现秒杀表
     * @param iSeckill
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/pageList" )
    public Result pageList(@RequestBody Seckill iSeckill,@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Seckill> pageList = iSeckillService.pageList(iSeckill,page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

}
