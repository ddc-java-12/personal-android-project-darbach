package edu.cnm.deepdive.dicecrunch.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.dicecrunch.model.entity.History;
import edu.cnm.deepdive.dicecrunch.service.HistoryRepository;
import java.util.List;

public class HistoryViewModel extends AndroidViewModel {

  private final HistoryRepository historyRepository;

  public HistoryViewModel(@NonNull Application application) {
    super(application);
    historyRepository = new HistoryRepository(application);
  }



}
