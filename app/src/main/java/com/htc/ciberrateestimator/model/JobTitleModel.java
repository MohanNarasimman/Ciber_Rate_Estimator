package com.htc.ciberrateestimator.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JobTitleModel implements Serializable {

    @SerializedName("jobTitleID")
    private long jobTitleId;

    @SerializedName("jobTitleName")
    private String jobTitleName;

    public JobTitleModel(long jobTitleId, String jobTitleName) {
        this.jobTitleId = jobTitleId;
        this.jobTitleName = jobTitleName;
    }

    @Override
    public String toString() {
        return "JobTitleModel{" +
                "jobTitleId=" + jobTitleId +
                ", jobTitleName='" + jobTitleName + '\'' +
                '}';
    }

    public long getJobTitleId() {
        return jobTitleId;
    }

    public void setJobTitleId(int jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    public String getJobTitleName() {
        return jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }
}
