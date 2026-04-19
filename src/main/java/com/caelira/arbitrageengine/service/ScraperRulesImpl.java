package com.caelira.arbitrageengine.service;

import com.caelira.arbitrageengine.repository.ScraperRulesRepository;
import com.caelira.arbitrageengine.scraper.ScraperRules;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScraperRulesImpl implements ScraperRulesService{

    private final ScraperRulesRepository  repository;

    public ScraperRulesImpl(ScraperRulesRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<ScraperRules> getAllScraperRules() {
        return repository.findAll();
    }

    @Override
    public void saveRule(ScraperRules rules) {
        repository.save(rules);
    }
}
