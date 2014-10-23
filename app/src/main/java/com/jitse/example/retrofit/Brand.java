package com.jitse.example.retrofit;


public class Brand extends BaseAPIModel {
    public String name;
    public int id;
    public int realBrandId;
    public String cleanName;
    public String headerImageUrl;
    public String imageUrl;
    public String brandUrl;

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", realBrandId=" + realBrandId +
                '}';
    }
}