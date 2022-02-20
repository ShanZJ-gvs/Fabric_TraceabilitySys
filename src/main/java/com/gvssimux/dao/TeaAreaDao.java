package com.gvssimux.dao;

import com.gvssimux.pojo.TeaArea;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaAreaDao {

    /*查询全部*/
    @Select("select * from tea.tea_area")
    List<TeaArea> selectAll();

    /*全部插入*/
    int insert(TeaArea record);

    /*部分插入*/
    int insertSelective(TeaArea record);
}
