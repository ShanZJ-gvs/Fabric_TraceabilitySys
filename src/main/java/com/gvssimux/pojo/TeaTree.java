package com.gvssimux.pojo;

import lombok.Data;
import lombok.experimental.Accessors;




@Data
@Accessors(chain = true)
public class TeaTree {
    // 茶树编号

    private String teaTreeId;

    // 茶树地址

    private String teaTreeAddress;

    // 茶树经纬度

    private String teaTreeLongitude;

    // 茶树高度

    private String teaTreeHeight;

    // 茶树种类

    private String teaTreeKind;

    // 茶树状态

    private String teaTreeState;

    // 茶树护养情况

    private String teaTreeCultivate;

    // 茶树生长环境

    private String teaTreeGrowingEnv;

    // 茶树所属茶区

    private String teaAreaId2;

    // 茶树所属茶园

    private String teaGardenId2;


    /**
     * 所属公司
     * */
    private String company;


    /**
     * 用于在富查询时标识查询类型的实体对象
     * */
    private final String type = "TeaTree";



}