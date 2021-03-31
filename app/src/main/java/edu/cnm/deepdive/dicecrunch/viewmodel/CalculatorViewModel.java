package edu.cnm.deepdive.dicecrunch.viewmodel;

import android.app.Application;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.dicecrunch.model.entity.Face;
import edu.cnm.deepdive.dicecrunch.model.pojo.DieWithFaces;
import edu.cnm.deepdive.dicecrunch.service.DieRepository;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class CalculatorViewModel extends AndroidViewModel implements LifecycleObserver {


  public CalculatorViewModel(
      @NonNull @NotNull Application application) {
    super(application);
  }
}
