package com.gvssimux.controller;

import com.gvssimux.pojo.UserBasic;
import com.gvssimux.service.UserLoginServiceImpl;
import com.gvssimux.util.GetUUID;
import com.gvssimux.util.UserVerify;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

public class RegisterController {
    /**2021
     * 注册----验证用户名
     * 返回值:
     *      error0:输入不规范
     *      error1:输入用户名已经存在
     *      success0:表示此用户名可以注册
     */
    @ResponseBody
    @RequestMapping("/registerUser")
    public  String  registerUser (String user) {
        if (UserVerify.judgeContainsStr(user) && UserVerify.isStrLength(user,4,8)){
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            UserLoginServiceImpl mapper = context.getBean("UserServiceImpl", UserLoginServiceImpl.class);
            //a为1则重复 若a为0不重复
            //执行查询
            int a = mapper.getUserByUser(user);
            System.out.println("执行成功registerUser=====》:"+user);
            if(a==0){
                System.out.println("success0");
                return "success0";
            }
            System.out.println("error1");
            return "error1";
        }
        System.out.println("error0");
        return "error0";

    }



    /**
     * 注册----验证昵称
     * 返回值:
     *      unameerror:昵称不规范
     *      unamesuccess:昵称符合规范
     */
    @ResponseBody
    @RequestMapping("/registerUname")
    public  String  registerUname (String uname) {
        if(UserVerify.isStrLength(uname,2,12)){
            System.out.println("unamesuccess");
            return "unamesuccess";
        }
        System.out.println("unameerror");
        return "unameerror";
    }


    /**
     * 注册----验证密码打于6  (设置了小于50，是防止字符过长，超出字符串长度)
     * 返回值:
     *      pwderror0:密码长度小于6或大于50
     *      pwderror1:密码不包含字母和数字
     *      pwdsuccess:OK
     */
    @ResponseBody
    @RequestMapping("/registerPWD")
    public  String  registerPWD (String pwd) {
        if(UserVerify.isStrLength(pwd,6,50)){
            if(UserVerify.HasDigit(pwd)&&UserVerify.judgeContainsStr2(pwd)){
                System.out.println("pwdsuccess");
                return "pwdsuccess";
            }
            System.out.println("pwderror1");
            return "pwderror1";
        }
        System.out.println("pwderror0");
        return "pwderror0";
    }


    /**
     * 验证 重输密码 是否一致
     * 返回值:
     *      pwd2error:不一致
     *      pwd2success:OK
     */
    @ResponseBody
    @RequestMapping("/registerPWD2")
    public  String  registerPWD2 (String pwd,String pwd2) {
        if (pwd.length()==0){
            System.out.println("pwderror3");
            return "pwderror3";
        }
        if (pwd2.equals(pwd)){
            System.out.println("pwd2success");
            return "pwd2success";
        }
        System.out.println("pwd2error");
        return "pwd2error";
    }


    /**
     * 点击注册按钮
     */
    @ResponseBody
    @RequestMapping("/setuser")
    public String setUser(HttpSession session, String user_uname, String user_loginid, String user_pwd, String user_pwd2){
        if (registerUser(user_loginid).equals("success0")&&
                registerUname(user_uname).equals("unamesuccess")&&
                registerPWD(user_pwd).equals("pwdsuccess")&&
                registerPWD2(user_pwd,user_pwd2).equals("pwd2success")){
            System.out.println("setUserSuccess");

            //插入用户信息
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            UserLoginServiceImpl mapper = context.getBean("UserServiceImpl", UserLoginServiceImpl.class);
            UserBasic user1 = new UserBasic(GetUUID.get(),user_uname,user_loginid,user_pwd2,"admin",5);
//            System.out.println(mapper.insertSelective(user1));
            //创建session
            if (user_loginid!=null) {
                //把用户的信息存在session中,用于验证
                session.setAttribute("uuid",user_loginid);
                return "setUserSuccess";
            }

        }
        System.out.println("setUserError");
        return "setUserError";
    }


    @org.junit.Test
    public void  test(){
        System.out.println("①语句覆盖");
        registerUname("哈哈哈");
        registerUname("单");
        System.out.println("②判定覆盖");
        registerUname("小明同学");
        registerUname("笑");
        System.out.println("③条件覆盖");
        registerUname("小李同学");
        registerUname("天");
        System.out.println("④判定-条件覆盖");
        registerUname("小李同学");
        registerUname("吴");
        System.out.println("⑤组合覆盖");
        registerUname("小黄同学");
        registerUname("紫");
        System.out.println("⑥路径覆盖");
        registerUname("高同学");
        registerUname("杨");

    }


    @org.junit.Test
    public void  test1(){
        System.out.println("①语句覆盖");
        registerUser("shanzj");
        registerUser("123ws");
        registerUser("_12");
        registerUser("admin");
        System.out.println("②判定覆盖");
        registerUser("shanzj1");
        registerUser("12");
        registerUser("_ws");
        registerUser("admin");
        System.out.println("③条件覆盖④判定-条件覆盖⑤组合覆盖⑥路径覆盖");
        registerUser("shanzj");
        registerUser("12");
        registerUser("_ws");
        registerUser("admin");
        registerUser("admin12345678");

    }

}
