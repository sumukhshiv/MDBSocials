package io.github.sumukhshiv.mdbsocials;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.graphics.Color.DKGRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegisterUser;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewPasswordStrengthIndiactor;
    private TextView textViewPasswordGraphic;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        buttonRegisterUser = (Button) findViewById(R.id.buttonRegisterSignUp);
        buttonRegisterUser.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.editTextEmailSignUp);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordSignup);

        textViewPasswordStrengthIndiactor = (TextView) findViewById(R.id.textViewPasswordStrengthIndicator);
        textViewPasswordGraphic = (TextView) findViewById(R.id.textViewPasswordGraphic);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("AUTHinSignup", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("AUTHinSignUp", "onAuthStateChanged:signed_out");
                }
            }
        };

        editTextPassword.addTextChangedListener(mTextEditorWatcher);

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        public void afterTextChanged(Editable s)
        {
            switch (s.length()) {
                case 0:
                    textViewPasswordStrengthIndiactor.setTextColor(DKGRAY);
                    textViewPasswordGraphic.setBackgroundColor(DKGRAY);
                    textViewPasswordGraphic.setWidth(0);
                    textViewPasswordGraphic.setHeight(12);
                    break;
                case (1):
                    textViewPasswordStrengthIndiactor.setText("Must be at least 6 digits");
                    textViewPasswordStrengthIndiactor.setTextColor(DKGRAY);
                    textViewPasswordGraphic.setBackgroundColor(DKGRAY);
                    textViewPasswordGraphic.setWidth(50);
                    textViewPasswordGraphic.setHeight(12);
                    break;
                case 6:
                    textViewPasswordStrengthIndiactor.setText("Easy");
                    textViewPasswordGraphic.setBackgroundColor(RED);
                    textViewPasswordGraphic.setWidth(200);
                    textViewPasswordGraphic.setHeight(12);
                    break;
                case 8:
                    textViewPasswordStrengthIndiactor.setText("Easy");
                    textViewPasswordGraphic.setBackgroundColor(RED);
                    textViewPasswordGraphic.setWidth(300);
                    textViewPasswordGraphic.setHeight(12);
                    break;

                case 11:
                    textViewPasswordStrengthIndiactor.setText("Medium");
                    textViewPasswordGraphic.setBackgroundColor(YELLOW);
                    textViewPasswordGraphic.setWidth(400);
                    textViewPasswordGraphic.setHeight(12);
                    break;

                case 13:
                    textViewPasswordStrengthIndiactor.setText("Medium");
                    textViewPasswordGraphic.setBackgroundColor(YELLOW);
                    textViewPasswordGraphic.setWidth(500);
                    textViewPasswordGraphic.setHeight(12);
                    break;
                case 15:
                    textViewPasswordStrengthIndiactor.setText("Strong");
                    textViewPasswordGraphic.setBackgroundColor(GREEN);
                    textViewPasswordGraphic.setWidth(850);
                    textViewPasswordGraphic.setHeight(12);
                    break;
                case 17:
                    textViewPasswordStrengthIndiactor.setText("Strong");
                    textViewPasswordGraphic.setBackgroundColor(GREEN);
                    textViewPasswordGraphic.setWidth(950);
                    textViewPasswordGraphic.setHeight(12);
                    break;
                case 20:
                    textViewPasswordStrengthIndiactor.setText("Password Max Length Reached");
                    textViewPasswordGraphic.setBackgroundColor(DKGRAY);

            }

        }
    };

    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter a valid Email Address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {

            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (!email.contains("@")) {
                                Toast.makeText(SignUpActivity.this, "Please enter a valid Email Address", Toast.LENGTH_SHORT).show();
                            }
                            else if (mAuth.signInWithEmailAndPassword(email, password) != null) {
                                Toast.makeText(SignUpActivity.this, "An account with this email already exists.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Authentication Failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        } else if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                            Intent signupToFeedIntent = new Intent(SignUpActivity.this, UserArea.class);
                            signupToFeedIntent.putExtra("email", email);
                            startActivity(signupToFeedIntent);
                        }


                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRegisterSignUp:
                registerUser();
                break;
        }
    }
}
