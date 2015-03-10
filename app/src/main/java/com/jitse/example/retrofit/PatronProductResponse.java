package com.jitse.example.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by jitse on 3/9/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatronProductResponse {
    public List<Product> product;
}
