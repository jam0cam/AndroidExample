package com.jitse.example.activities;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.TextureView;
import android.widget.LinearLayout;

import com.jitse.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VideoActivity extends ActionBarActivity {

    private static final String BUCK_BUNNY = "http://video.webmfiles.org/big-buck-bunny_trailer.webm";
    private static final String TAG = VideoActivity.class.getName();

    private MediaPlayer mMediaPlayer;
    private int mVideoWidth;
    private int mVideoHeight;

    @InjectView(R.id.texture_view)
    TextureView mVideo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);

        ButterKnife.inject(this);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mVideoWidth = size.x;
        mVideoHeight = mVideoWidth * 2/3;

        mVideo1.setLayoutParams(new LinearLayout.LayoutParams(mVideoWidth, mVideoHeight));

        playVideo(mVideo1, BUCK_BUNNY);
    }


    private void playVideo(final TextureView tv, final String url) {
        tv.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int newWidth, int newHeight) {
                Log.d(TAG, "onSurfaceTextureAvailable");

                Surface s = new Surface(surface);

                Matrix txform = new Matrix();
                tv.getTransform(txform);
                txform.setScale((float) newWidth / mVideoWidth, (float) newHeight / mVideoHeight);
                tv.setTransform(txform);


                try {
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.setDataSource(url);
                    mMediaPlayer.setSurface(s);
                    mMediaPlayer.prepareAsync();
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Log.e(TAG, "onCompletion");
                        }
                    });
                    mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            Log.e(TAG, "onError");
                            return false;
                        }
                    });

                    mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            Log.d(TAG, "onPrepared");
                            mp.start();
                        }
                    });

                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Log.d(TAG, "Exception");
                    e.printStackTrace();
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }
}
