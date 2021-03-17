package edu.cnm.deepdive.dicecrunch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.dicecrunch.model.entity.Die;
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

}
