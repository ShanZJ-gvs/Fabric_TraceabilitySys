package com.cn.wjp.springboot.dao;

import com.cn.wjp.springboot.entity.TeaPack;

public interface TeaPackMapper {
    int insert(TeaPack record);

    int insertSelective(TeaPack record);
}