package com.jitse.example.retrofit;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by jitse on 11/5/14.
 */
public interface ProductService {

    @GET("/v1/product/asin/{asin}")
    void performSearch(@Path("asin") String asin, Callback<ProductResponse> callback);

}
