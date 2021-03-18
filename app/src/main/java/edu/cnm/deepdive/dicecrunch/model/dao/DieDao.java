package edu.cnm.deepdive.dicecrunch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.dicecrunch.model.entity.Die;
import edu.cnm.deepdive.dicecrunch.model.pojo.DieWithFaces;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface DieDao {

  @Insert
  Single<Long> insert(Die die);

  @Update
  Single<Integer> update(Die die);

  @Delete
  Single<Integer> delete(Die die);

  @Delete
  Single<Integer> delete(Die... die);

  @Delete
  Single<Integer> delete(Collection<Die> die);

  @Query("SELECT * FROM Die ORDER BY name ASC")
  LiveData<List<Die>> selectAll();

  @Transaction
  @Query("SELECT * FROM Die WHERE die_id = :dieId")
  LiveData<DieWithFaces> selectById(long dieId);

  // TODO selectById for DieWithFormula. Different method signature?
}
