package com.gvssimux.service;

import com.gvssimux.dao.TeaRankDao;
import com.gvssimux.pojo.TeaRank;

import java.util.List;

public class TeaRankServiceImpl implements TeaRankService{
    private TeaRankDao mapper;

    /*全部插入*/
    @Override
    public int insert(TeaRank record) {
        return mapper.insert(record);
    }

    /*部分插入*/
    @Override
    public int insertSelective(TeaRank record) {
        return mapper.insertSelective(record);
    }

    /*全部查询*/
    @Override
    public List<TeaRank> selectAll() {
        return mapper.selectAll();
    }

    /*spring自动转配*/
    public void setMapper(TeaRankDao mapper) {
        this.mapper = mapper;
    }
}
