package com.jitse.example.retrofit;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by jitse on 10/21/14.
 */
public interface BrandService {

    @GET("/Brand/{id}")
    void getBrandByBrandId(@Path("id") String brandId, Callback<BrandResponse> callback);

}
