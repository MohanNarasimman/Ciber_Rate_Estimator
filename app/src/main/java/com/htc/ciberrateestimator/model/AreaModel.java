package com.htc.ciberrateestimator.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AreaModel implements Serializable {

    @SerializedName("areaId")
    private long id;

    @SerializedName("areaName")
    private String areaLocation;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AreaModel(long id, String areaLocation) {
        this.id = id;
        this.areaLocation = areaLocation;
    }

    @Override
    public String toString() {
        return "AreaModel{" +
                "id=" + id +
                ", areaLocation='" + areaLocation + '\'' +
                '}';
    }

    public AreaModel() {
    }


    public String getAreaLocation() {
        return areaLocation;
    }

    public void setAreaLocation(String areaLocation) {
        this.areaLocation = areaLocation;
    }
}
