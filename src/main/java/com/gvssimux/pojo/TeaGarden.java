package com.gvssimux.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeaGarden {

    // 茶园编号
    private String teaGardenId1;

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

    private String teaGardenAreaid;

    private String teaGardenTreekind;

    private String teaGardenTeaid;



}