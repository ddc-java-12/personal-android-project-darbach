package edu.cnm.deepdive.dicecrunch;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.dicecrunch.service.DiceCrunchDatabase;
import io.reactivex.schedulers.Schedulers;

/**
 * Initializes (in the {@link #onCreate()} method) application-level resources. This class
 * <strong>must</strong> be referenced in {@code AndroidManifest.xml}, or it will not be loaded and
 * used by the Android system.
 */
public class DiceCrunchApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    DiceCrunchDatabase.setContext(this);
    DiceCrunchDatabase.getInstance()
        .getDieDao()
        .delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }

}
