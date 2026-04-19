package com.caelira.arbitrageengine.repository;

import com.caelira.arbitrageengine.scraper.ScraperRules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScraperRulesRepository extends JpaRepository<ScraperRules, Long> {

    List<ScraperRules> findAll();
}
