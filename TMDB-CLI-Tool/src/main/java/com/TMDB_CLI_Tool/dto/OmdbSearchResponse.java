package com.TMDB_CLI_Tool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OmdbSearchResponse {
    @JsonProperty("Search")
    private List<MovieDto> search;
    @JsonProperty("totalResults")
    private String totalResults;
    @JsonProperty("Response")
    private String response;

    public OmdbSearchResponse() {
    }

    public OmdbSearchResponse(List<MovieDto> results) {
        this.search = results;
    }

    public List<MovieDto> getSearch() {
        return search;
    }

    public void setSearch(List<MovieDto> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "OmdbsearchResponse{" +
            "search=" + search +
            ", totalResults='" + totalResults + '\'' +
            ", Response='" + response + '\'' +
            '}';
    }
}
