package com.itbuka.order.notify;
import com.itbuka.order.domain.Order;
import com.itbuka.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/notify")
public class NotifyController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 支付宝回调
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/aliNotify")  // 注意这里必须是POST接口
    @Transactional
    public void payNotify(HttpServletRequest request) throws Exception {
        System.out.println("=========支付宝异步回调========");
        Order order = new Order();

        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            params.put(name, request.getParameter(name));
        }
        // 获取订单号
        String s = params.get("out_trade_no");
        Object o = redisTemplate.opsForValue().get("orderNum_" + s);
        if (o != null) {
            String[] split = o.toString().split(",");
            for (String string : split) {
                // 支付成功，修改订单
                if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
                    order.setId(Long.valueOf(string));
                    order.setStatus(2);
                    order.setTradeNo(params.get("trade_no"));
                }else {
                    // 支付失败，关闭订单
                    order.setStatus(7);
                }
                orderService.update(order);
            }
        }
    }
}

