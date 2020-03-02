package com.htc.ciberrateestimator.room.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "area")
public class AreaRoomModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "areaID")
    private long areaID;

    @ColumnInfo(name = "areaName")
    private String areaName;

    public long getAreaID() {
        return areaID;
    }

    public void setAreaID(long value) {
        this.areaID = value;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String value) {
        this.areaName = value;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaID=" + areaID +
                ", areaName='" + areaName + '\'' +
                '}';
    }

    public AreaRoomModel(long areaID, String areaName) {
        this.areaID = areaID;
        this.areaName = areaName;
    }
}
