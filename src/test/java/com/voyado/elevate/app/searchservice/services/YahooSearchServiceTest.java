package com.voyado.elevate.app.searchservice.services;

import com.voyado.elevate.app.searchservice.services.exception.SearchServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class YahooSearchServiceTest {

    @Autowired
    private YahooSearchService yahooSearchService;

    @Test
    void testSuccessfulSearchWithSingleWordPhrase() throws SearchServiceException {
        String query = "sweden";
        SearchResult searchResult = yahooSearchService.search(query);
        assertEquals(searchResult.getSearchServiceName(), SearchServiceName.YAHOO_SEARCH.toString());
        assertNotEquals(searchResult.getTotalHits(), "0");
    }

    @Test
    void testSuccessfulSearchWithTwoWordsPhraseEndEqualsToSumOfIndividualHits() throws SearchServiceException {
        String word1 = "sweden";
        SearchResult searchResultSweden = yahooSearchService.search(word1);
        long totalHitsForWord1 = Long.parseLong(searchResultSweden.getTotalHits());

        String gibbarishWord = "sdskfdsfdfjdskfsdjflserjeafsjsdflkjsdflsdkfj";
        SearchResult searchResultIndia = yahooSearchService.search(gibbarishWord);
        long totalHitsForWord2 = Long.parseLong(searchResultIndia.getTotalHits());

        SearchResult searchResultBothWords = yahooSearchService.search(word1 + " " + gibbarishWord);
        long totalHitsForBothWords = Long.parseLong(searchResultBothWords.getTotalHits());

        assertEquals(totalHitsForBothWords, totalHitsForWord1 + totalHitsForWord2);

    }

    @Test
    void testUnsuccessfulSearchThrowsException() throws SearchServiceException {
        ReflectionTestUtils.setField(yahooSearchService, "apiKey", null);
        String query = "sweden";
        SearchServiceException exception = assertThrows(SearchServiceException.class, () -> {
            yahooSearchService.search("testQuery");
        });
        assertEquals("API key is empty for the service: " + SearchServiceName.YAHOO_SEARCH, exception.getMessage());
    }
}
