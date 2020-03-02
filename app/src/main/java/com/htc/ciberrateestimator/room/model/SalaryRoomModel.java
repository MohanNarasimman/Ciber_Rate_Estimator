package com.htc.ciberrateestimator.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "salary")
public class SalaryRoomModel {

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

    @ColumnInfo(name = "jobTitleID")
    private long jobTitleID;

    @ColumnInfo(name = "roleID")
    private long roleID;

    @ColumnInfo(name = "minSalary")
    private long minSalary;

    @ColumnInfo(name = "maxSalary")
    private long maxSalary;

    @ColumnInfo(name = "averageSalary")
    private long averageSalary;

    public long getAreaID() {
        return areaID;
    }

    public void setAreaID(long value) {
        this.areaID = value;
    }

    public long getJobTitleID() {
        return jobTitleID;
    }

    public void setJobTitleID(long value) {
        this.jobTitleID = value;
    }

    public long getRoleID() {
        return roleID;
    }

    public void setRoleID(long value) {
        this.roleID = value;
    }

    public long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(long value) {
        this.minSalary = value;
    }

    public long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(long value) {
        this.maxSalary = value;
    }

    public long getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(long value) {
        this.averageSalary = value;
    }

    public SalaryRoomModel(long areaID, long jobTitleID, long roleID, long minSalary, long maxSalary, long averageSalary) {
        this.areaID = areaID;
        this.jobTitleID = jobTitleID;
        this.roleID = roleID;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.averageSalary = averageSalary;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "areaID=" + areaID +
                ", jobTitleID=" + jobTitleID +
                ", roleID=" + roleID +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                ", averageSalary=" + averageSalary +
                '}';
    }
}
