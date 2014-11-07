package com.jitse.example.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jitse on 11/3/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
    String price;
    String asin;
    String originalPrice;
    String imageUrl;
    String productName;
    String brandName;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
