package com.jitse.example.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.jitse.example.ExampleApplication;
import com.jitse.example.R;
import com.jitse.example.intern.PhotoRecyclerAdapter;

public class AlbumActivity extends ActionBarActivity implements PhotoRecyclerAdapter.ClickListener {
    private static final String TAG = AlbumActivity.class.getName();

    private int count;
    private Bitmap[] thumbnails;
    private boolean[] thumbnailsselection;
    private Uri[] arrPath;

    private RecyclerView.LayoutManager mLayoutManager;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_album);

        final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };
        final String orderBy = MediaStore.Images.Media._ID;
        Cursor imagecursor = managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy);
        int image_column_index = imagecursor.getColumnIndex(MediaStore.Images.Media._ID);
        this.count = 10;
        this.thumbnails = new Bitmap[this.count];
        this.arrPath = new Uri[this.count];
        this.thumbnailsselection = new boolean[this.count];
        for (int i = 0; i < this.count; i++) {
            imagecursor.moveToPosition(i);
            int id = imagecursor.getInt(image_column_index);

            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
            thumbnails[i] = MediaStore.Images.Thumbnails.getThumbnail(
                    getApplicationContext().getContentResolver(), id,
                    MediaStore.Images.Thumbnails.MINI_KIND, null);

            arrPath[i] = Uri.parse("file://" + imagecursor.getString(dataColumnIndex));
        }

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        PhotoRecyclerAdapter mAdapter = new PhotoRecyclerAdapter(this, this, arrPath);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClicked(final ImageView imageView, final int position) {


        ((ExampleApplication)getApplication()).mBitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

        Intent intent = new Intent(AlbumActivity.this, PictureActivity.class);
        intent.putExtra("file", Uri.parse("file://" + arrPath[position]));
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AlbumActivity.this, imageView, "transition_image");
        startActivity(intent, options.toBundle());

//
//        //send over the image, small byte array
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        Bitmap bmp = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
//
//        bmp.compress(Bitmap.CompressFormat.PNG, 10, stream);
//        byte[] bytes = stream.toByteArray();
//        intent.putExtra("bitmap", bytes);


    }
}
