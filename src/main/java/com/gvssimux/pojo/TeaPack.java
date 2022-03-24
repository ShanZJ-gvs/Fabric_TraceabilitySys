package com.gvssimux.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class TeaPack {
    // 包装人
    private Employee teaPackPer;

    // 包装时间
    private String teaPackTime;

    // 小盒编号
    private String teaPackSmllBoxId;

    // 大盒编号
    private String teaPackBigBoxId;

    // 茶叶批编号
    private String teaPackID;

}