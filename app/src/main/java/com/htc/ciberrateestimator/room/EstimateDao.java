package com.htc.ciberrateestimator.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.htc.ciberrateestimator.room.model.AreaRoomModel;
import com.htc.ciberrateestimator.room.model.FixedPercentRoomModel;
import com.htc.ciberrateestimator.room.model.JobLevelRoomModel;
import com.htc.ciberrateestimator.room.model.JobTitleRoomModel;
import com.htc.ciberrateestimator.room.model.SalaryRoomModel;
import com.htc.ciberrateestimator.room.model.VersionRoomModel;

import java.util.List;

@Dao
public interface EstimateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllVersion(List<VersionRoomModel> versionRoomModels);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllArea(List<AreaRoomModel> areaRoomModels);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllJobTitle(List<JobTitleRoomModel> jobTitleRoomModels);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllJobLevel(List<JobLevelRoomModel> jobLevelRoomModels);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllSalary(List<SalaryRoomModel> salaryRoomModels);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllFixedPercent(List<FixedPercentRoomModel> fixedPercentRoomModels);


    @Query("SELECT * FROM version")
    List<VersionRoomModel> getAllVersion();

    @Query("SELECT * FROM area")
    List<AreaRoomModel> getAllArea();

    @Query("SELECT * FROM job_title")
    List<JobTitleRoomModel> getAllJobTitle();

    @Query("SELECT * FROM job_level WHERE jobTitleID = :jobTitleID")
    List<JobLevelRoomModel> getAllJobLevel(long jobTitleID);

    @Query("SELECT * FROM salary WHERE areaID = :areaID AND jobTitleID = :jobTitleID AND roleID =:roleID")
    List<SalaryRoomModel> getAllSalary(long areaID, long jobTitleID, long roleID);

    @Query("SELECT * FROM fixed_percent")
    List<FixedPercentRoomModel> getAllFixedPercent();

    @Query("DELETE FROM version ")
    void deleteVersion();

    @Query("DELETE FROM area")
    void deleteArea( );

    @Query("DELETE FROM job_title")
    void deleteJobTitle( );

    @Query("DELETE FROM job_level")
    void deleteJobLevel( );

    @Query("DELETE FROM salary")
    void deleteSalary();

    @Query("DELETE FROM fixed_percent")
    void deleteFixedPercent();
}
