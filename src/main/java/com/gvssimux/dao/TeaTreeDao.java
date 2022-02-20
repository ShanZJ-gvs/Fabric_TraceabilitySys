package com.gvssimux.dao;

import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaTree;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaTreeDao {
    /*全部插入*/
    int insert(TeaTree record);

    /*部分插入*/
    int insertSelective(TeaTree record);

    //查询全部
    @Select("select * from tea.tea_tree")
    List<TeaTree> selectAll();
}