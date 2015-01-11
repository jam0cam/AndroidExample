package com.jitse.example.intern;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jitse.example.R;
import com.squareup.picasso.Picasso;

/**
 * Created by jitse on 1/9/15.
 */
public class PhotoRecyclerAdapter extends RecyclerView.Adapter<PhotoRecyclerAdapter.ViewHolder> {
    public interface ClickListener {
        void onClicked(ImageView imageView, int index);
    }

    private Uri[] mUrls;
    private ClickListener mClickListener;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = ViewHolder.class.getName();

        // each data item is just a string in this case
        public CardView mCardView;
        public ImageView mImageView;
        public ClickListener mClickListener;

        public ViewHolder(View v, ClickListener clickListener) {
            super(v);

            mClickListener = clickListener;
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mImageView = (ImageView) mCardView.findViewById(R.id.iv);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PhotoRecyclerAdapter(Context context, ClickListener listener, Uri[] urls) {
        mClickListener = listener;
        mUrls = urls;
        mContext = context;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v, mClickListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Picasso.with(mContext)
                .load(mUrls[position])
                .fit()
                .centerCrop()
                .into(holder.mImageView);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onClicked(holder.mImageView, position);
                }
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mUrls.length;
    }


}
