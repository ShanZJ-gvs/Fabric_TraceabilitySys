package com.gvssimux.service;

import com.gvssimux.dao.TeaPackDao;
import com.gvssimux.pojo.TeaPack;

import java.util.List;

public class TeaPackServiceImpl implements TeaPackService{
    private TeaPackDao mapper;

    /*全部插入*/
    @Override
    public int insert(TeaPack record) {
        return mapper.insert(record);
    }

    /*部分插入*/
    @Override
    public int insertSelective(TeaPack record) {
        return mapper.insertSelective(record);
    }

    /*全部查询*/
    @Override
    public List<TeaPack> selectAll() {
        return mapper.selectAll();
    }

    /*Spring自动装配*/
    public void setMapper(TeaPackDao mapper) {
        this.mapper = mapper;
    }
}
