package com.lwl.yygh.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.model.user.UserInfo;
import com.lwl.yygh.user.service.UserInfoService;
import com.lwl.yygh.vo.user.UserInfoQueryVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/29 10:00
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Resource
    UserInfoService userInfoService;

    /**
     * 用户列表，条件查询带分页
     * @param page 当前页
     * @param limit 每页数据条数
     * @param userInfoQueryVo userInfoQueryVo
     * @return 查询结果
     */
    @GetMapping("/{page}/{limit}")
    public Result<IPage<UserInfo>> list(@PathVariable Long page,
                       @PathVariable Long limit,
                       UserInfoQueryVo userInfoQueryVo) {
        Page<UserInfo> pageParam = new Page<>(page, limit);
        IPage<UserInfo> pageModel = userInfoService.selectPage(pageParam, userInfoQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 用户锁定
     * @param userId 用户id
     * @param status 状态
     * @return 是否成功
     */
    @GetMapping("/lock/{userId}/{status}")
    public Result<Boolean> lock(@PathVariable Long userId,
                       @PathVariable Integer status) {
        userInfoService.lock(userId, status);
        return Result.ok();
    }

    /**
     * 用户详情
     * @param userId 用户id
     * @return 用户详情信息
     */
    @GetMapping("/show/{userId}")
    public Result<Map<String, Object>> show(@PathVariable Long userId) {
        Map<String, Object> map = userInfoService.show(userId);
        return Result.ok(map);
    }

    /**
     * 认证审批
     * @param userId 用户id
     * @param authStatus 审批状态
     * @return 是否成功
     */
    @GetMapping("/approval/{userId}/{authStatus}")
    public Result<Boolean> approval(@PathVariable Long userId,
                           @PathVariable Integer authStatus) {
        userInfoService.approval(userId, authStatus);
        return Result.ok();
    }
}
