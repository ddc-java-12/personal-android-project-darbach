package edu.cnm.deepdive.dicecrunch;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.dicecrunch.model.type.Operator;
import edu.cnm.deepdive.dicecrunch.service.DiceCrunchDatabase;
import edu.cnm.deepdive.dicecrunch.service.GoogleSignInService;
import io.reactivex.schedulers.Schedulers;
import java.security.SecureRandom;

/**
 * Initializes (in the {@link #onCreate()} method) application-level resources. This class
 * <strong>must</strong> be referenced in {@code AndroidManifest.xml}, or it will not be loaded and
 * used by the Android system.
 */
public class DiceCrunchApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Operator.setRng(new SecureRandom());
    GoogleSignInService.setContext(this);
    Stetho.initializeWithDefaults(this);
    DiceCrunchDatabase.setContext(this);
    DiceCrunchDatabase.getInstance()
        .getDieDao()
        .delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }

}
