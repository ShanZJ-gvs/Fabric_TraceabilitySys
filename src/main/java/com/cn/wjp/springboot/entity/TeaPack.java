package com.cn.wjp.springboot.entity;

import java.util.Date;

public class TeaPack {
    private String teaPackId;

    private String teaPackPerid;

    private Date teaPackTime;

    private String teaPackBigboxid;

    private String teaPackSmllboxid;

    private String teaPackSsl;

    public String getTeaPackId() {
        return teaPackId;
    }

    public void setTeaPackId(String teaPackId) {
        this.teaPackId = teaPackId == null ? null : teaPackId.trim();
    }

    public String getTeaPackPerid() {
        return teaPackPerid;
    }

    public void setTeaPackPerid(String teaPackPerid) {
        this.teaPackPerid = teaPackPerid == null ? null : teaPackPerid.trim();
    }

    public Date getTeaPackTime() {
        return teaPackTime;
    }

    public void setTeaPackTime(Date teaPackTime) {
        this.teaPackTime = teaPackTime;
    }

    public String getTeaPackBigboxid() {
        return teaPackBigboxid;
    }

    public void setTeaPackBigboxid(String teaPackBigboxid) {
        this.teaPackBigboxid = teaPackBigboxid == null ? null : teaPackBigboxid.trim();
    }

    public String getTeaPackSmllboxid() {
        return teaPackSmllboxid;
    }

    public void setTeaPackSmllboxid(String teaPackSmllboxid) {
        this.teaPackSmllboxid = teaPackSmllboxid == null ? null : teaPackSmllboxid.trim();
    }

    public String getTeaPackSsl() {
        return teaPackSsl;
    }

    public void setTeaPackSsl(String teaPackSsl) {
        this.teaPackSsl = teaPackSsl == null ? null : teaPackSsl.trim();
    }
}