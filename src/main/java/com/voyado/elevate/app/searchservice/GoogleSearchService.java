package com.voyado.elevate.app.searchservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.voyado.elevate.app.searchservice.SearchUtil.getSearchValue;

@Service
public class GoogleSearchService implements SearchService {
    @Value("${searchservice.google.api.key}")
    private String apiKey;

    @Value("${searchservice.google.cse.id}")
    private String cseId;

    private final SearchResult searchResult;

    public GoogleSearchService() {
        searchResult = new SearchResult(SearchServiceName.GOOGLE_SEARCH);
    }


    @Override
    public SearchResult search(String query) {

        String url = String.format(
                "https://www.googleapis.com/customsearch/v1?key=%s&cx=%s", apiKey, cseId);

        String searchValue = getSearchValue(url, query);

        searchResult.setTotalHits(String.valueOf(searchValue));
        return searchResult;
    }
}
