package com.htc.ciberrateestimator.model.response;

import java.util.ArrayList;
import java.util.List;

public class VersionResponse {

    private List<Data> data  = new ArrayList<>();

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> value) {
        this.data = value;
    }

    @Override
    public String toString() {
        return "VersionResponse{" +
                "data=" + data +
                '}';
    }

    public VersionResponse(List<Data> data) {
        this.data = data;
    }

    public class Data {
        private long versionID;
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

        public Data(String lastUpdatedDateTime) {
            this.lastUpdatedDateTime = lastUpdatedDateTime;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "versionID=" + versionID +
                    ", lastUpdatedDateTime='" + lastUpdatedDateTime + '\'' +
                    '}';
        }
    }
}
