package com.gvssimux.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class TeaRank {
    // 定级人
    private Employee teaRankPer;

    // 定级时间
    private Date teaRankTime;

    // 茶叶批编号
    private String teaRankId;

    // 茶叶等级
    private String teaRankRank;


}