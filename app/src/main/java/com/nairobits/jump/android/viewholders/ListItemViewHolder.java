package com.nairobits.jump.android.viewholders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nairobits.jump.android.R;
import com.nairobits.jump.android.models.Item;

public class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mTitleView;
    public TextView mDescView;

    public interface ViewHolderItemClickListener {
        void onViewHolderItemClick(ListItemViewHolder viewHolder, View view, int position);
    }

    private ViewHolderItemClickListener mViewHolderItemClickListener = null;

    public ListItemViewHolder(View itemView) {
        super(itemView);

        mTitleView = (TextView) itemView.findViewById(R.id.title);
        mDescView = (TextView) itemView.findViewById(R.id.description);

        itemView.setOnClickListener(this);

        // Add a click event listener
        Context mContext = itemView.getContext();
        try {
            mViewHolderItemClickListener = (ViewHolderItemClickListener) mContext;
        } catch (ClassCastException e) {
            Log.i("ListItemViewHolder", mContext.toString() + " should implement ViewHolderItemClickListener");
        }
    }

    public void bindItem(Item item) {
        mTitleView.setText(item.title);
        mDescView.setText(item.description);
    }

    @Override
    public void onClick(View view) {
        // Call the onViewHolderItemClick on an implementer
        if(mViewHolderItemClickListener != null) {
            mViewHolderItemClickListener.onViewHolderItemClick(this, view, getAdapterPosition());
        }
    }
}
