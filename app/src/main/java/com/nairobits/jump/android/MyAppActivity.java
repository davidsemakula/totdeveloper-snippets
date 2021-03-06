package com.nairobits.jump.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/*
* This activity was created to be a base class for all other activities
*
*
* It provides convenience methods for detecting authentication events and performing log out
*  - onSignInCompleted()
*  - onSignOutCompleted()
*  - signOut()
*
* Adds a default menu
*  - R.menu.default_menu
*  - onCreateOptionsMenu()
*  - onOptionsItemSelected()
*
*
* */
public class MyAppActivity extends AppCompatActivity {

    protected FirebaseAuth mAuth;
    protected FirebaseAuth.AuthStateListener mAuthListener;
    protected FirebaseDatabase mDatabase;
    protected String TAG = "<Replace with your app name>"; // This used for logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Listen for changes in Authentication state
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // Handle successful log in
                    onSignInCompleted();
                } else {
                    // handle successful log out
                    onSignOutCompleted();
                }
            }
        };
        mDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Attach Auth listener
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Dettach Auth listener when activity is being stopped
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Creates the default options menu that just includes a SignOut option
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.default_menu, menu);
        return true;

        // Override this method to remove menu from select activities like Login and Splash screens
        // Be sure not to call super in the override
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Called when a menu option is tapped
        switch (item.getItemId()) {
            /* Remove cases that don't apply to your app */
            case R.id.profile:
                goToProfile();
                return true;
            case R.id.signout:
                signOut();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public void onSignInCompleted() {
        // Override this in children that care about the sign in event
    }

    public void onSignOutCompleted() {
        // Override this in children that care about the sign out event
    }

    public void goToProfile() {
        // Override this in children that care about the sign out event
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    // Call this to get the currrent Firebase user from any activity that extend MyAppActivity
    public FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void showToast(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }
}
