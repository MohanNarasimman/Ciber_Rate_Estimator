package com.htc.ciberrateestimator.model;

import com.google.gson.annotations.SerializedName;

public class JobLevelModel {

    @SerializedName("roleID")
    private long jobId;

    @SerializedName("roleName")
    private String jobLevelName;

//    @SerializedName("Salary")
//    private Double Salary;

//    public Double getSalary() {
//        return Salary;
//    }
//
//    public void setSalary(Double salary) {
//        Salary = salary;
//    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public JobLevelModel() {
    }

    public JobLevelModel(long jobId, String jobLevelName) {
        this.jobId = jobId;
        this.jobLevelName = jobLevelName;
    }

    public String getJobLevelName() {
        return jobLevelName;
    }

    public void setJobLevelName(String jobLevelName) {
        this.jobLevelName = jobLevelName;
    }

    @Override
    public String toString() {
        return "JobLevelModel{" +
                "jobId=" + jobId +
                ", jobLevelName='" + jobLevelName + '\'' +
                '}';
    }

}
