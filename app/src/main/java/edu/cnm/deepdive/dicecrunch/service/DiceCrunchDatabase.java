package edu.cnm.deepdive.dicecrunch.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.dicecrunch.model.dao.DieDao;
import edu.cnm.deepdive.dicecrunch.model.dao.FaceDao;
import edu.cnm.deepdive.dicecrunch.model.dao.FormulaDao;
import edu.cnm.deepdive.dicecrunch.model.dao.HistoryDao;
import edu.cnm.deepdive.dicecrunch.model.entity.Die;
import edu.cnm.deepdive.dicecrunch.model.entity.Face;
import edu.cnm.deepdive.dicecrunch.model.entity.Formula;
import edu.cnm.deepdive.dicecrunch.model.entity.History;
import edu.cnm.deepdive.dicecrunch.service.DiceCrunchDatabase.Converters;
import java.util.Date;

/**
 * The ORM class to manage persistence. Only available as a singleton.
 */
@Database(
    entities = {Die.class, Face.class, Formula.class, History.class},
    version = 1
)
@TypeConverters(value = {Converters.class})
public abstract class DiceCrunchDatabase extends RoomDatabase {

  private static final String DB_NAME = "dicecrunch_db";

  private static Application context;

  /**
   * Provide the ORM with an application context.
   *
   * @param context
   */
  public static void setContext(Application context) {
    DiceCrunchDatabase.context = context;
  }

  /**
   * Get the singleton instance of the ORM.
   *
   * @return
   */
  public static DiceCrunchDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract DieDao getDieDao();

  public abstract FaceDao getFaceDao();

  public abstract FormulaDao getFormulaDao();

  public abstract HistoryDao getHistoryDao();

  private static class InstanceHolder {

    private static final DiceCrunchDatabase INSTANCE =
        Room.databaseBuilder(context, DiceCrunchDatabase.class, DB_NAME)
            .build();
  }

  public static class Converters {

    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }

  }
}
