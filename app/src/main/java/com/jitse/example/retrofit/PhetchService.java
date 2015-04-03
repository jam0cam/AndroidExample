package com.jitse.example.retrofit;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by jitse on 4/3/15.
 */
public interface PhetchService {
    @POST("/item/get/asinsByStockIds")
    Observable<List<>> getAsinsByStockId(List<String> stockIds);
}
