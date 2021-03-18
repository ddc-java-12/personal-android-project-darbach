package edu.cnm.deepdive.dicecrunch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.dicecrunch.model.entity.History;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface HistoryDao {

  @Insert
  Single<Long> insert(History history);

  @Update
  Single<Integer> update(History history);

  @Delete
  Single<Integer> delete(History history);

  @Delete
  Single<Integer> delete(History... history);

  @Delete
  Single<Integer> delete(Collection<History> history);

  @Query("SELECT * FROM History ORDER BY created ASC")
  LiveData<List<History>> selectAll();

  @Transaction
  @Query("SELECT * FROM History WHERE history_id = :historyId")
  LiveData<History> selectById(long historyId);
}
