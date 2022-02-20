package com.gvssimux.service;

import com.gvssimux.dao.TeaPickDao;
import com.gvssimux.pojo.TeaPick;

import java.util.List;

public class TeaPickServiceImpl implements TeaPickService{
    private TeaPickDao mapper;

    /*全部插入*/
    @Override
    public int insert(TeaPick record) {
        return mapper.insert(record);
    }

    /*部分插入*/
    @Override
    public int insertSelective(TeaPick record) {
        return mapper.insertSelective(record);
    }

    /*全部查询*/
    @Override
    public List<TeaPick> selectAll() {
        return mapper.selectAll();
    }

    /*spring自动装配*/
    public void setMapper(TeaPickDao mapper) {
        this.mapper = mapper;
    }
}
