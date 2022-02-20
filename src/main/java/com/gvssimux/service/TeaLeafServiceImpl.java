package com.gvssimux.service;

import com.gvssimux.dao.TeaLeafDao;
import com.gvssimux.pojo.TeaLeaf;

import java.util.List;

public class TeaLeafServiceImpl implements TeaLeafService{

    private TeaLeafDao mapper;

    /*全部插入*/
    @Override
    public int insert(TeaLeaf record) {
        return mapper.insert(record);
    }

    /*部分插入*/
    @Override
    public int insertSelective(TeaLeaf record) {
        return mapper.insertSelective(record);
    }

    /*全部查询*/
    @Override
    public List<TeaLeaf> selectAll() {
        return mapper.selectAll();
    }


    /*spring自动装配*/
    public void setMapper(TeaLeafDao mapper) {
        this.mapper = mapper;
    }
}
