package com.jitse.example.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by jitse on 11/3/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse {

    private List<SearchResult> results;

    public List<SearchResult> getResults() {
        return results;
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }
}
