package com.gvssimux.dao;


import com.gvssimux.pojo.TeaPick;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaPickDao {
    /*全部插入*/
    int insert(TeaPick record);

    /*部分插入*/
    int insertSelective(TeaPick record);

    /*全部查询*/
    @Select("select * from tea.tea_pick")
    List<TeaPick> selectAll();
}