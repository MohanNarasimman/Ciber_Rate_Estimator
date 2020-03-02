package com.htc.ciberrateestimator.model.response;

import com.htc.ciberrateestimator.model.JobTitleModel;

import java.util.ArrayList;
import java.util.List;

public class JobTitleModelResponse {

    private List<JobTitleModel> data = new ArrayList<>();

    @Override
    public String toString() {
        return "JobTitleModelResponse{" +
                "data=" + data +
                '}';
    }

    public JobTitleModelResponse(List<JobTitleModel> data) {
        this.data = data;
    }

    public List<JobTitleModel> getData() {
        return data;
    }

    public void setData(List<JobTitleModel> data) {
        this.data = data;
    }
}
