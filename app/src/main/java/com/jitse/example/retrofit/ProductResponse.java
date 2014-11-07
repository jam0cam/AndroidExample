package com.jitse.example.retrofit;

/**
 * Created by jitse on 11/5/14.
 */
public class ProductResponse  extends JsonIgnorable {
    Product product;

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
