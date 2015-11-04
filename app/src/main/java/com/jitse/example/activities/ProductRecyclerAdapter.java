package com.jitse.example.activities;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jitse.example.R;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> {
    private String[] mColors;
    private String[] mProductNames;
    private String[] mBrandNames;
    private Drawable[] mDrawable;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView1;
        public TextView mTextView2;
        public CardView mCardView;
        public ImageView mImageView;
        public FrameLayout mFrameLayout;

        public ViewHolder(View v) {
            super(v);

            mFrameLayout = (FrameLayout) v;
            mCardView = (CardView) mFrameLayout.findViewById(R.id.card_view);
            mTextView1 = (TextView) mCardView.findViewById(R.id.tv_1);
            mTextView2 = (TextView) mCardView.findViewById(R.id.tv_2);
            mImageView = (ImageView) mCardView.findViewById(R.id.img_style);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProductRecyclerAdapter(String[] colors, String[] products, String[] brands, Drawable[] drawables) {
        mColors = colors;
        mDrawable = drawables;
        mProductNames = products;
        mBrandNames = brands;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProductRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.styles_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        if (mColors != null && mColors.length > 0) {
            holder.mTextView1.setVisibility(View.GONE);
            holder.mTextView2.setText(mColors[position]);
        } else {
            holder.mTextView1.setText(mBrandNames[position]);
            holder.mTextView2.setText(mProductNames[position]);
        }
        holder.mImageView.setImageDrawable(mDrawable[position]);

        if (position == 0) {
            int top = holder.mFrameLayout.getPaddingTop();
            int bottom = holder.mFrameLayout.getPaddingBottom();
            int right = holder.mFrameLayout.getPaddingRight();
            holder.mFrameLayout.setPadding(0, top, right, bottom);
        } else if (position == getItemCount()-1) {
            int top = holder.mFrameLayout.getPaddingTop();
            int bottom = holder.mFrameLayout.getPaddingBottom();
            int left = holder.mFrameLayout.getPaddingLeft();
            holder.mFrameLayout.setPadding(left, top, 0, bottom);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mColors != null && mColors.length > 0) {
            return mColors.length;
        } else {
            return mProductNames.length;
        }
    }
}