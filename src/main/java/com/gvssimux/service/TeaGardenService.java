package com.gvssimux.service;

import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.pojo.fabquery.TeaAreaQueryResultList;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeaGardenService {


    /*全部插入*/
    String insertOne(TeaGarden record);

    /*查询全部*/
    TeaAreaQueryResultList selectOffsetLimit(int offset,int limit) throws Exception;




}
