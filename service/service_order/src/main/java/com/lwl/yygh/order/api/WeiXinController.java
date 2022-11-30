package com.lwl.yygh.order.api;

import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.order.service.PaymentInfoService;
import com.lwl.yygh.order.service.WeiXinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/30 13:35
 */
@RestController
@RequestMapping("/api/order/weixin")
public class WeiXinController {
    @Resource
    private WeiXinService weiXinService;

    @Resource
    private PaymentInfoService paymentInfoService;

    /**
     * 下单生成支付二维码
     * @param orderId 订单id
     * @return result
     */
    @GetMapping("/createNative/{orderId}")
    public Result<Map<String, Object>> createNative(@PathVariable("orderId") Long orderId) {
        return Result.ok(weiXinService.createNative(orderId));
    }

    /**
     * 查询支付状态
     * @param orderId 订单id
     * @return 支付状态
     */
    @GetMapping("/queryPayStatus/{orderId}")
    public Result<Object> queryPayStatus(@PathVariable("orderId") Long orderId) {
        //调用查询接口
        Map<String, String> resultMap = weiXinService.queryPayStatus(orderId);
        if (resultMap == null) {//出错
            return Result.fail().message("支付出错");
        }
        if ("SUCCESS".equals(resultMap.get("trade_state"))) {//如果成功
            //更改订单状态，处理支付结果
            String outTradeNo = resultMap.get("out_trade_no");
            paymentInfoService.paySuccess(outTradeNo, resultMap);
            return Result.ok().message("支付成功");
        }
        return Result.ok().message("支付中");
    }

}
