package com.gvssimux.dao;


import com.gvssimux.pojo.TeaPack;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaPackDao {
    /*全部插入*/
    int insert(TeaPack record);

    /*部分插入*/
    int insertSelective(TeaPack record);

    /*全部查询*/
    @Select("select * from tea.tea_pack")
    List<TeaPack> selectAll();
}
