package com.voyado.elevate.app.searchservice.services;

import com.voyado.elevate.app.searchservice.services.exception.SearchServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchServiceFactoryTest {

    @InjectMocks
    private SearchServiceFactory mySearchServiceFactory;

    @Mock
    private GoogleSearchService myGoogleSearchService;

    @Mock
    private YahooSearchService myYahooSearchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSearchServices() {
        List<SearchService> searchServices = mySearchServiceFactory.createSearchServices();
        assertEquals(searchServices, List.of(myGoogleSearchService, myYahooSearchService));
    }
}
