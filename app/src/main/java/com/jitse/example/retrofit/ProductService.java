package com.jitse.example.retrofit;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by jitse on 11/5/14.
 */
public interface ProductService {

    @GET("/v1/product/asin/{asin}")
    void performSearch(@Path("asin") String asin, Callback<ProductResponse> callback);


    @GET("/Product")
    Observable<PatronProductResponse> getProduct(@Query("filterOos") boolean filterOos,
                    @Query("id") String id);
}
