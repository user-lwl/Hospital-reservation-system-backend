package com.lwl.yygh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.yygh.model.user.UserInfo;
import com.lwl.yygh.vo.user.LoginVo;
import com.lwl.yygh.vo.user.UserAuthVo;

import java.util.Map;

/**
* @author user-lwl
* @description 针对表【user_info(用户表)】的数据库操作Service
* @createDate 2022-11-23 19:16:38
*/
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 登陆
     * @param loginVo 登陆对象
     * @return 登陆信息
     */
    Map<String, Object> loginUser(LoginVo loginVo);

    /**
     * 用户认证接口
     * @param userId 用户id
     * @param userAuthVo 用户Vo
     */
    void userAuth(Long userId, UserAuthVo userAuthVo);
}
