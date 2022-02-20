package com.gvssimux.service;

import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaGarden;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaGardenService {

    //查询全部
    List<TeaGarden> selectAll();

    /*全部插入*/
    int insert(TeaGarden record);

    /*部分插入*/
    int insertSelective(TeaGarden record);

}
