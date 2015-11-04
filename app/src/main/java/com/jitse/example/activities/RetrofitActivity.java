package com.jitse.example.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jitse.example.ExampleApplication;
import com.jitse.example.R;
import com.jitse.example.retrofit.BrandResponse;
import com.jitse.example.retrofit.BrandService;
import com.jitse.example.retrofit.ProductResponse;
import com.jitse.example.retrofit.ProductService;
import com.jitse.example.retrofit.SearchResponse;
import com.jitse.example.retrofit.SearchService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class RetrofitActivity extends Activity {

    @InjectView(R.id.btn_search_zappos)
    Button mBtnSearch;
    @InjectView(R.id.et_search_zappos)
    EditText mEtSearch;

    @InjectView(R.id.btn_search_mafia)
    Button mBtnSearchMafia;
    @InjectView(R.id.et_search_mafia)
    EditText mEtSearchMafia;


    @InjectView(R.id.btn_product_mafia)
    Button mBtnProductMafia;
    @InjectView(R.id.et_product_mafia)
    EditText mEtProductMafia;

    @InjectView(R.id.tv_results)
    TextView mTvResults;



    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_search_zappos)
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


    @OnClick(R.id.btn_product_mafia)
    public void onClickedMafia() {
        ProductService productService = ((ExampleApplication) getApplication()).getProductService();
        productService.performSearch(mEtProductMafia.getText().toString(), new Callback<ProductResponse>() {
            @Override
            public void success(final ProductResponse productResponse, Response response) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTvResults.setText(productResponse.toString());
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                throw error;
            }
        });
    }

    @OnClick(R.id.btn_search_mafia)
    public void onClickedSearchMafia() {
        SearchService searchService = ((ExampleApplication)getApplication()).getSearchService();
        searchService.performSearch(mEtSearchMafia.getText().toString(), new Callback<SearchResponse>() {
            @Override
            public void success(SearchResponse searchResponse, Response response) {
                mTvResults.setText(searchResponse.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                throw error;
            }
        });
    }
}
