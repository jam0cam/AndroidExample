package com.jitse.example.intern;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.jitse.example.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.ByteArrayOutputStream;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public interface ClickListener {
        void onClicked(ImageView imageView, byte[] b);
    }

    private String[] mUrls;
    private String[] mBrands;
    private ImageLoader mImageLoader;
    private ClickListener mClickListener;
    private Drawable mSpinner;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = ViewHolder.class.getName();

        // each data item is just a string in this case
        public CardView mCardView;
        public ImageView mImageView;
        public ProgressBar mProgressBar;
        public ClickListener mClickListener;

        public ViewHolder(View v, ClickListener clickListener) {
            super(v);

            mClickListener = clickListener;
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) {

                        Drawable d = mImageView.getDrawable();

                        if (d instanceof BitmapDrawable) {
                            Bitmap bmp = ((BitmapDrawable)d).getBitmap();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
                            byte[] b = baos.toByteArray();
                            mClickListener.onClicked(mImageView, b);
                        }

                    }
                }
            });

            mImageView = (ImageView) mCardView.findViewById(R.id.iv);
            mProgressBar = (ProgressBar) mCardView.findViewById(R.id.pb);
        }

        public void showProgress() {
            Log.d(TAG, "Showing progress bar");
            mProgressBar.setAlpha(1);
            mProgressBar.setVisibility(View.VISIBLE);
            mImageView.setVisibility(View.GONE);
        }

        public void hideProgress() {
            Log.d(TAG, "Hiding progress bar");
            mProgressBar.animate().alpha(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.d(TAG, "Showing Image View");
                    mImageView.setVisibility(View.VISIBLE);
                    mImageView.setAlpha(0f);
                    mImageView.animate().alpha(1).setDuration(200).start();

                    mProgressBar.setVisibility(View.GONE);
                }
            }).setDuration(200).start();
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerAdapter(ClickListener listener, ImageLoader imgLoader, Drawable spinner, String[] urls, String[] brands) {
        mClickListener = listener;
        mImageLoader = imgLoader;
        mUrls = urls;
        mBrands = brands;
        mSpinner = spinner;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_card_view, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v, mClickListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.showProgress();
        mImageLoader.displayImage(mUrls[position], holder.mImageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.hideProgress();
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
        holder.mImageView.setTag(mBrands[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mUrls.length;
    }

}