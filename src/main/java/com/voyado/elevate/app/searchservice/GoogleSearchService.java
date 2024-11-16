package com.voyado.elevate.app.searchservice;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleSearchService implements SearchService {
    @Value("${searchservice.google.api.key}")
    private String apiKey;

    @Value("${searchservice.google.cse.id}")
    private String cseId;

    private final SearchResult searchResult;
    private final String url;

    public GoogleSearchService() {
        searchResult = new SearchResult(SearchServiceName.GOOGLE_SEARCH);
        url = String.format(
                "https://www.googleapis.com/customsearch/v1?key=%s&cx=%s", apiKey, cseId);
    }


    @Override
    public SearchResult search(String query) {
        String[] words = query.split(" ");
        long totalHits = 0L;
        for(String word : words) {
            String urlWithQuery = String.format("%s&q=%s", url, word);
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(urlWithQuery, String.class);

            if (response != null) {
                JSONObject jsonResponse = new JSONObject(response);
                String totalResults = jsonResponse.getJSONObject("searchInformation")
                        .getString("totalResults");

                totalHits += Long.parseLong(totalResults);
            } else {
                searchResult.setTotalHits("Error retrieving data");
                return searchResult;
            }
        }

        searchResult.setTotalHits(String.valueOf(totalHits));
        return searchResult;
    }
}
