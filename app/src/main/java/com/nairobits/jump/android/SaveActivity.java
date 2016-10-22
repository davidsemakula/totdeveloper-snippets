package com.nairobits.jump.android;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.nairobits.jump.android.models.Item;

public class SaveActivity extends MyAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
    }

    public void saveArticle(View view) {
        DatabaseReference articlesRef = mDatabase.getReference(Constants.FIREBASE_CHILD_ITEMS);
        String newId = articlesRef.push().getKey();

        TextView titleView = (TextView) findViewById(R.id.title);
        String title = String.valueOf(titleView.getText());

        TextView descriptionView = (TextView) findViewById(R.id.description);
        String description = String.valueOf(descriptionView.getText().toString());

        String userId = null;
        FirebaseUser mFirebaseUser = getCurrentUser();
        if(mFirebaseUser != null) {
            userId = mFirebaseUser.getUid();
        }

        Item item = new Item(title, description, userId);
        articlesRef.child(newId).setValue(item);

        showToast("Item saved");

        // Clear the form
        titleView.setText("");
        descriptionView.setText("");
    }
}
