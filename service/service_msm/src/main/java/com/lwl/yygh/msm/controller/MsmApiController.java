package com.lwl.yygh.msm.controller;

import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.msm.service.MsmService;
import com.lwl.yygh.msm.utils.RandomUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author user-lwl
 * @createDate 2022/11/28 13:01
 */
@RestController
@RequestMapping("/api/msm")
public class MsmApiController {
    @Resource
    private MsmService msmService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/send/{phone}")
    public Result<Boolean> sendCode(@PathVariable String phone) {
        //取到ok
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //取不到
        //生成，发送
        code = RandomUtil.getSixBitRandom();
        System.out.println("验证码:" + code);
//        boolean isSend = msmService.send(phone, code);
        boolean isSend = true;
        if (isSend) {
            redisTemplate.opsForValue().set(phone, code, 1, TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.fail(false).message("发送短信失败");
        }
    }
}
