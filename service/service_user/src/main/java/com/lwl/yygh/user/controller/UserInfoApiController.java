package com.lwl.yygh.user.controller;

import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.common.utils.AuthContextHolder;
import com.lwl.yygh.model.user.UserInfo;
import com.lwl.yygh.user.service.UserInfoService;
import com.lwl.yygh.vo.user.LoginVo;
import com.lwl.yygh.vo.user.UserAuthVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/28 9:30
 */
@RestController
@RequestMapping("/api/user")
public class UserInfoApiController {
    @Resource
    private UserInfoService userInfoService;

    /**
     * 登陆
     * @param loginVo 登陆对象
     * @return 登录信息
     */
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody LoginVo loginVo) {
        Map<String, Object> info = userInfoService.loginUser(loginVo);
        return Result.ok(info);
    }

    /**
     * 用户认证接口
     * @param userAuthVo 用户信息
     * @param request 请求
     * @return 是否成功
     */
    @PostMapping("/auth/userAuth")
    public Result<Boolean> userAuth(@RequestBody UserAuthVo userAuthVo, HttpServletRequest request) {
        userInfoService.userAuth(AuthContextHolder.getUserId(request), userAuthVo);
        return Result.ok();
    }

    /**
     * 获取用户id信息接口
     * @param request 请求
     * @return 用户信息
     */
    @GetMapping("/auth/getUserInfo")
    public Result<UserInfo> getUserInfo(HttpServletRequest request) {
        Long userId = AuthContextHolder.getUserId(request);
        UserInfo userInfo = userInfoService.getById(userId);
        return Result.ok(userInfo);
    }
}
