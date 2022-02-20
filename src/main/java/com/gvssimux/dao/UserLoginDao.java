package com.gvssimux.dao;

import com.gvssimux.pojo.UserBasic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserLoginDao {


    //查询全部user，不带密码
    @Select("select userid,uname,`user`,sign,time from bms.user")
    List<UserBasic> selectAll();

    //根据Id查询
    @Select("select * from bms.user where userid = #{userid}")
    UserBasic selectByUserId(@Param("userid") String userid);

    //查询用户数量
    @Select("SELECT COUNT(*) FROM bms.user")
    int getUserNum();

    //登陆查询，使用用户名和密码登陆
    @Select("select * from user_module.user_basic where user_loginid = #{user_loginid} && user_pwd = #{user_pwd} && user_sign = 'admin'")
    UserBasic getUserIdByUserNamePwd(@Param("user_loginid")String user_loginid, @Param("user_pwd")String user_pwd);


    //2021-写
    //注册---验证用户名是否在数据中
    @Select("SELECT user from user_module.user_basic where user=#{user}")
    String getUserByUser(@Param("user")String user);

}