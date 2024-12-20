package com.voyado.elevate.app.searchservice.controller;

import com.voyado.elevate.app.searchservice.services.AppSearchAggregatorService;
import com.voyado.elevate.app.searchservice.services.exception.SearchServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchServiceController {

    @Autowired
    AppSearchAggregatorService searchAggregatorService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/searchhits")
    public String search(@RequestParam("query") String query, Model model) throws SearchServiceException {

        return searchAggregatorService.getSearchResult(query, model);
    }
}
