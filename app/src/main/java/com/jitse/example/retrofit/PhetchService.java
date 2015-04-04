package com.jitse.example.retrofit;

import com.jitse.example.model.AsinStock;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by jitse on 4/3/15.
 */
public interface PhetchService {
    @POST("/item/get/asinsByStockIds")
    Observable<List<AsinStock>> getAsinsByStockId(@Body List<String> stockIds);
}
