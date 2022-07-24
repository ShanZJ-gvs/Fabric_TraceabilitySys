package com.gvssimux.pojo;

import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
public class TeaGarden {

    // 茶园编号

    private String teaGardenId1;

    // 茶园名字

    private String teaGardenName;

    // 茶园地址

    private String teaGardenAddress;

    // 茶园面积

    private String teaGardenArea;

    // 茶园经纬度

    private String teaGardenLongitude;

    // 所属茶区编号

    private String teaAreaId2;

    // 所属茶园编号

    private String teaGardenId2;


    /**
     * 所属公司
     * */
    private String company;


    /**
     * 用于在富查询时标识查询类型的实体对象
     * */
    private final String type = "TeaGarden";




}