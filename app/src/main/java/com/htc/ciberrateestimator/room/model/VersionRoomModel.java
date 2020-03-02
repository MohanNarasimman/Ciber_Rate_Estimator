package com.htc.ciberrateestimator.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "version")
public class VersionRoomModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "versionID")
    private long versionID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "lastUpdatedDateTime")
    private String lastUpdatedDateTime;

    public long getVersionID() {
        return versionID;
    }

    public void setVersionID(long value) {
        this.versionID = value;
    }

    public String getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(String value) {
        this.lastUpdatedDateTime = value;
    }

    public VersionRoomModel(long versionID, String lastUpdatedDateTime) {
        this.versionID = versionID;
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    @Override
    public String toString() {
        return "Version{" +
                "versionID=" + versionID +
                ", lastUpdatedDateTime='" + lastUpdatedDateTime + '\'' +
                '}';
    }

}
