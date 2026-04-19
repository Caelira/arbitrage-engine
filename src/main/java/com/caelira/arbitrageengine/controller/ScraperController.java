package com.caelira.arbitrageengine.controller;

import com.caelira.arbitrageengine.entity.Listing;
import com.caelira.arbitrageengine.scraper.ScraperRules;
import com.caelira.arbitrageengine.service.ListingService;
import com.caelira.arbitrageengine.service.ScraperRulesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ScraperController {

    private final ScraperRulesService scraperRulesService;
    private final ListingService listingService;

    public ScraperController(ScraperRulesService scraperRulesService, ListingService listingService) {
        this.scraperRulesService = scraperRulesService;
        this.listingService = listingService;
    }

    @GetMapping("/createARule")
    public String showCreateRulePage(Model model){
        model.addAttribute("rule", new ScraperRules());
        model.addAttribute("allRules", scraperRulesService.getAllScraperRules());
        return "rule-creation";
    }

    @PostMapping("/saveRule")
    public String createNewRule(@ModelAttribute("rule") ScraperRules rules){

        scraperRulesService.saveRule(rules);

        return "redirect:/index";
    }

    @GetMapping("/deals")
    public String showLiveDeals(Model model) {
        List<Listing> recentListings = listingService.getAllListings();
        model.addAttribute("deals", recentListings);

        return "deals";
    }
}
