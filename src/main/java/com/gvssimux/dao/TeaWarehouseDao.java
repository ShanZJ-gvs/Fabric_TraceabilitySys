package com.gvssimux.dao;


import com.gvssimux.pojo.TeaWarehouse;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaWarehouseDao {
    /*全部插入*/
    int insert(TeaWarehouse record);

    /*部分插入*/
    int insertSelective(TeaWarehouse record);

    /*全部查询*/
    @Select("select * from tea.tea_warehouse")
    List<TeaWarehouse> selectAll();
}