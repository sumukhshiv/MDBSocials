package io.github.sumukhshiv.mdbsocials;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by sumukhshivakumar on 3/2/17.
 */

public class FirebaseUtils {

    public static void login(final String email, String password, final ProgressDialog progressDialog, Activity activity) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final Context context = activity.getApplicationContext();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show();
                            Intent loginToFeedIntent = new Intent(context, UserArea.class);
                            loginToFeedIntent.putExtra("email", email);
                            loginToFeedIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(loginToFeedIntent);
                        } else if (!task.isSuccessful()) {
                            Toast.makeText(context, "Email/Password combination is not correct. Please Try Again.", Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    public static void signUp(final String email, final String password, final ProgressDialog progressDialog, Activity activity) {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final Context context = activity.getApplicationContext();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            //Prints the reason why the user was unable to register
                            Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        } else if (task.isSuccessful()) {
                            //case where registration was successful
                            progressDialog.dismiss();
                            Toast.makeText(context, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                            //After registering go straight to Feed
                            Intent signupToFeedIntent = new Intent(context, UserArea.class);
                            signupToFeedIntent.putExtra("email", email);
                            signupToFeedIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(signupToFeedIntent);
                        }


                    }
                });
    }

}
