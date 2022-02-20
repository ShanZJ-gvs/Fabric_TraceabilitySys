package com.gvssimux.dao;

import com.gvssimux.pojo.TeaLeaf;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaLeafDao {

    /*全部插入*/
    int insert(TeaLeaf record);

    /*部分插入*/
    int insertSelective(TeaLeaf record);

    /*全部查询*/
    @Select("select * from tea.tea_leaf")
    List<TeaLeaf> selectAll();
}