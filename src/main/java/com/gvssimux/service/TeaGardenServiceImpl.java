package com.gvssimux.service;

import com.gvssimux.dao.TeaGardenDao;
import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaGarden;

import java.util.List;

public class TeaGardenServiceImpl implements TeaGardenService{
    private TeaGardenDao mapper;

    /*全部查询*/
    @Override
    public List<TeaGarden> selectAll() {
        return mapper.selectAll();
    }

    /*全部插入*/
    @Override
    public int insert(TeaGarden record) {
        return mapper.insert(record);
    }

    /*部分插入*/
    @Override
    public int insertSelective(TeaGarden record) {
        return mapper.insertSelective(record);
    }

    /*spring自动装配*/
    public void setMapper(TeaGardenDao mapper) {
        this.mapper = mapper;
    }
}
