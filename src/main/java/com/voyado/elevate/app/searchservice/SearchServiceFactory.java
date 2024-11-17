package com.voyado.elevate.app.searchservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceFactory {

    @Value("${searchservice.google.api.key}")
    private String apiKey;

    @Value("${searchservice.google.cse.id}")
    private String cseId;

    @Autowired
    GoogleSearchService googleSearchService;

    public List<SearchService> createSearchServices() {
        List<SearchService> searchServices = new ArrayList<>();
        searchServices.add(googleSearchService);
        return searchServices;
    }
}
