package com.gvssimux.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class TeaWarehouse {
    private String teaWarehouseId;

    private String teaWarehousePerid;

    private Date teaWarehouseIntime;

    private Date teaWarehouseOuttime;

}