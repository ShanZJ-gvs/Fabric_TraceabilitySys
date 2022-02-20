package com.gvssimux.dao;

import com.gvssimux.pojo.TeaTesting;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaTestingDao {
    /*全部插入*/
    int insert(TeaTesting record);

    /*部分插入*/
    int insertSelective(TeaTesting record);

    /*全部查询*/
    @Select("select * from tea.tea_testing")
    List<TeaTesting> selectAll();
}