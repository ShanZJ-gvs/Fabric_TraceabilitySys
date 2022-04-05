package com.gvssimux.service;

import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.fabquery.TeaAreaQueryResultList;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaAreaService {



    /*全部插入*/
    String insertOne(TeaArea record);

    /*查询全部*/
    TeaAreaQueryResultList selectOffsetLimit(int offset,int limit) throws Exception;






//

//
//    /*部分插入*/
//    int insertSelective(TeaArea record);
}
