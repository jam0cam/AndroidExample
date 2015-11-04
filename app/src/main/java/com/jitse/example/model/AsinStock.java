package com.jitse.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jitse on 4/3/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsinStock {
    public String asin;

    @JsonProperty("stock_id")
    public String stockId;
}
