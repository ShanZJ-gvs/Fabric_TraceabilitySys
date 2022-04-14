package com.gvssimux.pojo;

import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
public class TeaPick {
    // 采茶师
    private Employee teaPickPer;

    // 采摘时间
    private String teaPickTime;

    // 采摘月份
    private String month;

    // 茶叶批编号
    private String teaPickId;

    // 所属茶树编号
    private String teaTreeId2;

    // 鲜叶质量
    private String teaPickQuality;

    // 数量
    private Integer output;

    // 公司
    private String company;



    /**
     * 用于在富查询时标识查询类型的实体对象
     * */
    private final String type = "TeaPick";

}