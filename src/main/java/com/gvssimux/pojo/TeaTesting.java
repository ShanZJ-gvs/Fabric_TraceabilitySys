package com.gvssimux.pojo;

import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
public class TeaTesting {
    // 质检人
    private Employee teaTestingPer;

    // 质检批次Id
    private String teaTestingId;

    // 质检结果
    private String teaTestingResult;

    // 质检时间
    private String teaTestingTime;

    // 小盒编号
    private String teaTestingSmllBoxId;

    // 大盒编号
    private String teaTestingBigBoxId;

    // 公司
    private String company;



}