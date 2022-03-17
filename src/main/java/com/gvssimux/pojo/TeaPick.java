package com.gvssimux.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class TeaPick {
    // 采茶师编号
    private String teaPickPerId;

    // 采摘时间
    private Date teaPickTime;

    // 茶叶批编号
    private String teaPickId;

    // 所属茶树编号
    private String teaTreeId2;

    // 鲜叶质量
    private String teaPickQuality;

}