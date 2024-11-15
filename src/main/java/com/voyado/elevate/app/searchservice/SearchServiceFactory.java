package com.voyado.elevate.app.searchservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchServiceFactory {

    @Autowired
    public SearchServiceFactory() {
    }

    public List<SearchService> createSearchServices() {
        List<SearchService> searchServices = new ArrayList<>();
        searchServices.add(new GoogleSearchService());
        return searchServices;
    }
}
