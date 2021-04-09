package edu.cnm.deepdive.dicecrunch.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.dicecrunch.model.entity.History;
import edu.cnm.deepdive.dicecrunch.service.DieRepository;
import edu.cnm.deepdive.dicecrunch.service.HistoryRepository;
import edu.cnm.deepdive.dicecrunch.service.Parser;
import java.util.List;

/**
 * Manages the state of the app for the user interface. This class allows the formulas and results
 * to be populated and shared across the different fragments.
 */
public class CalculatorViewModel extends AndroidViewModel implements LifecycleObserver {

  private static final String ROLL_RESULT_FORMAT = "%s = %s";

  private final MutableLiveData<String> formula;
  private final MutableLiveData<String> result;
  private final MutableLiveData<List<String>> trace;
  private final DieRepository dieRepository;
  private final HistoryRepository historyRepository;


  /**
   * Creates an instance of the ViewModel. Information it keeps track of includes the formula and
   * results.
   *
   * @param application
   */
  public CalculatorViewModel(@NonNull Application application) {
    super(application);
    dieRepository = new DieRepository(application);
    historyRepository = new HistoryRepository(application);
    formula = new MutableLiveData<>("");
    result = new MutableLiveData<>();
    trace = new MutableLiveData<>();
  }

  /**
   * Returns the literal version of the formula currently being displayed.
   *
   * @return
   */
  public LiveData<String> getFormula() {
    return formula;
  }

  /**
   * Returns the rolled result of the formula.
   *
   * @return
   */
  public LiveData<String> getResult() {
    return result;
  }

  /**
   * Returns the evaulation at step of parsing the equation.
   *
   * @return
   */
  public LiveData<List<String>> getTrace() {
    return trace;
  }

  /**
   * Return the history of all dice rolls and their results.
   *
   * @return
   */
  public LiveData<List<History>> getHistory() {
    return historyRepository.getAll();
  }

  /**
   * Erases everything from the formula entry field.
   */
  public void clearFormula() {
    this.formula.setValue("");
    this.result.setValue("");
  }

  /**
   * Adds text to the end of the formula entry field.
   *
   * @param fragment The new text to be appended to the existing formula entry field.
   */
  public void appendToFormula(String fragment) {
    this.formula.setValue(this.formula.getValue() + fragment);
  }

  /**
   * Deletes a single character from the end of the formula entry field.
   */
  public void backspace() {
    String formula = this.formula.getValue();
    if (!formula.isEmpty()) {
      this.formula.setValue(formula.substring(0, formula.length() - 1));
    }
  }

  /**
   * Parses and evaluates the dice equation from the formula entry field. The results are stored
   * within this ViewModel's state and the database.
   */
  public void evaluate() {
    Parser parser = new Parser(this.formula.getValue());
    this.result
        .setValue(String.format(ROLL_RESULT_FORMAT, parser.getExpression(), parser.getValue()));
    this.trace.setValue(parser.getTrace());
    History history = new History();
    history.setTrace(String.join("\n", parser.getTrace()));
    history.setResult(this.result.getValue());
    history.setFormula(this.formula.getValue());
    historyRepository
        .save(history)
        .subscribe();
  }

}
