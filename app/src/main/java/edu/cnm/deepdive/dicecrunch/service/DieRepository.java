package edu.cnm.deepdive.dicecrunch.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.dicecrunch.model.dao.DieDao;
import edu.cnm.deepdive.dicecrunch.model.dao.FaceDao;
import edu.cnm.deepdive.dicecrunch.model.dao.FormulaDao;
import edu.cnm.deepdive.dicecrunch.model.entity.Die;
import edu.cnm.deepdive.dicecrunch.model.entity.Face;
import edu.cnm.deepdive.dicecrunch.model.entity.Formula;
import edu.cnm.deepdive.dicecrunch.model.pojo.DieWithFaces;
import edu.cnm.deepdive.dicecrunch.model.pojo.DieWithFormulas;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Iterator;
import java.util.List;

/**
 * A repository to manage persistence for the Die entity and database table.
 */
public class DieRepository {

  private final Context context;
  private final DieDao dieDao;
  private final FaceDao faceDao;
  private final FormulaDao formulaDao;

  /**
   * Create a DieRepository.
   *
   * @param context The application context.
   */
  public DieRepository(Context context) {
    this.context = context;
    DiceCrunchDatabase database = DiceCrunchDatabase.getInstance();
    dieDao = database.getDieDao();
    faceDao = database.getFaceDao();
    formulaDao = database.getFormulaDao();
  }

  /**
   * Return all the saved custom dice.
   *
   * @return
   */
  public LiveData<List<Die>> getAll() {
    return dieDao.selectAll();
  }

  /**
   * Return all the saved custom dice joined with the table of their faces.
   *
   * @param dieId The unique identifier for a specific custom die.
   * @return
   */
  public LiveData<DieWithFaces> get(long dieId) {
    return dieDao.selectById(dieId);
  }

  /**
   * Persist a custom die and it's faces to the databse.
   *
   * @param dieWithFaces A POJO of the new die to be saved.
   * @return A ReactiveX task.
   */
  public Single<DieWithFaces> save(DieWithFaces dieWithFaces) {
    if (dieWithFaces.getId() > 0) {
      // Update
      return dieDao
          .update(dieWithFaces)
          .map((ignored) -> dieWithFaces)
          .subscribeOn(Schedulers.io());
    } else {
      // Insert
      return dieDao
          .insert(dieWithFaces)
          .flatMap((dieId) -> {
            for (Face face : dieWithFaces.getFaces()) {
              face.setDieId(dieId);
            }
            return faceDao.insert(dieWithFaces.getFaces());
          })
          .map((faceIds) -> {
            Iterator<Long> idIterator = faceIds.iterator();
            Iterator<Face> faceIterator = dieWithFaces.getFaces().iterator();
            while (idIterator.hasNext() && faceIterator.hasNext()) {
              faceIterator.next().setId(idIterator.next());
            }
            return dieWithFaces;
          })
          .subscribeOn(Schedulers.io());
    }
  }

  /**
   * Persist a custom die and the formulae it is used in to the databse.
   *
   * @param die A POJO of the die to be saved.
   * @return A ReactiveX task.
   */
  public Single<DieWithFormulas> save(DieWithFormulas die) {
    if (die.getId() > 0) {
      // Update
      return dieDao
          .update(die)
          .map((ignored) -> die)
          .subscribeOn(Schedulers.io());
    } else {
      // Insert
      return dieDao
          .insert(die)
          .flatMap((dieId) -> {
            for (Formula formula : die.getFormulas()) {
              formula.setDieId(dieId);
            }
            return formulaDao.insert(die.getFormulas());
          })
          .map((formulaIds) -> {
            Iterator<Long> idIterator = formulaIds.iterator();
            Iterator<Formula> formulaIterator = die.getFormulas().iterator();
            while (idIterator.hasNext() && formulaIterator.hasNext()) {
              formulaIterator.next().setId(idIterator.next());
            }
            return die;
          })
          .subscribeOn(Schedulers.io());
    }
  }

  /**
   * Remove a custom Die from the database.
   *
   * @param die The Die object being deleted.
   * @return A ReactiveX task.
   */
  public Completable delete(Die die) {
    return (
        (die.getId() == 0)
            ? Completable.complete() // No-op
            : dieDao
                .delete(die)
                .ignoreElement()
    )
        .subscribeOn(Schedulers.io()); // Delete
  }

}
