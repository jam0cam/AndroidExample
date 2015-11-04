package com.jitse.example.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jitse.example.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by jitse on 12/2/14.
 */
public class SimilarItemsRecyclerAdapter extends RecyclerView.Adapter<SimilarItemsRecyclerAdapter.ViewHolder> {
    private static final String TAG = SimilarItemsRecyclerAdapter.class.getName();

    private RecyclerClickListener mRecyclerListener;
    private List<String> mUrls;
    private ImageLoader mImageLoader;

    public interface RecyclerClickListener {
        public void onSimilarItemsClicked(ImageView imageView, String url, int position);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImageView;
        public String url;

        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.img_similar_style);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SimilarItemsRecyclerAdapter(List<String> urls,  RecyclerClickListener recyclerListener, ImageLoader imageLoader) {
        mUrls = urls;
        mRecyclerListener = recyclerListener;
        mImageLoader = imageLoader;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SimilarItemsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_items_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.url = mUrls.get(position);

        mImageLoader.displayImage(holder.url, holder.mImageView);

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecyclerListener != null) {
                    mRecyclerListener.onSimilarItemsClicked(holder.mImageView, holder.url, position);
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mUrls == null ? 0 : mUrls.size();
    }
}