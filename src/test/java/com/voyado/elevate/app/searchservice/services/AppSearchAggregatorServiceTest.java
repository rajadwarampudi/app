package com.voyado.elevate.app.searchservice.services;

import com.voyado.elevate.app.searchservice.services.exception.SearchServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AppSearchAggregatorServiceTest {

    @InjectMocks
    private AppSearchAggregatorService appSearchAggregatorService;

    @Mock
    private SearchServiceFactory mySearchServiceFactory;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSearchResultSuccess() throws SearchServiceException {
        SearchResult googleSearchResult = createSearchResult(SearchServiceName.GOOGLE_SEARCH, "20000");
        SearchResult yahooSearchResult = createSearchResult(SearchServiceName.YAHOO_SEARCH, "10000");
        List<SearchService> searchServices = createMockSearchServices("phrase", googleSearchResult, yahooSearchResult);
        when(mySearchServiceFactory.createSearchServices())
                .thenReturn(searchServices);

        String result = appSearchAggregatorService.getSearchResult("phrase", model);

        assertEquals("result", result);
        verify(model, times(1)).addAttribute("searchResults",
                List.of(googleSearchResult, yahooSearchResult));
    }

    @Test
    void testGetSearchResultFailure() throws SearchServiceException {
        String exceptionMessage = "CSE ID is missing";
        SearchResult yahooSearchResult = createSearchResult(SearchServiceName.YAHOO_SEARCH, "10000");
        List<SearchService> searchServices = createMockSearchServicesThatThrowsException("phrase", yahooSearchResult, exceptionMessage);
        when(mySearchServiceFactory.createSearchServices())
                .thenReturn(searchServices);

        // Act & Assert: Verify that the exception is thrown and check the message
        SearchServiceException exception = assertThrows(SearchServiceException.class, () -> {
            appSearchAggregatorService.getSearchResult("phrase", model);
        });

        // Verify the exception message
        assertEquals(exceptionMessage, exception.getMessage(), "The exception message should match");
    }

    private List<SearchService> createMockSearchServices(String phrase,
                                                         SearchResult googleSearchResult,
                                                         SearchResult yahooSearchResult) throws SearchServiceException {
        SearchService googleSearchService = mock(SearchService.class);
        when(googleSearchService.search(phrase)).thenReturn(googleSearchResult);

        SearchService yahooSearchService = mock(SearchService.class);
        when(yahooSearchService.search(phrase)).thenReturn(yahooSearchResult);

        return List.of(googleSearchService, yahooSearchService);
    }

    private List<SearchService> createMockSearchServicesThatThrowsException(String phrase,
                                                         SearchResult yahooSearchResult,
                                                         String exceptionMessageForGoogleService) throws SearchServiceException {
        SearchService googleSearchService = mock(SearchService.class);
        when(googleSearchService.search(phrase)).thenThrow(new SearchServiceException(exceptionMessageForGoogleService));

        SearchService yahooSearchService = mock(SearchService.class);
        when(yahooSearchService.search(phrase)).thenReturn(yahooSearchResult);

        return List.of(googleSearchService, yahooSearchService);
    }

    private SearchResult createSearchResult(SearchServiceName serviceName, String totalHits) {
        SearchResult searchResult = new SearchResult(serviceName);
        searchResult.setTotalHits(totalHits);
        return searchResult;
    }
}
