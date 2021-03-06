package com.gvssimux.pojo;

import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
public class TeaMake {
    // 制茶师
    private Employee teaMakePer;

    // 制茶时间
    private String teaMakeTime;

    // 采摘月份
    private String month;

    // 制茶工艺
    private String teaMakeWay;

    // 茶叶批编号  与teaPickId是对应的
    private String teaMakeId;

    // 数量
    private Integer output;

    // 公司
    private String company;


    /**
     * 用于在富查询时标识查询类型的实体对象
     * */
    private final String type = "TeaMake";

    /**
     * 用于在富查询时标识查询类型的实体对象
     * */
    private final String type2 = "TeaLeft";


}