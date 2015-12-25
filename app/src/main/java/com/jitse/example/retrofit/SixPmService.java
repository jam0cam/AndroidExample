package com.jitse.example.retrofit;

import com.jitse.example.model.ImageResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by jitse on 12/21/15.
 */
public interface SixPmService {

    @GET("/mobileapi/v2/images")
    void getImages(@Query("productId") String productId,
                                        @Query("key") String apiKey,
                                        @Query("recipe") String recipe,
                                    Callback<ImageResponse> cb);
}
