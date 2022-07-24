package com.gvssimux.pojo;

import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
public class TeaPack {
    // 包装人
    private Employee teaPackPer;

    // 包装时间
    private String teaPackTime;

    // 月份
    private String month;

    // 小盒编号
    private String teaPackSmllBoxId;

    // 大盒编号
    private String teaPackBigBoxId;

    // 茶叶批编号
    private String teaPackID;

    // 公司
    private String company;

    /**
     * 用于在富查询时标识查询类型的实体对象
     * */
    private final String type = "TeaPack";


}