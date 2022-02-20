package com.gvssimux.dao;


import com.gvssimux.pojo.TeaMake;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaMakeDao {
    /*全部插入*/
    int insert(TeaMake record);

    /*部分插入*/
    int insertSelective(TeaMake record);

    /*全部查询*/
    @Select("select * from tea.tea_make")
    List<TeaMake> selectAll();
}