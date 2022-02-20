package com.gvssimux.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class TeaPick {
    private String teaTreeId;

    private String teaPickId;

    private String teaPickPerid;

    private Date teaPickTime;

    private String teaPickQuality;


}