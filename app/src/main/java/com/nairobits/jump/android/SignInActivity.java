package com.nairobits.jump.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignInActivity extends MyAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Remove default menu defined in MyAppActivity
        return true;
    }

    public void signIn(View view) {

        TextView emailView = (TextView) findViewById(R.id.email);
        String email = String.valueOf(emailView.getText());

        TextView pswsdView = (TextView) findViewById(R.id.password);
        String password = String.valueOf(pswsdView.getText().toString());

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Snackbar.make(view, "Enter a username and password", Snackbar.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Sign in failed
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signInAnonymous(View view) {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // Sign in failed
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInAnonymously", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication Failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // User the FirebaseUser.isAnonymous() method to determine if current user is anonymous
    }

    @Override
    public void onSignInCompleted() {
        // Called after sign in is complete
        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);
    }

    public void goToSignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
