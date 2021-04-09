package edu.cnm.deepdive.dicecrunch.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.dicecrunch.model.dao.HistoryDao;
import edu.cnm.deepdive.dicecrunch.model.entity.History;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * A repository to manage persistence for the History entity and database table.
 */
public class HistoryRepository {

  private final Context context;
  private final HistoryDao historyDao;

  /**
   * Create a HistoryRepository.
   *
   * @param context The application context.
   */
  public HistoryRepository(Context context) {
    this.context = context;
    historyDao = DiceCrunchDatabase.getInstance().getHistoryDao();
  }

  /**
   * Return the history of all persisted dice formula evaluations.
   *
   * @return
   */
  public LiveData<List<History>> getAll() {
    return historyDao.selectAll();
  }

  /**
   * Return a specified dice formula evaluation.
   *
   * @param HistoryId A unique identifier for a dice formula evaluation.
   * @return
   */
  public LiveData<History> get(long HistoryId) {
    return historyDao.selectById(HistoryId);
  }

  /**
   * Persist a dice formula evaluation to the database.
   *
   * @param history A specific dice formula evaluation.
   * @return A ReactiveX task.
   */
  public Single<History> save(History history) {
    if (history.getId() > 0) {
      // Update
      return historyDao
          .update(history)
          .map((ignored) -> history)
          .subscribeOn(Schedulers.io());
    } else {
      // Insert
      return historyDao
          .insert(history)
          .map((ignored) -> history)
          .subscribeOn(Schedulers.io());
    }
  }

  /**
   * Delete a dice formula evaluation from the database.
   *
   * @param history A specific dice formula evaluation.
   * @return A ReactiveX task.
   */
  public Completable delete(History history) {
    return (
        (history.getId() == 0)
            ? Completable.complete() // No-op
            : historyDao
                .delete(history)
                .ignoreElement()
    )
        .subscribeOn(Schedulers.io()); // Delete
  }

}
