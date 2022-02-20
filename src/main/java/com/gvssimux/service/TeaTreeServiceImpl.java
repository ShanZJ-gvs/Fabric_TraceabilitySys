package com.gvssimux.service;

import com.gvssimux.dao.TeaTreeDao;
import com.gvssimux.pojo.TeaTree;

import java.util.List;

public class TeaTreeServiceImpl implements TeaTreeService{
    private TeaTreeDao mapper;

    /*全部插入*/
    @Override
    public int insert(TeaTree record) {
        return mapper.insert(record);
    }

    /*部分插入*/
    @Override
    public int insertSelective(TeaTree record) {
        return mapper.insertSelective(record);
    }

    /*全部插入*/
    @Override
    public List<TeaTree> selectAll() {
        return mapper.selectAll();
    }

    /*spring自动装配*/
    public void setMapper(TeaTreeDao mapper) {
        this.mapper = mapper;
    }
}
