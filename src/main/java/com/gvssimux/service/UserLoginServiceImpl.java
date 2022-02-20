package com.gvssimux.service;

import com.gvssimux.dao.UserLoginDao;
import com.gvssimux.pojo.UserBasic;

import java.util.List;


public class UserLoginServiceImpl {
    private UserLoginDao mapper;


    public List<UserBasic> selectAll(){
        return mapper.selectAll();
    }


    public UserBasic selectByUserId(String userid) {
        return mapper.selectByUserId(userid);
    }


    public int getUserNum() {
        return mapper.getUserNum();
    }


    public UserBasic getUserIdByUserNamePwd(String user_loginid, String user_pwd) {
        return mapper.getUserIdByUserNamePwd(user_loginid, user_pwd);
    }

    //2021-写
    //注册---验证用户名是否在数据中
    public int getUserByUser(String user){
        if (mapper.getUserByUser(user)==null){
            return 0;
        }
        if(mapper.getUserByUser(user).equals(user))
            return 1;
        return 0;
    }


    public void setMapper(UserLoginDao mapper) {
        this.mapper = mapper;
    }
}
