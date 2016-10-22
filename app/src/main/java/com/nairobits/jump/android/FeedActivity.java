package com.nairobits.jump.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.nairobits.jump.android.viewholders.ListItemViewHolder;
import com.nairobits.jump.android.models.Item;

public class FeedActivity extends MyAppActivity {

    private RecyclerView mRecyclerView;
    private DatabaseReference mArticlesRef;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mRecyclerView = (RecyclerView) findViewById(R.id.feed);
        mArticlesRef = mDatabase.getReference(Constants.FIREBASE_CHILD_ITEMS);
        setUpFirebaseAdapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }


    private void setUpFirebaseAdapter() {
        // FirebaseRecyclerAdapter recycler adapter is a Recycler adapter made for Firebase
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Item, ListItemViewHolder>
                (Item.class, R.layout.list_item, ListItemViewHolder.class,
                        mArticlesRef) {

            @Override
            protected void populateViewHolder(ListItemViewHolder viewHolder,
                                              Item model, int position) {
                viewHolder.bindItem(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    public void goToSave(View view) {
        startActivity(new Intent(this, SaveActivity.class));
    }
}
