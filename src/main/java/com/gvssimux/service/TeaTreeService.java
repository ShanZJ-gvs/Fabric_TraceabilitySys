package com.gvssimux.service;

import com.gvssimux.pojo.TeaTree;

import java.util.List;

public interface TeaTreeService {
    /*全部插入*/
    int insert(TeaTree record);

    /*部分插入*/
    int insertSelective(TeaTree record);

    /*全部查询*/
    List<TeaTree> selectAll();

}
