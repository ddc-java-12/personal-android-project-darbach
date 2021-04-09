package edu.cnm.deepdive.dicecrunch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.dicecrunch.model.entity.Face;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * Data access object for the ORM to manage the Face entity.
 */
@Dao
public interface FaceDao {

  @Insert
  Single<Long> insert(Face face);

  @Insert
  Single<List<Long>> insert(Face... faces);

  @Insert
  Single<List<Long>> insert(Collection<Face> faces);

  @Update
  Single<Integer> update(Face face);

  @Update
  Single<Integer> update(Face... faces);

  @Update
  Single<Integer> update(Collection<Face> faces);

  @Delete
  Single<Integer> delete(Face face);

  @Delete
  Single<Integer> delete(Face... face);

  @Delete
  Single<Integer> delete(Collection<Face> face);

  @Query("SELECT * FROM Face ORDER BY name ASC")
  LiveData<List<Face>> selectAll();

}
