package com.lwl.yygh.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.yygh.model.user.UserInfo;
import com.lwl.yygh.user.mapper.UserInfoMapper;
import com.lwl.yygh.user.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【user_info(用户表)】的数据库操作Service实现
* @createDate 2022-11-23 19:16:38
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService {

}




