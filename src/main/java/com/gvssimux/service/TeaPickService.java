package com.gvssimux.service;

import com.gvssimux.pojo.TeaPick;

import java.util.List;

public interface TeaPickService {
    /*全部插入*/
    int insert(TeaPick record);

    /*部分插入*/
    int insertSelective(TeaPick record);

    /*全部查询*/
    List<TeaPick> selectAll();
}
