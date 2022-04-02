package com.gvssimux.pojo;


import lombok.Data;

@Data
public class UserBasic  {

    private Integer userId;

    private String userUuid;

    private String userLoginid;

    private String userPwd;

    private String userUname;

    private String userSign;

    private String user_tel;

    private String userEmail;

    private String userSex;

    private String userAge;


    public UserBasic(String s, String user_uname, String user_loginid, String user_pwd2, String admin, int i) {
    }

    public UserBasic(Integer userId, String userUuid, String userLoginid, String userPwd, String userUname, String userSign, String user_tel, String userEmail, String userSex, String userAge) {
        this.userId = userId;
        this.userUuid = userUuid;
        this.userLoginid = userLoginid;
        this.userPwd = userPwd;
        this.userUname = userUname;
        this.userSign = userSign;
        this.user_tel = user_tel;
        this.userEmail = userEmail;
        this.userSex = userSex;
        this.userAge = userAge;
    }

    public UserBasic() {
    }
}