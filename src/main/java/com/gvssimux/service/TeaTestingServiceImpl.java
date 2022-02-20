package com.gvssimux.service;

import com.gvssimux.dao.TeaTestingDao;
import com.gvssimux.pojo.TeaTesting;

import java.util.List;

public class TeaTestingServiceImpl implements TeaTestingService{
    private TeaTestingDao mapper;

    /*全部插入*/
    @Override
    public int insert(TeaTesting record) {
        return 0;
    }

    /*部分插入*/
    @Override
    public int insertSelective(TeaTesting record) {
        return 0;
    }

    /*全部查询*/
    @Override
    public List<TeaTesting> selectAll() {
        return null;
    }

    /*spring自动装配*/
    public void setMapper(TeaTestingDao mapper) {
        this.mapper = mapper;
    }
}
