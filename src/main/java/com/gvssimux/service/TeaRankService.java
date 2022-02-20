package com.gvssimux.service;

import com.gvssimux.pojo.TeaRank;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaRankService {
    /*全部插入*/
    int insert(TeaRank record);

    /*部分插入*/
    int insertSelective(TeaRank record);

    /*全部插询*/
    List<TeaRank> selectAll();
}
