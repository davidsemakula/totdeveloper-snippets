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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignUpActivity extends MyAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Remove default menu defined in MyAppActivity
        return true;
    }

    public void signUp(View view) {
        TextView emailView = (TextView) findViewById(R.id.email);
        String email = String.valueOf(emailView.getText());

        TextView pswsdView = (TextView) findViewById(R.id.password);
        String password = String.valueOf(pswsdView.getText().toString());

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Snackbar.make(view, "Enter a username and password", Snackbar.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Snackbar.make(findViewById(R.id.email), "Authentication Failed", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onSignInCompleted() {
        // Called after sign in is complete
        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);
    }
}
