package edu.cnm.deepdive.dicecrunch.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.dicecrunch.service.DieRepository;
import edu.cnm.deepdive.dicecrunch.service.HistoryRepository;

public class CalculatorViewModel extends AndroidViewModel implements LifecycleObserver {

  private final MutableLiveData<String> formula;
  private final MutableLiveData<String> result;
  private final MutableLiveData<String> trace;
  private final DieRepository dieRepository;
  private final HistoryRepository historyRepository;

  public CalculatorViewModel(@NonNull Application application) {
    super(application);
    dieRepository = new DieRepository(application);
    historyRepository = new HistoryRepository(application);
    formula = new MutableLiveData<>("");
    result = new MutableLiveData<>();
    trace = new MutableLiveData<>();
  }

  public LiveData<String> getFormula() {
    return formula;
  }

  public LiveData<String> getResult() {
    return result;
  }

  public LiveData<String> getTrace() {
    return trace;
  }

  // TODO Use these methods to manage the view in case of screen rotate.

  private void clearFormula() {
    this.formula.setValue("");
  }

  private void appendToFormula(String fragment) {
    this.formula.setValue(this.formula.getValue() + fragment);
  }

  private void backspace() {
    String formula = this.formula.getValue();
    if (!formula.isEmpty()) {
      this.formula.setValue(formula.substring(0, formula.length() - 1));
    }
  }

}
