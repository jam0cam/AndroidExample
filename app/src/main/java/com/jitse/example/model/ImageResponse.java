package com.jitse.example.model;

import com.jitse.example.retrofit.BaseAPIModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jitse on 12/21/15.
 */
public class ImageResponse extends BaseAPIModel {
    public HashMap<String, ArrayList<ProductImage>> images;
}