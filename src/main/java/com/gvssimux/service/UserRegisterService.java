package com.gvssimux.service;

import com.gvssimux.pojo.UserBasic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface UserRegisterService {
    /*添加一条信息到user_basic表中*/
    int addUserBasic(Map<String,Object> map);


    UserBasic getUserBasicByUserUname(int user_id);

}
