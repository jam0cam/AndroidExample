package com.jitse.example;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jitse.example.retrofit.BrandResponse;
import com.jitse.example.retrofit.BrandService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class RetrofitActivity extends Activity {

    @InjectView(R.id.btn_search)
    Button mBtnSearch;

    @InjectView(R.id.tv_results)
    TextView mTvResults;

    @InjectView(R.id.et_search)
    EditText mEtSearch;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_search)
    public void onClicked() {
        BrandService brandService = ((ExampleApplication) getApplication()).getBrandService();
        brandService.getBrandByBrandId(mEtSearch.getText().toString(), new Callback<BrandResponse>() {
            @Override
            public void success(final BrandResponse brandResponse, Response response) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTvResults.setText(brandResponse.toString());
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                mTvResults.setText(error.getMessage());
            }
        });
    }

}
