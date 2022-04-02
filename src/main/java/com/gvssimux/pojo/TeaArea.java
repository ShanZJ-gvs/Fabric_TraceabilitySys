package com.gvssimux.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.List;


@Data
@Accessors(chain = true)
public class TeaArea {
    /**
     * 茶区--唯一编号
     * */

    private String teaAreaId1;

    /**
     * 茶区--名字
     * */

    private String teaAreaName;

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

    private String teaGardenId2;


    private List<String> teaKinds;


    private List<String> teaGardenIds;


    private List<String>  teaAreaSonids;


    public TeaArea(@JsonProperty("teaAreaId1") String teaAreaId1,@JsonProperty("teaAreaAddress") String teaAreaAddress,@JsonProperty("teaAreaLongitude") String teaAreaLongitude,@JsonProperty("teaAreaArea") String teaAreaArea,@JsonProperty("teaAreaDay") String teaAreaDay,@JsonProperty("teaAreaId2") String teaAreaId2,@JsonProperty("teaGardenId2") String teaGardenId2, List<String> teaKinds, List<String> teaGardenIds, List<String> teaAreaSonids) {
        this.teaAreaId1 = teaAreaId1;
        this.teaAreaAddress = teaAreaAddress;
        this.teaAreaLongitude = teaAreaLongitude;
        this.teaAreaArea = teaAreaArea;
        this.teaAreaDay = teaAreaDay;
        this.teaAreaId2 = teaAreaId2;
        this.teaGardenId2 = teaGardenId2;
        this.teaKinds = teaKinds;
        this.teaGardenIds = teaGardenIds;
        this.teaAreaSonids = teaAreaSonids;
    }

    public TeaArea() {
    }
}