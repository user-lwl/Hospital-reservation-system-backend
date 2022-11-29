package com.lwl.yygh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.yygh.common.exception.YyghException;
import com.lwl.yygh.common.helper.JwtHelper;
import com.lwl.yygh.common.result.ResultCodeEnum;
import com.lwl.yygh.enums.AuthStatusEnum;
import com.lwl.yygh.model.user.Patient;
import com.lwl.yygh.model.user.UserInfo;
import com.lwl.yygh.user.mapper.UserInfoMapper;
import com.lwl.yygh.user.service.PatientService;
import com.lwl.yygh.user.service.UserInfoService;
import com.lwl.yygh.vo.user.LoginVo;
import com.lwl.yygh.vo.user.UserAuthVo;
import com.lwl.yygh.vo.user.UserInfoQueryVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author user-lwl
* @description 针对表【user_info(用户表)】的数据库操作Service实现
* @createDate 2022-11-23 19:16:38
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private PatientService patientService;

    /**
     * 登陆
     * @param loginVo 登陆对象
     * @return 登录信息
     */
    @Override
    public Map<String, Object> loginUser(LoginVo loginVo) {
        //获取手机号和验证码
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();
        //判空
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }
        //判断验证码
        String redisCode = redisTemplate.opsForValue().get(phone);
        if (!code.equals(redisCode)) {
            throw new YyghException(ResultCodeEnum.CODE_ERROR);
        }
        //绑定手机号码
        UserInfo userInfo = null;
        if(!StringUtils.isEmpty(loginVo.getOpenid())) {
            userInfo = this.selectWxInfoOpenId(loginVo.getOpenid());
            if(null != userInfo) {
                userInfo.setPhone(loginVo.getPhone());
                this.updateById(userInfo);
            } else {
                throw new YyghException(ResultCodeEnum.DATA_ERROR);
            }
        }
        if (userInfo == null) {
            //判断第一次登陆
            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone", phone);
            userInfo = userInfoMapper.selectOne(queryWrapper);
            if (userInfo == null) {
                userInfo = new UserInfo();
                userInfo.setName("");
                userInfo.setPhone(phone);
                userInfo.setStatus(1);
                userInfoMapper.insert(userInfo);
            }
        }
        //判断封号
        if (userInfo.getStatus() == 0) {
            throw new YyghException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }
        //登陆
        //返回登录信息
        Map<String, Object> map = new HashMap<>();
        String name = userInfo.getName();
        if (StringUtils.isEmpty(name)) {
            name = userInfo.getNickName();
            if (StringUtils.isEmpty(name)) {
                name = userInfo.getPhone();
            }
        }
        map.put("name", name);
        String token = JwtHelper.createToken(userInfo.getId(), name);
        map.put("token", token);
        return map;
    }

    /**
     * 用户认证
     * @param userId 用户id
     * @param userAuthVo 用户Vo
     */
    @Override
    public void userAuth(Long userId, UserAuthVo userAuthVo) {
        //查询信息
        UserInfo userInfo = userInfoMapper.selectById(userId);
        //设置认证信息
        userInfo.setName(userAuthVo.getName());
        userInfo.setCertificatesType(userAuthVo.getCertificatesType());
        userInfo.setCertificatesNo(userAuthVo.getCertificatesNo());
        userInfo.setCertificatesUrl(userAuthVo.getCertificatesUrl());
        userInfo.setAuthStatus(AuthStatusEnum.AUTH_RUN.getStatus());
        //信息更新
        userInfoMapper.updateById(userInfo);
    }

    /**
     * 条件查询带分页，用户列表
     * @param pageParam page模型
     * @param userInfoQueryVo userInfoQueryVo
     * @return 查询结果
     */
    @Override
    public IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo) {
        // 查询条件
        // 用户名称
        String name = userInfoQueryVo.getKeyword();
        // 用户状态
        Integer status = userInfoQueryVo.getStatus();
        // 认证状态
        Integer authStatus = userInfoQueryVo.getAuthStatus();
        // 开始时间
        String createTimeBegin = userInfoQueryVo.getCreateTimeBegin();
        // 结束时间
        String createTimeEnd = userInfoQueryVo.getCreateTimeEnd();
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq("status", status);
        }
        if (!StringUtils.isEmpty(authStatus)) {
            queryWrapper.eq("auth_status", authStatus);
        }
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.ge("create_time", createTimeBegin);
        }
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.le("create_time", createTimeEnd);
        }
        IPage<UserInfo> pages = userInfoMapper.selectPage(pageParam, queryWrapper);
        // 编号 -> 值
        pages.getRecords().forEach(this::packageUserInfo);
        return pages;
    }

    /**
     * 用户锁定
     * @param userId 用户id
     * @param status 状态
     */
    @Override
    public void lock(Long userId, Integer status) {
        if (status == 0 || status == 1) {
            UserInfo userInfo = userInfoMapper.selectById(userId);
            userInfo.setStatus(status);
            userInfoMapper.updateById(userInfo);
        }
    }

    /**
     * 根据id查询用户详情信息
     * @param userId 用户id
     * @return 用户详情信息
     */
    @Override
    public Map<String, Object> show(Long userId) {
        Map<String, Object> map = new HashMap<>();
        //用户信息
        UserInfo userInfo = this.packageUserInfo(userInfoMapper.selectById(userId));
        map.put("userInfo", userInfo);
        //就诊人信息
        List<Patient> patientList = patientService.findAllUserId(userId);
        map.put("patientList", patientList);
        return map;
    }

    /**
     * 认证审批
     * @param userId 用户id
     * @param authStatus 审批状态
     */
    @Override
    public void approval(Long userId, Integer authStatus) {
        // 2: 审核通过 -1 : 审核不通过
        if (authStatus == 2 || authStatus == -1) {
            UserInfo userInfo = userInfoMapper.selectById(userId);
            userInfo.setAuthStatus(authStatus);
            userInfoMapper.updateById(userInfo);
        }
    }

    /**
     * 编号 -> 值
     * @param userInfo 用户信息
     */
    private UserInfo packageUserInfo(UserInfo userInfo) {
        userInfo.getParam().put("authStatusString", AuthStatusEnum.getStatusNameByStatus(userInfo.getAuthStatus()));
        String statusString = userInfo.getStatus() == 0 ? "锁定" : "正常";
        userInfo.getParam().put("statusString", statusString);
        return userInfo;
    }

    private UserInfo selectWxInfoOpenId(String openid) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);
        return userInfoMapper.selectOne(queryWrapper);
    }
}




