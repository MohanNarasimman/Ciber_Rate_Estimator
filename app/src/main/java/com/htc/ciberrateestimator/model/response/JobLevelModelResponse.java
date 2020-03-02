package com.htc.ciberrateestimator.model.response;

import com.htc.ciberrateestimator.model.JobLevelModel;

import java.util.ArrayList;
import java.util.List;

public class JobLevelModelResponse {
    List<JobLevelModel> data = new ArrayList<>();

    public JobLevelModelResponse(List<JobLevelModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JobLevelModelResponse{" +
                "data=" + data +
                '}';
    }

    public List<JobLevelModel> getData() {
        return data;
    }

    public void setData(List<JobLevelModel> data) {
        this.data = data;
    }
}
