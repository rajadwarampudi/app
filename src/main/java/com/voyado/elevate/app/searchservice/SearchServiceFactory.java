package com.voyado.elevate.app.searchservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchServiceFactory {

    private final GoogleSearchService googleSearchService;
    public SearchServiceFactory(GoogleSearchService googleSearchService) {
        this.googleSearchService = googleSearchService;
    }

    public List<SearchService> createSearchServices() {
        List<SearchService> searchServices = new ArrayList<>();
        searchServices.add(googleSearchService);
        return searchServices;
    }
}
