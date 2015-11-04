package com.jitse.example.activities;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jitse.example.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WebviewActivity extends ActionBarActivity {

    private static final String TAG = WebviewActivity.class.getName();

    @InjectView(R.id.ll)
    LinearLayout mLl;

    @InjectView(R.id.ll_parent)
    LinearLayout mLlParent;

    @InjectView(R.id.webview)
    WebView mWebview;

    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @InjectView(R.id.btn_description)
    Button mBtnDescription;

    boolean isFirstTime = true;

    String mDescription = "<!DOCTYPE html>\n" +
            "    <html>\n" +
            "    <head>\n" +
            "    <!--<script type=\"text/javascript\">-->\n" +
            "    <!--document.addEventListener(\"DOMContentLoaded\", function () {-->\n" +
            "    <!--var anchors = document.getElementsByTagName('a'), toRemove = [];-->\n" +
            "    <!--// Remove all anchors-->\n" +
            "    <!--for (var i = 0; i < anchors.length; i++) {-->\n" +
            "    <!--var parent = anchors[i];-->\n" +
            "    <!--while ((parent = parent.parentNode)) {-->\n" +
            "    <!--if (parent.tagName === 'LI') {-->\n" +
            "    <!--//parent.parentNode.removeChild(parent);-->\n" +
            "    <!--toRemove.push(parent);-->\n" +
            "    <!--break;-->\n" +
            "    <!--}-->\n" +
            "    <!--}-->\n" +
            "    <!--}-->\n" +
            "    <!--// Moved here in case there was more than one link-->\n" +
            "    <!--for (var x = 0; x < toRemove.length; x++) {-->\n" +
            "    <!--document.getElementsByTagName('ul')[0].removeChild(toRemove[x]);-->\n" +
            "    <!--}-->\n" +
            "    <!--}, false);-->\n" +
            "    <!--</script>-->\n" +
            "    <style type=\"text/css\">\n" +
            "    body {\n" +
            "    font-family: Roboto, Droid Sans, Verdana, sans-serif;\n" +
            "    color: #a0a0a0;\n" +
            "    width: 96%;\n" +
            "    margin: 0;\n" +
            "    padding: 20px 2% 20px 2%;\n" +
            "    font-size: 10pt;\n" +
            "    }\n" +
            "    h2 {\n" +
            "    font-weight: bold;\n" +
            "    font-size: 12pt;\n" +
            "    }\n" +
            "    ul {\n" +
            "    margin: 0 0 0 0;\n" +
            "    padding: 0 25px 0 25px;\n" +
            "    list-style-type: square;\n" +
            "    }\n" +
            "    ul li:first-child {\n" +
            "    font-weight: normal;\n" +
            "    padding: 0;\n" +
            "    margin: 0 0 20px -17px;\n" +
            "    list-style: none;\n" +
            "    font-style: italic;\n" +
            "    font-size: 1.4em;\n" +
            "    color: #717171;\n" +
            "    }\n" +
            "    li ul li:first-child {\n" +
            "    font-family: Roboto, Droid Sans, Verdana, sans-serif;\n" +
            "    margin: 6px 0;\n" +
            "    font-weight: normal;\n" +
            "    font-style: normal;\n" +
            "    list-style: square;\n" +
            "    color: #a0a0a0;\n" +
            "    width: 100%;\n" +
            "    font-size: 10pt;\n" +
            "    }\n" +
            "    li {\n" +
            "    margin-bottom: 6px;\n" +
            "    }\n" +
            "    a {\n" +
            "    color: #464646;\n" +
            "    text-decoration: none;\n" +
            "    }\n" +
            "    </style>\n" +
            "    </head>\n" +
            "    <body>\n" +
            "    <ul><li>High intensity training is your forte; you're a woman who jumps, squats, pushes and pulls. That's why Nike&reg; designed the Free 1.0 Cross Bionic trainer specifically for women to promote balance, stability, and flexibility. Go out there and show them what you're made of, girl.</li><li>SKU: 8300000</li>\n" +
            "    <li>Zero millimeter offset gives the female athlete the most barefoot-like feel of any Nike Free, helping her remained balanced, stable, and flexible. </li>\n" +
            "    <li>Ultra lightweight textile upper with dynamic Flywire technology provides an adaptive and stable fit.</li>\n" +
            "    <li>A more anatomically shaped heel and forefoot enable natural motion for multidirectional movement. </li>\n" +
            "    <li>Integrated lace closure for a snug fit. </li>\n" +
            "    <li>Pull-tab at heel for easy on and off. </li>\n" +
            "    <li>Smooth textile lining and a padded footbed for all-day comfort. </li>\n" +
            "    <li>Durable rubber outsole with strategically placed flexgrooves in the forefoot for flexibility and traction. </li>\n" +
            "    <li>Contrast Nike Swoosh&reg; logo at lateral toe, and Nike Training Free 1.0 Cross Bionic callout at tongue. </li>\n" +
            "    <li>Imported. </li>\n" +
            "    <li class=\"measurements\">Measurements:\n" +
            "    <ul>\n" +
            "    <li> Weight: 6 oz</li>\n" +
            "    </ul>\n" +
            "    </li>\n" +
            "    <li>Product measurements were taken using size 9, width B - Medium. Please note that measurements may vary by size.</li>\n" +
            "    </ul>\n" +
            "    </body>\n" +
            "    </html>";


    String urls [] = {
            "http://a1.zassets.com/images/z/2/5/9/0/0/7/2590074-p-MULTIVIEW.jpg",
            "http://a1.zassets.com/images/z/2/6/3/1/4/3/2631430-p-MULTIVIEW.jpg",
            "http://a1.zassets.com/images/z/2/8/7/6/1/5/2876156-p-MULTIVIEW.jpg",
            "http://a1.zassets.com/images/z/2/7/2/5/1/8/2725180-p-MULTIVIEW.jpg",
            "http://a1.zassets.com/images/z/2/7/2/5/1/8/2725182-p-MULTIVIEW.jpg",
            "http://a1.zassets.com/images/z/2/7/2/5/1/8/2725181-p-MULTIVIEW.jpg"
    };

    int mWebviewOriginalHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        ButterKnife.inject(this);

        mWebview.setScrollbarFadingEnabled(Boolean.TRUE);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebContentsDebuggingEnabled(true);
        mWebview.loadDataWithBaseURL("http://www.zappos.com", mDescription, "text/html", "UTF-8", null);
        mWebview.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Log.d(TAG, "onLayoutChange");
                if (isFirstTime && mWebview.getHeight() > 250) {
//                    mWebview.setVisibility(View.GONE);

                    Log.d(TAG, "setting height to 250");
                    isFirstTime = false;
                    mWebviewOriginalHeight = mWebview.getHeight();
                    mWebview.getLayoutParams().height = 250;
                }
            }
        });

        LayoutTransition parentTransition = new LayoutTransition();
        parentTransition.enableTransitionType(LayoutTransition.CHANGING);
        mLlParent.setLayoutTransition(parentTransition);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();

        ImageLoader imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        SimilarItemsRecyclerAdapter mAdapterSimilarItems = new SimilarItemsRecyclerAdapter(Arrays.asList(urls), new SimilarItemsRecyclerAdapter.RecyclerClickListener() {
            @Override
            public void onSimilarItemsClicked(ImageView imageView, String style, int position) {
            }
        }, imgLoader);

        mRecyclerView.setAdapter(mAdapterSimilarItems);
    }

    @OnClick(R.id.btn_description)
    public void descriptionClick() {
        Log.d(TAG, "testing");
//        if (mWebview.getVisibility() == View.VISIBLE) {
//            mWebview.setVisibility(View.GONE);
//        } else {
//            mWebview.setVisibility(View.VISIBLE);
//        }

        if (mWebview.getHeight() == 250) {
            Log.d(TAG, "setting height to " + mWebviewOriginalHeight);
            mWebview.getLayoutParams().height = mWebviewOriginalHeight;
        } else {
            mWebview.getLayoutParams().height = 250;
            Log.d(TAG, "setting height to " + 250);
        }

        mWebview.requestLayout();
    }
}
