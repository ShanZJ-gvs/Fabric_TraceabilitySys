package com.gvssimux.service;

import com.gvssimux.pojo.TeaTesting;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaTestingService {
    /*全部插入*/
    int insert(TeaTesting record);

    /*部分插入*/
    int insertSelective(TeaTesting record);

    /*全部查询*/
    List<TeaTesting> selectAll();
}
