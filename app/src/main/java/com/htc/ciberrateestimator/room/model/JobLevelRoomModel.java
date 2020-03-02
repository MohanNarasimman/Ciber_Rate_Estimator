package com.htc.ciberrateestimator.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "job_level")
public class JobLevelRoomModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @ColumnInfo(name = "roleID")
    private long roleID;

    @ColumnInfo(name = "jobTitleID")
    private long jobTitleID;

    @ColumnInfo(name = "roleName")
    private String roleName;

    public long getRoleID() {
        return roleID;
    }

    public void setRoleID(long value) {
        this.roleID = value;
    }

    public long getJobTitleID() {
        return jobTitleID;
    }

    public void setJobTitleID(long value) {
        this.jobTitleID = value;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String value) {
        this.roleName = value;
    }

    public JobLevelRoomModel(long roleID, long jobTitleID, String roleName) {
        this.roleID = roleID;
        this.jobTitleID = jobTitleID;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "JobRole{" +
                "roleID=" + roleID +
                ", jobTitleID=" + jobTitleID +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
