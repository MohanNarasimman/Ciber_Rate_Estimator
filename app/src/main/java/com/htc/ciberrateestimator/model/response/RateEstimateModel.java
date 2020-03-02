package com.htc.ciberrateestimator.model.response;

public class RateEstimateModel {

    private Data data;

    public RateEstimateModel(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RateEstimateModel{" +
                "data=" + data +
                '}';
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private Double minBillRate;
        private Double estimatedRevenueByRate;
        private Double minBillRateBySalary;
        private Double estimatedRevenueBySalary;

        @Override
        public String toString() {
            return "Data{" +
                    "minBillRate=" + minBillRate +
                    ", estimatedRevenueByRate=" + estimatedRevenueByRate +
                    ", minBillRateBySalary=" + minBillRateBySalary +
                    ", estimatedRevenueBySalary=" + estimatedRevenueBySalary +
                    '}';
        }

        public Data(Double minBillRate, Double estimatedRevenueByRate, Double minBillRateBySalary, Double estimatedRevenueBySalary) {
            this.minBillRate = minBillRate;
            this.estimatedRevenueByRate = estimatedRevenueByRate;
            this.minBillRateBySalary = minBillRateBySalary;
            this.estimatedRevenueBySalary = estimatedRevenueBySalary;
        }

        public Double getMinBillRate() {
            return minBillRate;
        }

        public void setMinBillRate(Double minBillRate) {
            this.minBillRate = minBillRate;
        }

        public Double getEstimatedRevenueByRate() {
            return estimatedRevenueByRate;
        }

        public void setEstimatedRevenueByRate(Double estimatedRevenueByRate) {
            this.estimatedRevenueByRate = estimatedRevenueByRate;
        }

        public Double getMinBillRateBySalary() {
            return minBillRateBySalary;
        }

        public void setMinBillRateBySalary(Double minBillRateBySalary) {
            this.minBillRateBySalary = minBillRateBySalary;
        }

        public Double getEstimatedRevenueBySalary() {
            return estimatedRevenueBySalary;
        }

        public void setEstimatedRevenueBySalary(Double estimatedRevenueBySalary) {
            this.estimatedRevenueBySalary = estimatedRevenueBySalary;
        }
    }
}
