package com.gvssimux.service;

import com.gvssimux.pojo.TeaPack;

import java.util.List;

public interface TeaPackService{
    /*全部插入*/
    int insert(TeaPack record);

    /*部分插入*/
    int insertSelective(TeaPack record);

    /*全部查询*/
    List<TeaPack> selectAll();
}
