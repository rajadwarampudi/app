package com.voyado.elevate.app;

import com.voyado.elevate.app.searchservice.SearchResult;
import com.voyado.elevate.app.searchservice.SearchService;
import com.voyado.elevate.app.searchservice.SearchServiceFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppSearchAggregatorService {

    public String getSearchResult(String query, Model model) {

        SearchServiceFactory searchServiceFactory = new SearchServiceFactory();
        List<SearchService> searchServices = searchServiceFactory.createSearchServices();

        List<SearchResult> searchResults = new ArrayList<>();

        for(SearchService searchService : searchServices) {
            searchResults.add(searchService.search(query));
        }
        model.addAttribute("searchResults", searchResults);

        return "result";
    }
}
