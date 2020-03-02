package com.htc.ciberrateestimator.room.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "job_title")
public class JobTitleRoomModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "jobTitleID")
    private long jobTitleID;

    @ColumnInfo(name = "jobTitleName")
    private String jobTitleName;

    public long getJobTitleID() {
        return jobTitleID;
    }

    public void setJobTitleID(long value) {
        this.jobTitleID = value;
    }

    public String getJobTitleName() {
        return jobTitleName;
    }

    public void setJobTitleName(String value) {
        this.jobTitleName = value;
    }

    public JobTitleRoomModel(long jobTitleID, String jobTitleName) {
        this.jobTitleID = jobTitleID;
        this.jobTitleName = jobTitleName;
    }

    @Override
    public String toString() {
        return "JobTitle{" +
                "jobTitleID=" + jobTitleID +
                ", jobTitleName='" + jobTitleName + '\'' +
                '}';
    }
}
