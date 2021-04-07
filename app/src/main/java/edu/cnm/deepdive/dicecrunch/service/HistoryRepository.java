package edu.cnm.deepdive.dicecrunch.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.dicecrunch.model.dao.HistoryDao;
import edu.cnm.deepdive.dicecrunch.model.entity.History;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class HistoryRepository {

  private final Context context;
  private final HistoryDao historyDao;

  public HistoryRepository(Context context) {
    this.context = context;
    historyDao = DiceCrunchDatabase.getInstance().getHistoryDao();
  }

  public LiveData<List<History>> getAll() {
    return historyDao.selectAll();
  }

  public LiveData<History> get(long HistoryId) {
    return historyDao.selectById(HistoryId);
  }

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
