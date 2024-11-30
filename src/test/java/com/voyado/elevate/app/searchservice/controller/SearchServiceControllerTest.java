package com.voyado.elevate.app.searchservice.controller;

import com.voyado.elevate.app.searchservice.services.AppSearchAggregatorService;
import com.voyado.elevate.app.searchservice.services.exception.SearchServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SearchServiceController.class)
class SearchServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppSearchAggregatorService myAppSearchAggregatorService;

    @Test
    void testIndexPageSuccess() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testSearchHitsSuccess() throws Exception {
        when(myAppSearchAggregatorService.getSearchResult(any(), any())).thenReturn("result");
        mockMvc.perform(post("/searchhits")
                        .param("query", "searchword"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"));
    }

    @Test
    void testSearchHitsThrowsException() throws Exception {
        String customExceptionMessage = "Set API Key";
        String expectedExceptionMessage = customExceptionMessage + ": Please follow the documentation from " +
                "README file to set API Key and CSE ID, set the values in application properties";
        when(myAppSearchAggregatorService.getSearchResult(any(), any())).thenThrow(new SearchServiceException(customExceptionMessage));
        mockMvc.perform(post("/searchhits")
                        .param("query", "searchword"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedExceptionMessage));
    }

}
