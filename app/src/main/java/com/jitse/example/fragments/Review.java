package com.jitse.example.fragments;

import com.jitse.example.retrofit.BaseAPIModel;

/**
 * Created by jitse on 12/3/14.
 */
public class Review extends BaseAPIModel {
    public String id;
    public String date;
    public String name;
    public String location;
    public String otherShoes;
    public String summary;
    public String shoeSize;
    public String shoeWidth;
    public String shoeArch;
    public String overallRating;
    public String comfortRating;
    public String lookRating;

    public Review(String date, String name, String location, String summary, String overallRating) {
        this.date = date;
        this.name = name;
        this.summary = summary;
        this.overallRating = overallRating;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;

        Review review = (Review) o;

        if( comfortRating != null ? !comfortRating.equals(review.comfortRating) : review.comfortRating != null )
            return false;
        if( date != null ? !date.equals(review.date) : review.date != null ) return false;
        if( id != null ? !id.equals(review.id) : review.id != null ) return false;
        if( location != null ? !location.equals(review.location) : review.location != null ) return false;
        if( lookRating != null ? !lookRating.equals(review.lookRating) : review.lookRating != null ) return false;
        if( name != null ? !name.equals(review.name) : review.name != null ) return false;
        if( otherShoes != null ? !otherShoes.equals(review.otherShoes) : review.otherShoes != null ) return false;
        if( overallRating != null ? !overallRating.equals(review.overallRating) : review.overallRating != null )
            return false;
        if( shoeArch != null ? !shoeArch.equals(review.shoeArch) : review.shoeArch != null ) return false;
        if( shoeSize != null ? !shoeSize.equals(review.shoeSize) : review.shoeSize != null ) return false;
        if( shoeWidth != null ? !shoeWidth.equals(review.shoeWidth) : review.shoeWidth != null ) return false;
        if( summary != null ? !summary.equals(review.summary) : review.summary != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (otherShoes != null ? otherShoes.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (shoeSize != null ? shoeSize.hashCode() : 0);
        result = 31 * result + (shoeWidth != null ? shoeWidth.hashCode() : 0);
        result = 31 * result + (shoeArch != null ? shoeArch.hashCode() : 0);
        result = 31 * result + (overallRating != null ? overallRating.hashCode() : 0);
        result = 31 * result + (comfortRating != null ? comfortRating.hashCode() : 0);
        result = 31 * result + (lookRating != null ? lookRating.hashCode() : 0);
        return result;
    }
}
