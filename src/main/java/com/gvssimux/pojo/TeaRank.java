package com.gvssimux.pojo;

import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
public class TeaRank {
    // 定级人
    private Employee teaRankPer;

    // 定级时间
    private String teaRankTime;

    // 茶叶批编号
    private String teaRankId;

    // 茶叶等级
    private String teaRankRank;

    // 公司
    private String company;


}