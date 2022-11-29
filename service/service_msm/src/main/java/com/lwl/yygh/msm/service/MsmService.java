package com.lwl.yygh.msm.service;

import com.lwl.yygh.vo.msm.MsmVo;

/**
 * @author user-lwl
 * @createDate 2022/11/28 13:02
 */
public interface MsmService {
    /**
     * 发送手机验证码
     * @param phone 手机号
     * @param code 验证码
     * @return 是否发送成功
     */
    boolean send(String phone, String code);

    /**
     * mq发送短信使用
     * @param msmVo msmVo
     * @return 是否成功
     */
    boolean send(MsmVo msmVo);
}
