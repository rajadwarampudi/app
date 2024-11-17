package com.voyado.elevate.app.searchservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceFactory {

    @Autowired
    GoogleSearchService googleSearchService;

    @Autowired
    YahooSearchService yahooSearchService;

    public List<SearchService> createSearchServices() {
        List<SearchService> searchServices = new ArrayList<>();
        searchServices.add(googleSearchService);
        searchServices.add(yahooSearchService);
        return searchServices;
    }
}
