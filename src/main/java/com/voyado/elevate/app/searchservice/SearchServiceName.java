package com.voyado.elevate.app.searchservice;

public enum SearchServiceName {
    GOOGLE_SEARCH("GoogleSearch"),
    YAHOO_SEARCH("YahooSearch");

    private final String name;
    private SearchServiceName(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
