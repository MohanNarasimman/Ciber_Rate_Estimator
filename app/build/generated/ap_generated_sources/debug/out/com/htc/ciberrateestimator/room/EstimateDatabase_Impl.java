package com.htc.ciberrateestimator.room;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EstimateDatabase_Impl extends EstimateDatabase {
  private volatile EstimateDao _estimateDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `version` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `versionID` INTEGER NOT NULL, `lastUpdatedDateTime` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `area` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `areaID` INTEGER NOT NULL, `areaName` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `job_title` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `jobTitleID` INTEGER NOT NULL, `jobTitleName` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `job_level` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `roleID` INTEGER NOT NULL, `jobTitleID` INTEGER NOT NULL, `roleName` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `salary` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `areaID` INTEGER NOT NULL, `jobTitleID` INTEGER NOT NULL, `roleID` INTEGER NOT NULL, `minSalary` INTEGER NOT NULL, `maxSalary` INTEGER NOT NULL, `averageSalary` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `fixed_percent` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fixedPercentageID` INTEGER NOT NULL, `fixedPercentage` REAL NOT NULL, `fixedGP` REAL NOT NULL, `fixedEstimatedHours` INTEGER NOT NULL, `fixedAnnualBillableHours` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '210c7f82c64434fdd99e4bdb5070681c')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `version`");
        _db.execSQL("DROP TABLE IF EXISTS `area`");
        _db.execSQL("DROP TABLE IF EXISTS `job_title`");
        _db.execSQL("DROP TABLE IF EXISTS `job_level`");
        _db.execSQL("DROP TABLE IF EXISTS `salary`");
        _db.execSQL("DROP TABLE IF EXISTS `fixed_percent`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsVersion = new HashMap<String, TableInfo.Column>(3);
        _columnsVersion.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVersion.put("versionID", new TableInfo.Column("versionID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVersion.put("lastUpdatedDateTime", new TableInfo.Column("lastUpdatedDateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVersion = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVersion = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVersion = new TableInfo("version", _columnsVersion, _foreignKeysVersion, _indicesVersion);
        final TableInfo _existingVersion = TableInfo.read(_db, "version");
        if (! _infoVersion.equals(_existingVersion)) {
          return new RoomOpenHelper.ValidationResult(false, "version(com.htc.ciberrateestimator.room.model.VersionRoomModel).\n"
                  + " Expected:\n" + _infoVersion + "\n"
                  + " Found:\n" + _existingVersion);
        }
        final HashMap<String, TableInfo.Column> _columnsArea = new HashMap<String, TableInfo.Column>(3);
        _columnsArea.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArea.put("areaID", new TableInfo.Column("areaID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArea.put("areaName", new TableInfo.Column("areaName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysArea = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesArea = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoArea = new TableInfo("area", _columnsArea, _foreignKeysArea, _indicesArea);
        final TableInfo _existingArea = TableInfo.read(_db, "area");
        if (! _infoArea.equals(_existingArea)) {
          return new RoomOpenHelper.ValidationResult(false, "area(com.htc.ciberrateestimator.room.model.AreaRoomModel).\n"
                  + " Expected:\n" + _infoArea + "\n"
                  + " Found:\n" + _existingArea);
        }
        final HashMap<String, TableInfo.Column> _columnsJobTitle = new HashMap<String, TableInfo.Column>(3);
        _columnsJobTitle.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsJobTitle.put("jobTitleID", new TableInfo.Column("jobTitleID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsJobTitle.put("jobTitleName", new TableInfo.Column("jobTitleName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysJobTitle = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesJobTitle = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoJobTitle = new TableInfo("job_title", _columnsJobTitle, _foreignKeysJobTitle, _indicesJobTitle);
        final TableInfo _existingJobTitle = TableInfo.read(_db, "job_title");
        if (! _infoJobTitle.equals(_existingJobTitle)) {
          return new RoomOpenHelper.ValidationResult(false, "job_title(com.htc.ciberrateestimator.room.model.JobTitleRoomModel).\n"
                  + " Expected:\n" + _infoJobTitle + "\n"
                  + " Found:\n" + _existingJobTitle);
        }
        final HashMap<String, TableInfo.Column> _columnsJobLevel = new HashMap<String, TableInfo.Column>(4);
        _columnsJobLevel.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsJobLevel.put("roleID", new TableInfo.Column("roleID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsJobLevel.put("jobTitleID", new TableInfo.Column("jobTitleID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsJobLevel.put("roleName", new TableInfo.Column("roleName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysJobLevel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesJobLevel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoJobLevel = new TableInfo("job_level", _columnsJobLevel, _foreignKeysJobLevel, _indicesJobLevel);
        final TableInfo _existingJobLevel = TableInfo.read(_db, "job_level");
        if (! _infoJobLevel.equals(_existingJobLevel)) {
          return new RoomOpenHelper.ValidationResult(false, "job_level(com.htc.ciberrateestimator.room.model.JobLevelRoomModel).\n"
                  + " Expected:\n" + _infoJobLevel + "\n"
                  + " Found:\n" + _existingJobLevel);
        }
        final HashMap<String, TableInfo.Column> _columnsSalary = new HashMap<String, TableInfo.Column>(7);
        _columnsSalary.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalary.put("areaID", new TableInfo.Column("areaID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalary.put("jobTitleID", new TableInfo.Column("jobTitleID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalary.put("roleID", new TableInfo.Column("roleID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalary.put("minSalary", new TableInfo.Column("minSalary", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalary.put("maxSalary", new TableInfo.Column("maxSalary", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalary.put("averageSalary", new TableInfo.Column("averageSalary", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSalary = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSalary = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSalary = new TableInfo("salary", _columnsSalary, _foreignKeysSalary, _indicesSalary);
        final TableInfo _existingSalary = TableInfo.read(_db, "salary");
        if (! _infoSalary.equals(_existingSalary)) {
          return new RoomOpenHelper.ValidationResult(false, "salary(com.htc.ciberrateestimator.room.model.SalaryRoomModel).\n"
                  + " Expected:\n" + _infoSalary + "\n"
                  + " Found:\n" + _existingSalary);
        }
        final HashMap<String, TableInfo.Column> _columnsFixedPercent = new HashMap<String, TableInfo.Column>(6);
        _columnsFixedPercent.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFixedPercent.put("fixedPercentageID", new TableInfo.Column("fixedPercentageID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFixedPercent.put("fixedPercentage", new TableInfo.Column("fixedPercentage", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFixedPercent.put("fixedGP", new TableInfo.Column("fixedGP", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFixedPercent.put("fixedEstimatedHours", new TableInfo.Column("fixedEstimatedHours", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFixedPercent.put("fixedAnnualBillableHours", new TableInfo.Column("fixedAnnualBillableHours", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFixedPercent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFixedPercent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFixedPercent = new TableInfo("fixed_percent", _columnsFixedPercent, _foreignKeysFixedPercent, _indicesFixedPercent);
        final TableInfo _existingFixedPercent = TableInfo.read(_db, "fixed_percent");
        if (! _infoFixedPercent.equals(_existingFixedPercent)) {
          return new RoomOpenHelper.ValidationResult(false, "fixed_percent(com.htc.ciberrateestimator.room.model.FixedPercentRoomModel).\n"
                  + " Expected:\n" + _infoFixedPercent + "\n"
                  + " Found:\n" + _existingFixedPercent);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "210c7f82c64434fdd99e4bdb5070681c", "65a286ca5fa4642af94251afb890e840");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "version","area","job_title","job_level","salary","fixed_percent");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `version`");
      _db.execSQL("DELETE FROM `area`");
      _db.execSQL("DELETE FROM `job_title`");
      _db.execSQL("DELETE FROM `job_level`");
      _db.execSQL("DELETE FROM `salary`");
      _db.execSQL("DELETE FROM `fixed_percent`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public EstimateDao estimateDao() {
    if (_estimateDao != null) {
      return _estimateDao;
    } else {
      synchronized(this) {
        if(_estimateDao == null) {
          _estimateDao = new EstimateDao_Impl(this);
        }
        return _estimateDao;
      }
    }
  }
}
