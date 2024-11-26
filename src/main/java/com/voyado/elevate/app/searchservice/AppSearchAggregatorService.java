package com.voyado.elevate.app.searchservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppSearchAggregatorService {

    @Autowired
    SearchServiceFactory searchServiceFactory;

    public String getSearchResult(String query, Model model) {

        List<SearchService> searchServices = searchServiceFactory.createSearchServices();

        List<SearchResult> searchResults = new ArrayList<>();

        for(SearchService searchService : searchServices) {
            searchResults.add(searchService.search(query));
        }
        model.addAttribute("searchResults", searchResults);

        return "result";
    }
}
