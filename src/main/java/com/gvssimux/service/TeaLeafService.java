package com.gvssimux.service;

import com.gvssimux.pojo.TeaLeaf;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaLeafService {
    /*全部插入*/
    int insert(TeaLeaf record);

    /*部分插入*/
    int insertSelective(TeaLeaf record);

    /*全部查询*/
    List<TeaLeaf> selectAll();
}
