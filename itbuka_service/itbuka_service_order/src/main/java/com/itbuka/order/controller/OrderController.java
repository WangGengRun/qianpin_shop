package com.itbuka.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.itbuka.order.domain.Order;
import com.itbuka.entity.Result;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.StatusCode;


import java.util.List;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    private OrderService iOrderService;


    /**
     * 查询全部
     * @param
     * @return
     */
    @GetMapping("/selectAll")
    public Result< List<Order> > selectAll(){
        return new Result<>("查询成功",iOrderService.selectAll());
    }


    /**
     * 条件查询
     * @param iOrder
     * @return
     */
    @PostMapping("/selectList")
    public Result< List<Order> > select(@RequestBody Order iOrder){
        return new Result<>("条件查询成功",iOrderService.selectList(iOrder));
    }

    /**
     * 新增
     * @param iOrder
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Order iOrder){
        Integer i = iOrderService.insert(iOrder);
        if (i == -1){
            return Result.fail("查询不到商品");
        }
        if (i == -2){
            return Result.fail("库存不足");
        }
        if (i == -3){
            return Result.fail("订单失败");
        }
        return new Result<>("新增成功");
    }


    /**
     * 删除
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        iOrderService.delete(ids);
        return new Result<>("删除成功");
    }


    /**
     * 修改
     * @param iOrder
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Order iOrder){
        iOrderService.update(iOrder);
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
        Page<Order> pageList = iOrderService.pageAll(page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /**
     * 分页条件搜索实现
     * @param iOrder
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/pageList" )
    public Result pageList(@RequestBody Order iOrder,@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Order> pageList = iOrderService.pageList(iOrder,page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
    /**
     * 购物车下单
     * @param
     * @return
     */
    @PostMapping("/addCart")
    public Result addCart(@RequestBody Map map){
        iOrderService.addCart(map);
        return Result.ok("购物车下单成功");
    }

    /**
     * 清空购物车
     * @param
     * @return
     */
    @PostMapping("/clearCart")
    public Result clearCart(@RequestBody Map map){
        iOrderService.clearCart(map);
        return Result.ok("清空购物车成功");
    }
    /**
     * 批量发货
     * @param orders  订单列表
     */
    @PostMapping("/batchSend")
    public Result batchSend( @RequestBody List<Order> orders){
        iOrderService.batchSend( orders );
        return Result.ok("发货成功");
    }
    /**
     * 确认收货
     * @param orderId  订单号
     * @return
     */
    @GetMapping("/take")
    public Result take(@RequestParam String orderId){
        iOrderService.confirmTask( orderId);
        return new Result( true,StatusCode.OK,"" );
    }


}
