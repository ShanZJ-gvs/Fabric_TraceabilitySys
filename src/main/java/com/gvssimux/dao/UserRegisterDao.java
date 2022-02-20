package com.gvssimux.dao;

import com.gvssimux.pojo.UserBasic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface UserRegisterDao {
    /*添加一条信息到user_basic表中*/
    int addUserBasic(Map<String,Object> map);


    @Select("select * from user_module.user_basic where user_id = #{user_id}")
    UserBasic getUserBasicByUserUname(@Param("user_id")int user_id);

}
