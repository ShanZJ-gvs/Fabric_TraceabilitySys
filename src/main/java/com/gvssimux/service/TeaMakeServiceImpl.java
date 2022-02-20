package com.gvssimux.service;

import com.gvssimux.dao.TeaMakeDao;
import com.gvssimux.pojo.TeaMake;

import java.util.List;

public class TeaMakeServiceImpl implements TeaMakeService{
    private TeaMakeDao mapper;

    /*全部插入*/
    @Override
    public int insert(TeaMake record) {
        return mapper.insert(record);
    }

    /*部分插入*/
    @Override
    public int insertSelective(TeaMake record) {
        return mapper.insertSelective(record);
    }

    /*全部查询*/
    @Override
    public List<TeaMake> selectAll() {
        return mapper.selectAll();
    }

    /*spring自动装配*/
    public void setMapper(TeaMakeDao mapper) {
        this.mapper = mapper;
    }
}
