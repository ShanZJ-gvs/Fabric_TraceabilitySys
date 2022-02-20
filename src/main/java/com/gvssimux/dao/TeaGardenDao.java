package com.gvssimux.dao;

import com.gvssimux.pojo.TeaGarden;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaGardenDao {
    /*全部插入*/
    int insert(TeaGarden record);

    /*部分插入*/
    int insertSelective(TeaGarden record);

    //查询全部
    @Select("select * from tea.tea_garden")
    List<TeaGarden> selectAll();
}