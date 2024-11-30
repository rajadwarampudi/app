package com.voyado.elevate.app.searchservice.services;

import com.voyado.elevate.app.searchservice.services.exception.SearchServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.voyado.elevate.app.searchservice.services.util.SearchUtil.getSearchValue;

@Service
public class YahooSearchService implements SearchService {
    @Value("${searchservice.yahoo.api.key}")
    private String apiKey;

    @Value("${searchservice.cse.id}")
    private String cseId;

    private final SearchResult searchResult;

    public YahooSearchService() {
        searchResult = new SearchResult(SearchServiceName.YAHOO_SEARCH);
    }


    @Override
    public SearchResult search(String query) throws SearchServiceException {

        if(apiKey == null) {
            throw new SearchServiceException("API key is empty for the service: " + SearchServiceName.YAHOO_SEARCH);
        }

        if(cseId == null) {
            throw new SearchServiceException("cseId is empty for the service: " + SearchServiceName.YAHOO_SEARCH);
        }

        String url = String.format(
                "https://www.googleapis.com/customsearch/v1?key=%s&cx=%s", apiKey, cseId);

        String searchValue = getSearchValue(SearchServiceName.GOOGLE_SEARCH, url, query);

        searchResult.setTotalHits(searchValue);
        return searchResult;
    }
}
