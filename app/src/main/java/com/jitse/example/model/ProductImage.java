package com.jitse.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by jitse on 12/21/15.
 */

@JsonIgnoreProperties({"imageId", "format"})
public class ProductImage implements Serializable {
    public String type;

    public String filename;

    @JsonIgnore
    public String originalImageUrl;  // The image url we started with, when we switch to high res images
    public String highResImageUrl;

    public String recipeName;
    public String styleId;

    @Override
    public String toString() {
        return filename;
    }

}
