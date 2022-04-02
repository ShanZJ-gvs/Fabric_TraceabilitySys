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

    // 制茶工艺

    private String teaMakeWay;

    // 茶叶批编号  与teaPickId是对应的

    private String teaMakeId;

}