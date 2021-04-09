package edu.cnm.deepdive.dicecrunch.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import edu.cnm.deepdive.dicecrunch.R;
import edu.cnm.deepdive.dicecrunch.model.entity.History;
import edu.cnm.deepdive.dicecrunch.service.DieRepository;
import edu.cnm.deepdive.dicecrunch.service.HistoryRepository;
import edu.cnm.deepdive.dicecrunch.service.Parser;
import java.util.List;

public class CalculatorViewModel extends AndroidViewModel implements LifecycleObserver {

  private static final String ROLL_RESULT_FORMAT = "%s = %s";

  private final MutableLiveData<String> formula;
  private final MutableLiveData<String> result;
  private final MutableLiveData<List<String>> trace;
  private final DieRepository dieRepository;
  private final HistoryRepository historyRepository;
  // TODO: Put parser in here so dice tray and calculator can both use it.

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

  public LiveData<List<String>> getTrace() {
    return trace;
  }

  // TODO Use these methods to manage the view in case of screen rotate.

  public void clearFormula() {
    this.formula.setValue("");
    this.result.setValue("");
  }

  public void appendToFormula(String fragment) {
    this.formula.setValue(this.formula.getValue() + fragment);
  }

  public void backspace() {
    String formula = this.formula.getValue();
    if (!formula.isEmpty()) {
      this.formula.setValue(formula.substring(0, formula.length() - 1));
    }
  }

  public void evaluate() {
    Parser parser = new Parser(this.formula.getValue());
    this.result.setValue(String.format(ROLL_RESULT_FORMAT, parser.getExpression(), parser.getValue()));
    this.trace.setValue(parser.getTrace());
    History history = new History();
    history.setTrace(String.join("\n", parser.getTrace()));
    history.setResult(this.result.getValue());
    history.setFormula(this.formula.getValue());
    historyRepository
        .save(history)
        .subscribe();
  }

  public LiveData<List<History>> getHistory() {
    return historyRepository.getAll();
  };

}
