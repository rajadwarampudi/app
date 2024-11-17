package com.voyado.elevate.app.searchservice;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class SearchUtil {
    static String getSearchValue(String url, String query) {
        String[] words = query.split(" ");
        long totalHits = 0L;
        String error  = "Error retrieving data";

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
                return error;
            }
        }

        return String.valueOf(totalHits);
    }
}
