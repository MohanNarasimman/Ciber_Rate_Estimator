package com.htc.ciberrateestimator.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fixed_percent")
public class FixedPercentRoomModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FixedPercentRoomModel{" +
                "id=" + id +
                ", fixedPercentageID=" + fixedPercentageID +
                ", fixedPercentage=" + fixedPercentage +
                ", fixedGP=" + fixedGP +
                ", fixedEstimatedHours=" + fixedEstimatedHours +
                ", fixedAnnualBillableHours=" + fixedAnnualBillableHours +
                '}';
    }

    public FixedPercentRoomModel(long fixedPercentageID, double fixedPercentage, double fixedGP, long fixedEstimatedHours, long fixedAnnualBillableHours) {
        this.fixedPercentageID = fixedPercentageID;
        this.fixedPercentage = fixedPercentage;
        this.fixedGP = fixedGP;
        this.fixedEstimatedHours = fixedEstimatedHours;
        this.fixedAnnualBillableHours = fixedAnnualBillableHours;
    }

    @ColumnInfo(name = "fixedPercentageID" )
    private long fixedPercentageID;

    @ColumnInfo(name = "fixedPercentage")
    private double fixedPercentage;

    @ColumnInfo(name = "fixedGP")
    private double fixedGP;

    @ColumnInfo(name = "fixedEstimatedHours" )
    private long fixedEstimatedHours;

    @ColumnInfo(name = "fixedAnnualBillableHours" )
    private long fixedAnnualBillableHours;

    public long getFixedAnnualBillableHours() {
        return fixedAnnualBillableHours;
    }

    public void setFixedAnnualBillableHours(long fixedAnnualBillableHours) {
        this.fixedAnnualBillableHours = fixedAnnualBillableHours;
    }

    public long getFixedPercentageID() {
        return fixedPercentageID;
    }

    public void setFixedPercentageID(long value) {
        this.fixedPercentageID = value;
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
