package com.jitse.example.zappos;



import java.util.List;

/**
 * Created by jitse on 11/5/14.
 */
public class Product extends JsonIgnorable {
    public String onSale;
    public Review reviews;
    public String description;
    public String defaultProductType;
    public List<Style> styles;
    public List<String> genders;
    public String productName;
    public String brandName;
    public String defaultImageUrl;
    public String productId;
}
