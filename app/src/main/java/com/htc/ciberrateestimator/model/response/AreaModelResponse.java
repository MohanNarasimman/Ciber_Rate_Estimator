package com.htc.ciberrateestimator.model.response;


import androidx.room.Entity;

import com.htc.ciberrateestimator.model.AreaModel;

import java.util.ArrayList;
import java.util.List;

public class AreaModelResponse {

    List<AreaModel> data = new ArrayList<>();

    public List<AreaModel> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "AreaModelResponse{" +
                "data=" + data +
                '}';
    }

    public void setData(List<AreaModel> data) {
        this.data = data;
    }

    public AreaModelResponse(List<AreaModel> data) {
        this.data = data;
    }
}
