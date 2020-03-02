package com.htc.ciberrateestimator.model;

public class RateEstimatorModel {
    private AreaModel areaModel;
    private JobLevelModel jobLevelModel;
    private JobTitleModel jobTitleModel;
    private Double gP = 35.5;
    private long estimatedHours = 2000;

    private Double minBillRate = 0.0;
    private Double estimatedRevenueByRate = 0.0;
    private Double minBillRateBySalary = 0.0;
    private Double estimatedRevenueBySalary = 0.0;

    public RateEstimatorModel(Double gP, long estimatedHours) {
        this.gP = gP;
        this.estimatedHours = estimatedHours;
    }

    public RateEstimatorModel(AreaModel areaModel, JobLevelModel jobLevelModel, JobTitleModel jobTitleModel, Double gP, long estimatedHours, Double minBillRate, Double estimatedRevenueByRate, Double minBillRateBySalary, Double estimatedRevenueBySalary) {
        this.areaModel = areaModel;
        this.jobLevelModel = jobLevelModel;
        this.jobTitleModel = jobTitleModel;
        this.gP = gP;
        this.estimatedHours = estimatedHours;
        this.minBillRate = minBillRate;
        this.estimatedRevenueByRate = estimatedRevenueByRate;
        this.minBillRateBySalary = minBillRateBySalary;
        this.estimatedRevenueBySalary = estimatedRevenueBySalary;
    }

    @Override
    public String toString() {
        return "RateEstimatorModel{" +
                "areaModel=" + areaModel +
                ", jobLevelModel=" + jobLevelModel +
                ", jobTitleModel=" + jobTitleModel +
                ", gP=" + gP +
                ", estimatedHours=" + estimatedHours +
                ", minBillRate=" + minBillRate +
                ", estimatedRevenueByRate=" + estimatedRevenueByRate +
                ", minBillRateBySalary=" + minBillRateBySalary +
                ", estimatedRevenueBySalary=" + estimatedRevenueBySalary +
                '}';
    }

    public AreaModel getAreaModel() {
        return areaModel;
    }

    public void setAreaModel(AreaModel areaModel) {
        this.areaModel = areaModel;
    }

    public JobLevelModel getJobLevelModel() {
        return jobLevelModel;
    }

    public void setJobLevelModel(JobLevelModel jobLevelModel) {
        this.jobLevelModel = jobLevelModel;
    }

    public JobTitleModel getJobTitleModel() {
        return jobTitleModel;
    }

    public void setJobTitleModel(JobTitleModel jobTitleModel) {
        this.jobTitleModel = jobTitleModel;
    }

    public Double getgP() {
        return gP;
    }

    public void setgP(Double gP) {
        this.gP = gP;
    }

    public long getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(long estimatedHours) {
        this.estimatedHours = estimatedHours;
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
