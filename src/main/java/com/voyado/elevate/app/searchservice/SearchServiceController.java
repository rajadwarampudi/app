package com.voyado.elevate.app.searchservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchServiceController {

    @Value("${searchservice.google.api.key}")
    private String apiKey;

    @Value("${searchservice.google.cse.id}")
    private String cseId;

    @Autowired
    AppSearchAggregatorService searchAggregatorService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/searchhits")
    public String search(@RequestParam("query") String query, Model model) {

        return searchAggregatorService.getSearchResult(query, model);
    }
}