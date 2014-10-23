package com.jitse.example;

import android.app.Application;

import com.jitse.example.retrofit.BrandService;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by jitse on 10/21/14.
 */
public class ExampleApplication extends Application {

    public static String API = "https://api.zappos.com";
    private static final String KEY = "5ca1aa6b9151f729f5b7f05b14dba5ff8aedb975";
    BrandService mBrandService;

    @Override
    public void onCreate() {
        super.onCreate();

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("User-Agent", "Android-App v" + BuildConfig.VERSION_CODE);
                request.addQueryParam("key", KEY);
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API)
                .setRequestInterceptor(requestInterceptor)
                .build();

        mBrandService = restAdapter.create(BrandService.class);
    }

    public BrandService getBrandService() {
        return mBrandService;
    }
}
