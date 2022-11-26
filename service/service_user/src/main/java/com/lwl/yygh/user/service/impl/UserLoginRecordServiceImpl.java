package com.lwl.yygh.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.yygh.model.user.UserLoginRecord;
import com.lwl.yygh.user.mapper.UserLoginRecordMapper;
import com.lwl.yygh.user.service.UserLoginRecordService;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【user_login_record(用户登录记录表)】的数据库操作Service实现
* @createDate 2022-11-23 19:16:43
*/
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord>
    implements UserLoginRecordService {

}




