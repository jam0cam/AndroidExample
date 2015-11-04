package com.jitse.example.retrofit;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.text.TextWatcher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jitse.example.BR;
import com.jitse.example.views.SimpleTextWatcher;

import java.util.List;

/**
 * Created by jitse on 11/5/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends BaseObservable {
    public String onSale;
    public Review reviews;
    public String description;
    public String defaultProductType;
    public List<Style> styles;
    public List<String> genders;

    @Bindable
    public String productName;

    @Bindable
    public String brandName;

    @Bindable
    public String defaultImageUrl;
    public String productId;

    public String getOnSale() {
        return onSale;
    }

    public void setOnSale(String onSale) {
        this.onSale = onSale;
    }

    public Review getReviews() {
        return reviews;
    }

    public void setReviews(Review reviews) {
        this.reviews = reviews;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultProductType() {
        return defaultProductType;
    }

    public void setDefaultProductType(String defaultProductType) {
        this.defaultProductType = defaultProductType;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    public List<String> getGenders() {
        return genders;
    }

    public void setGenders(List<String> genders) {
        this.genders = genders;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        notifyPropertyChanged(BR.productName);
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
        notifyPropertyChanged(BR.brandName);
    }

    public String getDefaultImageUrl() {
        return defaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
        notifyPropertyChanged(BR.defaultImageUrl);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public TextWatcher getProductNameTextWatcher() {
        return new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                setProductName(s.toString());
            }
        };
    }
}
