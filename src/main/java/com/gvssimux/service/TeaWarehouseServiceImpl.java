package com.gvssimux.service;

import com.gvssimux.dao.TeaWarehouseDao;
import com.gvssimux.pojo.TeaWarehouse;

import java.util.List;

public class TeaWarehouseServiceImpl implements TeaWarehouseService{
    private TeaWarehouseDao mapper;

    /*全部插入*/
    @Override
    public int insert(TeaWarehouse record) {
        return mapper.insert(record);
    }

    /*部分插入*/
    @Override
    public int insertSelective(TeaWarehouse record) {
        return mapper.insertSelective(record);
    }

    /*全部查询*/
    @Override
    public List<TeaWarehouse> selectAll() {
        return mapper.selectAll();
    }

    /*spring自动装配*/
    public void setMapper(TeaWarehouseDao mapper) {
        this.mapper = mapper;
    }
}
