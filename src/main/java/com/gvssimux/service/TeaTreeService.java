package com.gvssimux.service;

import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaTree;
import com.gvssimux.pojo.fabquery.TeaAreaQueryResultList;

import java.util.List;

public interface TeaTreeService {

    /*全部插入*/
    String insertOne(TeaTree record);

    /*查询全部*/
    TeaAreaQueryResultList selectOffsetLimit(int offset,int limit) throws Exception;




}
