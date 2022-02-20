package com.gvssimux.service;

import com.gvssimux.pojo.TeaWarehouse;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaWarehouseService {

    /*全部插入*/
    int insert(TeaWarehouse record);

    /*部分插入*/
    int insertSelective(TeaWarehouse record);

    /*全部查询*/
    List<TeaWarehouse> selectAll();
}
