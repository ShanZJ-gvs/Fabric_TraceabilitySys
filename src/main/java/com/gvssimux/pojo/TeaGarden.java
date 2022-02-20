package com.gvssimux.pojo;

import lombok.Data;

@Data
public class TeaGarden {
    private String teaGardenId;

    private String teaGardenAddress;

    private String teaGardenArea;

    private String teaGardenLongitude;

    private String teaGardenTreekind;

    private String teaGardenTeaid;

    private String teaGardenAreaid;

    public TeaGarden(String teaGardenId, String teaGardenAddress, String teaGardenArea, String teaGardenLongitude) {
        this.teaGardenId = teaGardenId;
        this.teaGardenAddress = teaGardenAddress;
        this.teaGardenArea = teaGardenArea;
        this.teaGardenLongitude = teaGardenLongitude;
    }
}