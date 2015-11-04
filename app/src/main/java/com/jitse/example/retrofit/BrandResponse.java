package com.jitse.example.retrofit;

import java.util.List;

public class BrandResponse extends BaseAPIModel {
    public List<Brand> brands;

    @Override
    public String toString() {
        return "BrandResponse{" +
                "brands=" + brands +
                '}';
    }
}
