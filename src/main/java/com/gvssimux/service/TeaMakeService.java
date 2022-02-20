package com.gvssimux.service;

import com.gvssimux.pojo.TeaMake;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaMakeService {
    /*全部插入*/
    int insert(TeaMake record);

    /*部分插入*/
    int insertSelective(TeaMake record);

    /*全部查询*/
    List<TeaMake> selectAll();
}
