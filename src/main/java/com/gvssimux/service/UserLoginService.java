package com.gvssimux.service;

import com.gvssimux.pojo.UserBasic;

import java.util.List;

public interface UserLoginService {

    //查询全部user，不带密码
    List<UserBasic> selectAll();

    //根据Id查询
    UserBasic selectByUserId(String userid);

    //查询用户数量
    int getUserNum();

    //登陆查询，使用用户名和密码登陆
    UserBasic getUserIdByUserNamePwd(String user_loginid, String user_pwd);

    //2021-写
    //注册---验证用户名是否在数据中
    int getUserByUser(String user);

}
