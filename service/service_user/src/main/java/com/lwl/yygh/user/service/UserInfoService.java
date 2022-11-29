package com.lwl.yygh.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.yygh.model.user.UserInfo;
import com.lwl.yygh.vo.user.LoginVo;
import com.lwl.yygh.vo.user.UserAuthVo;
import com.lwl.yygh.vo.user.UserInfoQueryVo;

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

    /**
     * 用户列表，条件查询带分页
     * @param pageParam page模型
     * @param userInfoQueryVo userInfoQueryVo
     * @return 查询结果
     */
    IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);

    /**
     * 用户锁定
     * @param userId 用户id
     * @param status 状态
     */
    void lock(Long userId, Integer status);

    /**
     * 根据id查询用户详情
     * @param userId 用户id
     * @return 用户详情
     */
    Map<String, Object> show(Long userId);

    /**
     * 认证审批
     * @param userId 用户id
     * @param authStatus 审批状态
     */
    void approval(Long userId, Integer authStatus);
}
