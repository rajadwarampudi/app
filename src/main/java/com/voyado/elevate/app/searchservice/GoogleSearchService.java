package com.voyado.elevate.app.searchservice;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:application.properties")
public class GoogleSearchService implements SearchService {
    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.cse.id}")
    private String cseId;

    private final SearchResult searchResult;
    private final String url;

    @Autowired
    public GoogleSearchService() {
        searchResult = new SearchResult(SearchServiceName.GOOGLE_SEARCH);
        url = String.format(
                "https://www.googleapis.com/customsearch/v1?key=%s&cx=%s", apiKey, cseId);
    }


    @Override
    public SearchResult search(String query) {
        String urlWithQuery = String.format("%s&q=%s", url, query);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(urlWithQuery, String.class);

        if (response != null) {
            JSONObject jsonResponse = new JSONObject(response);
            String totalResults = jsonResponse.getJSONObject("searchInformation")
                    .getString("totalResults");

            System.out.println("query: " + query);
            System.out.println("total results: " + totalResults);
            searchResult.setTotalHits(totalResults);
        } else {
            searchResult.setTotalHits("Error retrieving data");
        }
        return searchResult;
    }
}
