package edu.cnm.deepdive.dicecrunch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.dicecrunch.model.entity.Formula;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface FormulaDao {

  @Insert
  Single<Long> insert(Formula formula);

  @Insert
  Single<List<Long>> insert(Formula... formulas);

  @Insert
  Single<List<Long>> insert(Collection<Formula> formulas);

  @Update
  Single<Integer> update(Formula formula);

  @Delete
  Single<Integer> delete(Formula formula);

  @Delete
  Single<Integer> delete(Formula... formula);

  @Delete
  Single<Integer> delete(Collection<Formula> formula);

  @Query("SELECT * FROM Formula ORDER BY name ASC")
  LiveData<List<Formula>> selectAll();

}
