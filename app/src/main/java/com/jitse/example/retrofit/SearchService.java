package com.jitse.example.retrofit;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by jitse on 10/21/14.
 */
public interface SearchService {

    @GET("/v1/search/term/{term}")
    void performSearch(@Path("term") String term, Callback<SearchResponse> callback);

}
