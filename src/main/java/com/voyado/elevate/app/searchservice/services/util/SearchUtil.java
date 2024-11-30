package com.voyado.elevate.app.searchservice.services.util;

import com.voyado.elevate.app.searchservice.services.exception.SearchServiceException;
import com.voyado.elevate.app.searchservice.services.SearchServiceName;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class SearchUtil {
    public static String getSearchValue(SearchServiceName serviceName, String url, String query)
            throws SearchServiceException {
        String[] words = query.split(" ");
        long totalHits = 0L;

        for(String word : words) {
            String response = getResponse(url, word);
            if (response != null) {
                JSONObject jsonResponse = new JSONObject(response);
                String totalResults = jsonResponse.getJSONObject("searchInformation")
                        .getString("totalResults");

                totalHits += Long.parseLong(totalResults);
            } else {
                throw new SearchServiceException("Search response is empty for the service: " + serviceName.toString());
            }
        }

        return String.valueOf(totalHits);
    }

    private static String getResponse(String url, String word) throws SearchServiceException {
        String urlWithQuery = String.format("%s&q=%s", url, word);
        RestTemplate restTemplate = new RestTemplate();
        String response;
        try {
            response = restTemplate.getForObject(urlWithQuery, String.class);
        } catch (Exception exception) {
            throw new SearchServiceException(exception);
        }

        return response;
    }
}
