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



    /**
     * 所属公司
     * */
    private String company;


    /**
     * 用于在富查询时标识查询类型的实体对象
     * */
    private final String type = "TeaArea";

}