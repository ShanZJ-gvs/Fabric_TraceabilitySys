package com.gvssimux.dao;

import com.gvssimux.pojo.TeaRank;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaRankDao {
    /*全部插入*/
    int insert(TeaRank record);

    /*部分插入*/
    int insertSelective(TeaRank record);

    /*全部插询*/
    @Select("select * from tea.tea_rank")
    List<TeaRank> selectAll();
}