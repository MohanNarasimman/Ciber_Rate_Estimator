package com.htc.ciberrateestimator.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetEstimateResponse {

    private List<Version> version = new ArrayList<>();
    private List<Area> area = new ArrayList<>();
    private List<JobTitle> jobTitle = new ArrayList<>();
    private List<JobRole> jobRole = new ArrayList<>();
    private List<Rate> rate = new ArrayList<>();
    private List<Salary> salary = new ArrayList<>();
    private List<FixedPercent> rateFixedPercent = new ArrayList<>();
    private List<FixedPercent> salaryFixedPercent = new ArrayList<>();

    public List<FixedPercent> getRateFixedPercent() {
        return rateFixedPercent;
    }

    public void setRateFixedPercent(List<FixedPercent> value) {
        this.rateFixedPercent = value;
    }

    public List<FixedPercent> getSalaryFixedPercent() {
        return salaryFixedPercent;
    }

    public void setSalaryFixedPercent(List<FixedPercent> value) {
        this.salaryFixedPercent = value;
    }

    public List<Version> getVersion() {
        return version;
    }

    public void setVersion(List<Version> value) {
        this.version = value;
    }

    public List<Area> getArea() {
        return area;
    }

    public void setArea(List<Area> value) {
        this.area = value;
    }

    public List<JobTitle> getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(List<JobTitle> value) {
        this.jobTitle = value;
    }

    public List<JobRole> getJobRole() {
        return jobRole;
    }

    public void setJobRole(List<JobRole> value) {
        this.jobRole = value;
    }

    public List<Rate> getRate() {
        return rate;
    }

    public void setRate(List<Rate> value) {
        this.rate = value;
    }

    public List<Salary> getSalary() {
        return salary;
    }

    public void setSalary(List<Salary> value) {
        this.salary = value;
    }

    public GetEstimateResponse(List<Version> version, List<Area> area, List<JobTitle> jobTitle, List<JobRole> jobRole, List<Rate> rate, List<Salary> salary) {
        this.version = version;
        this.area = area;
        this.jobTitle = jobTitle;
        this.jobRole = jobRole;
        this.rate = rate;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "GetEstimateResponse{" +
                "version=" + version +
                ", area=" + area +
                ", jobTitle=" + jobTitle +
                ", jobRole=" + jobRole +
                ", rate=" + rate +
                ", salary=" + salary +
                '}';
    }

    public class Area {

        @SerializedName("areaId")
        private long areaID;
        private String areaName;

        public long getAreaID() {
            return areaID;
        }

        public void setAreaID(long value) {
            this.areaID = value;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String value) {
            this.areaName = value;
        }

        @Override
        public String toString() {
            return "Area{" +
                    "areaID=" + areaID +
                    ", areaName='" + areaName + '\'' +
                    '}';
        }

        public Area(long areaID, String areaName) {
            this.areaID = areaID;
            this.areaName = areaName;
        }
    }

    public class JobTitle {
        private long jobTitleID;
        private String jobTitleName;

        public long getJobTitleID() {
            return jobTitleID;
        }

        public void setJobTitleID(long value) {
            this.jobTitleID = value;
        }

        public String getJobTitleName() {
            return jobTitleName;
        }

        public void setJobTitleName(String value) {
            this.jobTitleName = value;
        }

        public JobTitle(long jobTitleID, String jobTitleName) {
            this.jobTitleID = jobTitleID;
            this.jobTitleName = jobTitleName;
        }

        @Override
        public String toString() {
            return "JobTitle{" +
                    "jobTitleID=" + jobTitleID +
                    ", jobTitleName='" + jobTitleName + '\'' +
                    '}';
        }
    }

    public class JobRole {
        private long roleID;
        private long jobTitleID;
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

        public JobRole(long roleID, long jobTitleID, String roleName) {
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

    public class Rate {
        private long areaID;
        private long jobTitleID;
        private long roleID;
        private long minRate;
        private long maxRate;
        private long averageRate;

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

        public long getMinRate() {
            return minRate;
        }

        public void setMinRate(long value) {
            this.minRate = value;
        }

        public long getMaxRate() {
            return maxRate;
        }

        public void setMaxRate(long value) {
            this.maxRate = value;
        }

        public long getAverageRate() {
            return averageRate;
        }

        public void setAverageRate(long value) {
            this.averageRate = value;
        }

        public Rate(long areaID, long jobTitleID, long roleID, long minRate, long maxRate, long averageRate) {
            this.areaID = areaID;
            this.jobTitleID = jobTitleID;
            this.roleID = roleID;
            this.minRate = minRate;
            this.maxRate = maxRate;
            this.averageRate = averageRate;
        }

        @Override
        public String toString() {
            return "Rate{" +
                    "areaID=" + areaID +
                    ", jobTitleID=" + jobTitleID +
                    ", roleID=" + roleID +
                    ", minRate=" + minRate +
                    ", maxRate=" + maxRate +
                    ", averageRate=" + averageRate +
                    '}';
        }
    }

    public class Salary {
        private long areaID;
        private long jobTitleID;
        private long roleID;
        private long minSalary;
        private long maxSalary;
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

        public Salary(long areaID, long jobTitleID, long roleID, long minSalary, long maxSalary, long averageSalary) {
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

    public class Version {
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

        public Version(long versionID, String lastUpdatedDateTime) {
            this.versionID = versionID;
            this.lastUpdatedDateTime = lastUpdatedDateTime;
        }

        @Override
        public String toString() {
            return "Version{" +
                    "versionID=" + versionID +
                    ", lastUpdatedDateTime='" + lastUpdatedDateTime + '\'' +
                    '}';
        }
    }

    public class FixedPercent {
        public FixedPercent(long fixedPercentage_Id, double fixedPercentage, double fixedGP, long fixedEstimatedHours, long fixedAnnualBillableHours) {
            this.fixedPercentage_Id = fixedPercentage_Id;
            this.fixedPercentage = fixedPercentage;
            this.fixedGP = fixedGP;
            this.fixedEstimatedHours = fixedEstimatedHours;
            this.fixedAnnualBillableHours = fixedAnnualBillableHours;
        }

        @Override
        public String toString() {
            return "FixedPercent{" +
                    "fixedPercentage_Id=" + fixedPercentage_Id +
                    ", fixedPercentage=" + fixedPercentage +
                    ", fixedGP=" + fixedGP +
                    ", fixedEstimatedHours=" + fixedEstimatedHours +
                    ", fixedAnnualBillableHours=" + fixedAnnualBillableHours +
                    '}';
        }

        @SerializedName("fixedPercentage_Id")
        private long fixedPercentage_Id;
        private double fixedPercentage;
        private double fixedGP;
        private long fixedEstimatedHours;
        private long fixedAnnualBillableHours;

        public long getFixedPercentage_Id() {
            return fixedPercentage_Id;
        }

        public void setFixedPercentage_Id(long fixedPercentage_Id) {
            this.fixedPercentage_Id = fixedPercentage_Id;
        }

        public long getFixedAnnualBillableHours() {
            return fixedAnnualBillableHours;
        }

        public void setFixedAnnualBillableHours(long fixedAnnualBillableHours) {
            this.fixedAnnualBillableHours = fixedAnnualBillableHours;
        }

        public long getFixedPercentageID() {
            return fixedPercentage_Id;
        }

        public void setFixedPercentageID(long value) {
            this.fixedPercentage_Id = value;
        }

        public double getFixedPercentage() {
            return fixedPercentage;
        }

        public void setFixedPercentage(double value) {
            this.fixedPercentage = value;
        }

        public double getFixedGP() {
            return fixedGP;
        }

        public void setFixedGP(double value) {
            this.fixedGP = value;
        }

        public long getFixedEstimatedHours() {
            return fixedEstimatedHours;
        }

        public void setFixedEstimatedHours(long value) {
            this.fixedEstimatedHours = value;
        }
    }
}
