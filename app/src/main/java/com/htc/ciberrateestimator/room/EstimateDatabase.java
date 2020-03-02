package com.htc.ciberrateestimator.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.htc.ciberrateestimator.room.model.AreaRoomModel;
import com.htc.ciberrateestimator.room.model.FixedPercentRoomModel;
import com.htc.ciberrateestimator.room.model.JobLevelRoomModel;
import com.htc.ciberrateestimator.room.model.JobTitleRoomModel;
import com.htc.ciberrateestimator.room.model.SalaryRoomModel;
import com.htc.ciberrateestimator.room.model.VersionRoomModel;

@Database(entities = {VersionRoomModel.class, AreaRoomModel.class, JobTitleRoomModel.class, JobLevelRoomModel.class, SalaryRoomModel.class, FixedPercentRoomModel.class}, version = 2, exportSchema = false)
public abstract class EstimateDatabase extends RoomDatabase {

    public static EstimateDatabase INSTANCE;

    public abstract EstimateDao estimateDao();

    public static EstimateDatabase getInstance(Context context) {
        synchronized (EstimateDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EstimateDatabase.class, "estimatordatabase").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            }
        }
        return INSTANCE;
    }
}
