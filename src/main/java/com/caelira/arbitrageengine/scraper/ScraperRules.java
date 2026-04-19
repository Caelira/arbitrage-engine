package com.caelira.arbitrageengine.scraper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ScraperRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleClass;
    private String urlText; //url to scrape
    private String currency; // currency
    private String platform;
    private String univClass; //class to search
    private String priceClass; // class for price
    private String urlClass; // class for url



    public ScraperRules() {
    }

    public ScraperRules(String titleClass, String urlText, String currency, String platform, String univClass, String priceClass, String urlClass) {
        this.titleClass = titleClass;
        this.urlText = urlText;
        this.currency = currency;
        this.platform = platform;
        this.univClass = univClass;
        this.priceClass = priceClass;
        this.urlClass = urlClass;
    }

    public ScraperRules(Long id, String titleClass, String urlText, String currency, String platform, String univClass, String priceClass, String urlClass) {
        this.id = id;
        this.titleClass = titleClass;
        this.urlText = urlText;
        this.currency = currency;
        this.platform = platform;
        this.univClass = univClass;
        this.priceClass = priceClass;
        this.urlClass = urlClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUnivClass() {
        return univClass;
    }

    public void setUnivClass(String univClass) {
        this.univClass = univClass;
    }

    public String getPriceClass() {
        return priceClass;
    }

    public void setPriceClass(String priceClass) {
        this.priceClass = priceClass;
    }

    public String getUrlClass() {
        return urlClass;
    }

    public void setUrlClass(String urlClass) {
        this.urlClass = urlClass;
    }

    public String getTitleClass() {
        return titleClass;
    }

    public void setTitleClass(String titleClass) {
        this.titleClass = titleClass;
    }

    public String getUrlText() {
        return urlText;
    }

    public void setUrlText(String urlText) {
        this.urlText = urlText;
    }
}

//String title = element.select(".s-card__title").text();
//String priceText = element.select(".s-card__price").text();
//String urlText = element.select(".s-card__link").attr("href");
