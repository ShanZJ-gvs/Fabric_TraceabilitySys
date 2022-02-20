package com.gvssimux.service;

import com.gvssimux.dao.UserRegisterDao;
import com.gvssimux.pojo.UserBasic;
import com.gvssimux.service.UserRegisterService;

import java.util.Map;

public class UserRegisterServiceImpl implements UserRegisterService {
    private UserRegisterDao mapper;

    @Override
    public int addUserBasic(Map<String, Object> map) {
        return mapper.addUserBasic(map);
    }

    @Override
    public UserBasic getUserBasicByUserUname(int user_id) {
        return mapper.getUserBasicByUserUname(user_id);
    }



    public void setMapper(UserRegisterDao mapper) {
        this.mapper = mapper;
    }
}
