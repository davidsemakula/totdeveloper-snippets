package com.nairobits.jump.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileActivity extends MyAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void updateUser(View view) {

        TextView nameView = (TextView) findViewById(R.id.fullname);
        String fulName = String.valueOf(nameView.getText());

        if(TextUtils.isEmpty(fulName)) {
            Snackbar.make(view, "Enter your full name", Snackbar.LENGTH_LONG).show();
            return;
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Jane Q. User")
                //.setPhotoUri(Uri.parse("<Uri of file goes here>"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Snackbar.make(findViewById(R.id.fullname), "User profile updated.", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onSignOutCompleted() {
        Intent secondIntent = new Intent(this, SignInActivity.class);
        startActivity(secondIntent);
    }
}
