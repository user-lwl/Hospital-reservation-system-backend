package com.lwl.yygh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.yygh.common.exception.YyghException;
import com.lwl.yygh.common.helper.JwtHelper;
import com.lwl.yygh.common.result.ResultCodeEnum;
import com.lwl.yygh.enums.AuthStatusEnum;
import com.lwl.yygh.model.user.UserInfo;
import com.lwl.yygh.user.mapper.UserInfoMapper;
import com.lwl.yygh.user.service.UserInfoService;
import com.lwl.yygh.vo.user.LoginVo;
import com.lwl.yygh.vo.user.UserAuthVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
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
        }
        if (StringUtils.isEmpty(name)) {
            name = userInfo.getPhone();
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

    private UserInfo selectWxInfoOpenId(String openid) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);
        return userInfoMapper.selectOne(queryWrapper);
    }
}




