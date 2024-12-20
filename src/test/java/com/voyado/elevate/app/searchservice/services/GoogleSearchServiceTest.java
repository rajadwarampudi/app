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
public class GoogleSearchServiceTest {

    @Autowired
    private GoogleSearchService googleSearchService;

    @Test
    void testSuccessfulSearchWithSingleWordPhrase() throws SearchServiceException {
        String query = "sweden";
        SearchResult searchResult = googleSearchService.search(query);
        assertEquals(searchResult.getSearchServiceName(), SearchServiceName.GOOGLE_SEARCH.toString());
        assertNotEquals(searchResult.getTotalHits(), "0");
    }

    @Test
    void testSuccessfulSearchWithTwoWordsPhraseEndEqualsToSumOfIndividualHits() throws SearchServiceException {
        String word1 = "sweden";
        SearchResult searchResultSweden = googleSearchService.search(word1);
        long totalHitsForWord1 = Long.parseLong(searchResultSweden.getTotalHits());

        String gibbarishWord = "sdskfdsfdfjdskfsdjflserjeafsjsdflkjsdflsdkfj";
        SearchResult searchResultIndia = googleSearchService.search(gibbarishWord);
        long totalHitsForWord2 = Long.parseLong(searchResultIndia.getTotalHits());

        SearchResult searchResultBothWords = googleSearchService.search(word1 + " " + gibbarishWord);
        long totalHitsForBothWords = Long.parseLong(searchResultBothWords.getTotalHits());

        assertEquals(totalHitsForBothWords, totalHitsForWord1 + totalHitsForWord2);

    }

    @Test
    void testUnsuccessfulSearchThrowsException() throws SearchServiceException {
        ReflectionTestUtils.setField(googleSearchService, "apiKey", null);
        String query = "sweden";
        SearchServiceException exception = assertThrows(SearchServiceException.class, () -> {
            googleSearchService.search("testQuery");
        });
        assertEquals("API key is empty for the service: " + SearchServiceName.GOOGLE_SEARCH, exception.getMessage());
    }
}
