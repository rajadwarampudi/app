package com.voyado.elevate.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HitController {

    //@Value("${google.api.key}")
    //private String apiKey;

    //@Value("${google.cse.id}")
    //private String cseId;

    private final AppSearchAggregatorService myService;

    @Autowired
    public HitController(AppSearchAggregatorService myService) {
        this.myService = myService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/searchhits")
    public String search(@RequestParam("query") String query, Model model) {

        return myService.getSearchResult(query, model);
    }
}
