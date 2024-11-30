package com.voyado.elevate.app.searchservice.services;

public class SearchResult {
    private final SearchServiceName searchServiceName;
    private String totalHits;

    public SearchResult(SearchServiceName searchServiceName) {
        this.searchServiceName = searchServiceName;
    }


    public String getSearchServiceName() {
        return searchServiceName.toString();
    }

    public String getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(String totalHits) {
        this.totalHits = totalHits;
    }
}
