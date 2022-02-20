package com.gvssimux.service;

import com.gvssimux.dao.TeaAreaDao;
import com.gvssimux.pojo.TeaArea;

import java.util.List;

public class TeaAreaServiceImpl implements TeaAreaService {
    private TeaAreaDao mapper;

    /*查询全部*/
    @Override
    public int insert(TeaArea record) {
        return mapper.insert(record);
    }

    /*全部插入*/
    @Override
    public int insertSelective(TeaArea record) {
        return mapper.insertSelective(record);
    }

    /*部分插入*/
    @Override
    public List<TeaArea> selectAll() {
        return mapper.selectAll();
    }


    /*Spring自动装配*/
    public void setMapper(TeaAreaDao mapper) {
        this.mapper = mapper;
    }
}
