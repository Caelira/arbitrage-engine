package com.caelira.arbitrageengine.service;

import com.caelira.arbitrageengine.scraper.ScraperRules;

import java.util.List;

public interface ScraperRulesService {

    public List<ScraperRules> getAllScraperRules();
    public void saveRule(ScraperRules rules);
}
