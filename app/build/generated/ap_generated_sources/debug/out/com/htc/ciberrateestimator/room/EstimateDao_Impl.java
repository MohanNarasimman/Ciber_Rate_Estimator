package com.htc.ciberrateestimator.room;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.htc.ciberrateestimator.room.model.AreaRoomModel;
import com.htc.ciberrateestimator.room.model.FixedPercentRoomModel;
import com.htc.ciberrateestimator.room.model.JobLevelRoomModel;
import com.htc.ciberrateestimator.room.model.JobTitleRoomModel;
import com.htc.ciberrateestimator.room.model.SalaryRoomModel;
import com.htc.ciberrateestimator.room.model.VersionRoomModel;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EstimateDao_Impl implements EstimateDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VersionRoomModel> __insertionAdapterOfVersionRoomModel;

  private final EntityInsertionAdapter<AreaRoomModel> __insertionAdapterOfAreaRoomModel;

  private final EntityInsertionAdapter<JobTitleRoomModel> __insertionAdapterOfJobTitleRoomModel;

  private final EntityInsertionAdapter<JobLevelRoomModel> __insertionAdapterOfJobLevelRoomModel;

  private final EntityInsertionAdapter<SalaryRoomModel> __insertionAdapterOfSalaryRoomModel;

  private final EntityInsertionAdapter<FixedPercentRoomModel> __insertionAdapterOfFixedPercentRoomModel;

  private final SharedSQLiteStatement __preparedStmtOfDeleteVersion;

  private final SharedSQLiteStatement __preparedStmtOfDeleteArea;

  private final SharedSQLiteStatement __preparedStmtOfDeleteJobTitle;

  private final SharedSQLiteStatement __preparedStmtOfDeleteJobLevel;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSalary;

  private final SharedSQLiteStatement __preparedStmtOfDeleteFixedPercent;

  public EstimateDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVersionRoomModel = new EntityInsertionAdapter<VersionRoomModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `version` (`id`,`versionID`,`lastUpdatedDateTime`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, VersionRoomModel value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getVersionID());
        if (value.getLastUpdatedDateTime() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLastUpdatedDateTime());
        }
      }
    };
    this.__insertionAdapterOfAreaRoomModel = new EntityInsertionAdapter<AreaRoomModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `area` (`id`,`areaID`,`areaName`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AreaRoomModel value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getAreaID());
        if (value.getAreaName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAreaName());
        }
      }
    };
    this.__insertionAdapterOfJobTitleRoomModel = new EntityInsertionAdapter<JobTitleRoomModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `job_title` (`id`,`jobTitleID`,`jobTitleName`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, JobTitleRoomModel value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getJobTitleID());
        if (value.getJobTitleName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getJobTitleName());
        }
      }
    };
    this.__insertionAdapterOfJobLevelRoomModel = new EntityInsertionAdapter<JobLevelRoomModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `job_level` (`id`,`roleID`,`jobTitleID`,`roleName`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, JobLevelRoomModel value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getRoleID());
        stmt.bindLong(3, value.getJobTitleID());
        if (value.getRoleName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRoleName());
        }
      }
    };
    this.__insertionAdapterOfSalaryRoomModel = new EntityInsertionAdapter<SalaryRoomModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `salary` (`id`,`areaID`,`jobTitleID`,`roleID`,`minSalary`,`maxSalary`,`averageSalary`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SalaryRoomModel value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getAreaID());
        stmt.bindLong(3, value.getJobTitleID());
        stmt.bindLong(4, value.getRoleID());
        stmt.bindLong(5, value.getMinSalary());
        stmt.bindLong(6, value.getMaxSalary());
        stmt.bindLong(7, value.getAverageSalary());
      }
    };
    this.__insertionAdapterOfFixedPercentRoomModel = new EntityInsertionAdapter<FixedPercentRoomModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `fixed_percent` (`id`,`fixedPercentageID`,`fixedPercentage`,`fixedGP`,`fixedEstimatedHours`,`fixedAnnualBillableHours`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FixedPercentRoomModel value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getFixedPercentageID());
        stmt.bindDouble(3, value.getFixedPercentage());
        stmt.bindDouble(4, value.getFixedGP());
        stmt.bindLong(5, value.getFixedEstimatedHours());
        stmt.bindLong(6, value.getFixedAnnualBillableHours());
      }
    };
    this.__preparedStmtOfDeleteVersion = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM version ";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteArea = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM area";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteJobTitle = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM job_title";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteJobLevel = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM job_level";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteSalary = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM salary";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteFixedPercent = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM fixed_percent";
        return _query;
      }
    };
  }

  @Override
  public void insertAllVersion(final List<VersionRoomModel> versionRoomModels) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfVersionRoomModel.insert(versionRoomModels);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllArea(final List<AreaRoomModel> areaRoomModels) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAreaRoomModel.insert(areaRoomModels);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllJobTitle(final List<JobTitleRoomModel> jobTitleRoomModels) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfJobTitleRoomModel.insert(jobTitleRoomModels);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllJobLevel(final List<JobLevelRoomModel> jobLevelRoomModels) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfJobLevelRoomModel.insert(jobLevelRoomModels);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllSalary(final List<SalaryRoomModel> salaryRoomModels) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSalaryRoomModel.insert(salaryRoomModels);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllFixedPercent(final List<FixedPercentRoomModel> fixedPercentRoomModels) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFixedPercentRoomModel.insert(fixedPercentRoomModels);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteVersion() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteVersion.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteVersion.release(_stmt);
    }
  }

  @Override
  public void deleteArea() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteArea.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteArea.release(_stmt);
    }
  }

  @Override
  public void deleteJobTitle() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteJobTitle.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteJobTitle.release(_stmt);
    }
  }

  @Override
  public void deleteJobLevel() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteJobLevel.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteJobLevel.release(_stmt);
    }
  }

  @Override
  public void deleteSalary() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSalary.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteSalary.release(_stmt);
    }
  }

  @Override
  public void deleteFixedPercent() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFixedPercent.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteFixedPercent.release(_stmt);
    }
  }

  @Override
  public List<VersionRoomModel> getAllVersion() {
    final String _sql = "SELECT * FROM version";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfVersionID = CursorUtil.getColumnIndexOrThrow(_cursor, "versionID");
      final int _cursorIndexOfLastUpdatedDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdatedDateTime");
      final List<VersionRoomModel> _result = new ArrayList<VersionRoomModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final VersionRoomModel _item;
        final long _tmpVersionID;
        _tmpVersionID = _cursor.getLong(_cursorIndexOfVersionID);
        final String _tmpLastUpdatedDateTime;
        _tmpLastUpdatedDateTime = _cursor.getString(_cursorIndexOfLastUpdatedDateTime);
        _item = new VersionRoomModel(_tmpVersionID,_tmpLastUpdatedDateTime);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<AreaRoomModel> getAllArea() {
    final String _sql = "SELECT * FROM area";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfAreaID = CursorUtil.getColumnIndexOrThrow(_cursor, "areaID");
      final int _cursorIndexOfAreaName = CursorUtil.getColumnIndexOrThrow(_cursor, "areaName");
      final List<AreaRoomModel> _result = new ArrayList<AreaRoomModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final AreaRoomModel _item;
        final long _tmpAreaID;
        _tmpAreaID = _cursor.getLong(_cursorIndexOfAreaID);
        final String _tmpAreaName;
        _tmpAreaName = _cursor.getString(_cursorIndexOfAreaName);
        _item = new AreaRoomModel(_tmpAreaID,_tmpAreaName);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<JobTitleRoomModel> getAllJobTitle() {
    final String _sql = "SELECT * FROM job_title";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfJobTitleID = CursorUtil.getColumnIndexOrThrow(_cursor, "jobTitleID");
      final int _cursorIndexOfJobTitleName = CursorUtil.getColumnIndexOrThrow(_cursor, "jobTitleName");
      final List<JobTitleRoomModel> _result = new ArrayList<JobTitleRoomModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final JobTitleRoomModel _item;
        final long _tmpJobTitleID;
        _tmpJobTitleID = _cursor.getLong(_cursorIndexOfJobTitleID);
        final String _tmpJobTitleName;
        _tmpJobTitleName = _cursor.getString(_cursorIndexOfJobTitleName);
        _item = new JobTitleRoomModel(_tmpJobTitleID,_tmpJobTitleName);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<JobLevelRoomModel> getAllJobLevel(final long jobTitleID) {
    final String _sql = "SELECT * FROM job_level WHERE jobTitleID = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, jobTitleID);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfRoleID = CursorUtil.getColumnIndexOrThrow(_cursor, "roleID");
      final int _cursorIndexOfJobTitleID = CursorUtil.getColumnIndexOrThrow(_cursor, "jobTitleID");
      final int _cursorIndexOfRoleName = CursorUtil.getColumnIndexOrThrow(_cursor, "roleName");
      final List<JobLevelRoomModel> _result = new ArrayList<JobLevelRoomModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final JobLevelRoomModel _item;
        final long _tmpRoleID;
        _tmpRoleID = _cursor.getLong(_cursorIndexOfRoleID);
        final long _tmpJobTitleID;
        _tmpJobTitleID = _cursor.getLong(_cursorIndexOfJobTitleID);
        final String _tmpRoleName;
        _tmpRoleName = _cursor.getString(_cursorIndexOfRoleName);
        _item = new JobLevelRoomModel(_tmpRoleID,_tmpJobTitleID,_tmpRoleName);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<SalaryRoomModel> getAllSalary(final long areaID, final long jobTitleID,
      final long roleID) {
    final String _sql = "SELECT * FROM salary WHERE areaID = ? AND jobTitleID = ? AND roleID =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, areaID);
    _argIndex = 2;
    _statement.bindLong(_argIndex, jobTitleID);
    _argIndex = 3;
    _statement.bindLong(_argIndex, roleID);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfAreaID = CursorUtil.getColumnIndexOrThrow(_cursor, "areaID");
      final int _cursorIndexOfJobTitleID = CursorUtil.getColumnIndexOrThrow(_cursor, "jobTitleID");
      final int _cursorIndexOfRoleID = CursorUtil.getColumnIndexOrThrow(_cursor, "roleID");
      final int _cursorIndexOfMinSalary = CursorUtil.getColumnIndexOrThrow(_cursor, "minSalary");
      final int _cursorIndexOfMaxSalary = CursorUtil.getColumnIndexOrThrow(_cursor, "maxSalary");
      final int _cursorIndexOfAverageSalary = CursorUtil.getColumnIndexOrThrow(_cursor, "averageSalary");
      final List<SalaryRoomModel> _result = new ArrayList<SalaryRoomModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final SalaryRoomModel _item;
        final long _tmpAreaID;
        _tmpAreaID = _cursor.getLong(_cursorIndexOfAreaID);
        final long _tmpJobTitleID;
        _tmpJobTitleID = _cursor.getLong(_cursorIndexOfJobTitleID);
        final long _tmpRoleID;
        _tmpRoleID = _cursor.getLong(_cursorIndexOfRoleID);
        final long _tmpMinSalary;
        _tmpMinSalary = _cursor.getLong(_cursorIndexOfMinSalary);
        final long _tmpMaxSalary;
        _tmpMaxSalary = _cursor.getLong(_cursorIndexOfMaxSalary);
        final long _tmpAverageSalary;
        _tmpAverageSalary = _cursor.getLong(_cursorIndexOfAverageSalary);
        _item = new SalaryRoomModel(_tmpAreaID,_tmpJobTitleID,_tmpRoleID,_tmpMinSalary,_tmpMaxSalary,_tmpAverageSalary);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<FixedPercentRoomModel> getAllFixedPercent() {
    final String _sql = "SELECT * FROM fixed_percent";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfFixedPercentageID = CursorUtil.getColumnIndexOrThrow(_cursor, "fixedPercentageID");
      final int _cursorIndexOfFixedPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "fixedPercentage");
      final int _cursorIndexOfFixedGP = CursorUtil.getColumnIndexOrThrow(_cursor, "fixedGP");
      final int _cursorIndexOfFixedEstimatedHours = CursorUtil.getColumnIndexOrThrow(_cursor, "fixedEstimatedHours");
      final int _cursorIndexOfFixedAnnualBillableHours = CursorUtil.getColumnIndexOrThrow(_cursor, "fixedAnnualBillableHours");
      final List<FixedPercentRoomModel> _result = new ArrayList<FixedPercentRoomModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final FixedPercentRoomModel _item;
        final long _tmpFixedPercentageID;
        _tmpFixedPercentageID = _cursor.getLong(_cursorIndexOfFixedPercentageID);
        final double _tmpFixedPercentage;
        _tmpFixedPercentage = _cursor.getDouble(_cursorIndexOfFixedPercentage);
        final double _tmpFixedGP;
        _tmpFixedGP = _cursor.getDouble(_cursorIndexOfFixedGP);
        final long _tmpFixedEstimatedHours;
        _tmpFixedEstimatedHours = _cursor.getLong(_cursorIndexOfFixedEstimatedHours);
        final long _tmpFixedAnnualBillableHours;
        _tmpFixedAnnualBillableHours = _cursor.getLong(_cursorIndexOfFixedAnnualBillableHours);
        _item = new FixedPercentRoomModel(_tmpFixedPercentageID,_tmpFixedPercentage,_tmpFixedGP,_tmpFixedEstimatedHours,_tmpFixedAnnualBillableHours);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
