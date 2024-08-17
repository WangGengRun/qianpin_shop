package com.itbuka.alipay.feign;
import com.itbuka.alipay.domain.AliPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
@FeignClient(name = "alipay")
public interface PayFeign {

    /**
     * 查看支付订单信息
     */
    @RequestMapping("/alipay/query")
    Map queryPayment(@RequestBody AliPay aliPay);
}
