package com.jitse.example.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitse.example.R;
import com.jitse.example.retrofit.ProductResponse;

import java.io.IOException;
import java.io.InputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class JacksonActivity extends Activity {

    private static final String TAG = JacksonActivity.class.getName();

    @InjectView(R.id.btn_jackson)
    Button mBtnJackson;

    @InjectView(R.id.tv_jackson)
    TextView mTvJackson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jackson);

        ButterKnife.inject(this);


    }

    @OnClick(R.id.btn_jackson)
    public void onclick() {
        ObjectMapper mapper = new ObjectMapper();
        ProductResponse pr = null;
        try {

            InputStream inputStream = getResources().openRawResource(R.raw.product);


            pr = mapper.readValue(inputStream, ProductResponse.class);

            Log.d(TAG, "brandName:" + pr.getProduct().getBrandName());
            Log.d(TAG, "productName:" + pr.getProduct().getProductName());
            Log.d(TAG, "ASIN:" + pr.getProduct().getProductId());

        } catch (IOException e) {
            Log.e(TAG, "error:", e);
            e.printStackTrace();
        }


    }

}
