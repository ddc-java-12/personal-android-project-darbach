package edu.cnm.deepdive.dicecrunch.service;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import io.reactivex.Single;

/**
 * Singleton service class to handle business logic of Google Sign In.
 */
public class GoogleSignInService {

  private static final String BEARER_FORMAT = "Bearer %s";

  private static Application context;

  private final GoogleSignInClient client;

  private GoogleSignInAccount account;

  private GoogleSignInService() {
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail()
        .requestId()
        .requestProfile()
//        .requestIdToken(BuildConfig.CLIENT_ID) // Use client ID of web application.
        .build();
    client = GoogleSignIn.getClient(context, options);
  }

  /**
   * Store the application context.
   *
   * @param context
   */
  public static void setContext(Application context) {
    GoogleSignInService.context = context;
  }

  /**
   * Store the logged-in google account.
   *
   * @param account
   */
  private void setAccount(GoogleSignInAccount account) {
    this.account = account;
  }

  /**
   * Return the singleton instance of the class.
   *
   * @return
   */
  public static GoogleSignInService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Refreshes the login information if the user has not been away from the app for too long, to
   * avoid having to sign in again.
   *
   * @return A ReactiveX task.
   */
  public Single<GoogleSignInAccount> refresh() {
    return Single.create((emitter) -> client.silentSignIn()
        .addOnSuccessListener(this::setAccount)
        .addOnSuccessListener(emitter::onSuccess)
        .addOnFailureListener(emitter::onError));
  }

  /**
   * Refreshes the bearer token for writing to the log.
   *
   * @return A ReactiveX task.
   */
  public Single<String> refreshBearerToken() {
    return refresh()
        .map((account) -> String.format(BEARER_FORMAT, account.getIdToken()));
  }

  /**
   * Sets up the sign in environment.
   *
   * @param activity
   * @param requestCode
   */
  public void startSignIn(Activity activity, int requestCode) {
    account = null;
    Intent intent = client.getSignInIntent();
    activity.startActivityForResult(intent, requestCode);
  }

  /**
   * Finishes the sign in with either success or failure.
   *
   * @param data
   * @return
   */
  public Task<GoogleSignInAccount> completeSignIn(Intent data) {
    Task<GoogleSignInAccount> task = null;
    try {
      task = GoogleSignIn.getSignedInAccountFromIntent(data);
      setAccount(task.getResult(ApiException.class));
    } catch (ApiException e) {
      // Ignored: Exception will be passed automatically to onFailureListener.
    }
    return task;
  }

  /**
   * Logs the user out of Google Sign In.
   *
   * @return
   */
  public Task<Void> signOut() {
    return client.signOut()
        .addOnCompleteListener((task) -> setAccount(null));
  }

  private static class InstanceHolder {

    private static final GoogleSignInService INSTANCE = new GoogleSignInService();
  }
}
