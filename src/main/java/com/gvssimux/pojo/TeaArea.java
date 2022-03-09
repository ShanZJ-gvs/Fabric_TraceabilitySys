package com.gvssimux.pojo;


import lombok.Data;

import java.util.List;

@Data
public class TeaArea {
    /**
     * 茶区--唯一编号
     * */
    private String teaAreaId1;

    /**
     * 茶区--地址
     * */
    private String teaAreaAddress;
    /**
     * 茶区--经纬度
     * */
    private String teaAreaLongitude;

    /**
     * 茶区--面积
     * */
    private String teaAreaArea;

    /**
     * 茶区--晴天数
     * */
    private String teaAreaDay;

    /**
     * 所属于的--茶区编号
     * */
    private String teaAreaId2;

    /**
     * 所属于的--茶园编号
     * */
    private String teaGardenId;

    private List<String> teaKinds;

    private List<String> teaGardenIds;

    private List<String>  teaAreaSonids;

}