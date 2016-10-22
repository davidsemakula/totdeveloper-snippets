package com.nairobits.jump.android.models;


import android.support.annotation.Nullable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/* Java representations of the item to be store
*
* Rename the class (and file) to something descriptive
*
*/

@IgnoreExtraProperties
public class Item {

    public String title;
    public String description;
    public String user;
    public long reverse_order;

    public Item() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Item(String title, String description, @Nullable String user) {
        this.title = title;
        this.description = description;
        this.user = user;
        // Added to support reverse order sorting
        this.reverse_order = 0 - System.currentTimeMillis();
    }

}