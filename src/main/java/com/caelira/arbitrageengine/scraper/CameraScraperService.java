package com.caelira.arbitrageengine.scraper;

import com.caelira.arbitrageengine.entity.Listing;
import com.caelira.arbitrageengine.service.ListingService;
import com.caelira.arbitrageengine.service.ScraperRulesService;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class CameraScraperService {

    private final ListingService service;
    private final ScraperRulesService scraperRulesService;

    public CameraScraperService(ListingService service, ScraperRulesService scraperRulesService) {
        this.service = service;
        this.scraperRulesService = scraperRulesService;
    }

    // make this to 30 min
    @Scheduled(fixedRate = 60000)
    public void scrapeMarket() {
        System.out.println("--- WAKING UP SCRAPER ENGINE ---");
        List<ScraperRules> rulesList = scraperRulesService.getAllScraperRules();

        try (Playwright playwright = Playwright.create()) {

            // Disable the flags that tell websites this is an automated bot
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                    .setHeadless(true)
                    .setArgs(Arrays.asList("--disable-blink-features=AutomationControlled"));

            Browser browser = playwright.chromium().launch(launchOptions);

            // context that perfectly mimics a real Windows Chrome user
            Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                    .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
                    .setViewportSize(1920, 1080);

            BrowserContext context = browser.newContext(contextOptions);

            // Apply a script to permanently hide the webdriver flag before the page even loads
            context.addInitScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

            Page page = context.newPage();
            page.setDefaultTimeout(20000);

            for (ScraperRules rule : rulesList) {
                try {
                    String url = rule.getUrlText();

                    page.navigate(url);
                    page.waitForLoadState();

                    System.out.println("Target: " + url);
                    System.out.println("Page Title: " + page.title());

                    if (page.title().contains("Pardon Our Interruption") || page.title().contains("Security Measure")) {
                        System.err.println("CRITICAL: IP Temporarily blocked by WAF. Skipping rule.");
                        continue;
                    }

                    List<Locator> listing = page.locator(rule.getUnivClass()).all();
                    System.out.println("Found " + listing.size() + " raw items on page.");

                    for (Locator element : listing) {
                        try {
                            String title = element.locator(rule.getTitleClass()).first().innerText();
                            String priceText = element.locator(rule.getPriceClass()).first().innerText();
                            String urlText = element.locator(rule.getUrlClass()).first().getAttribute("href");

                            if (urlText == null) continue;

                            String cleanUrl = urlText.split("\\?")[0];

                            if (!priceText.isEmpty() && !cleanUrl.isEmpty() && !service.existsByListingUrl(cleanUrl)) {
                                String cleanPrice = priceText.replaceAll("[^0-9.]", "");

                                if (!cleanPrice.isEmpty() && cleanPrice.indexOf('.') == cleanPrice.lastIndexOf('.')) {
                                    BigDecimal convPrice = new BigDecimal(cleanPrice);

                                    Listing scraped = new Listing();
                                    scraped.setScrapedAt(LocalDateTime.now());
                                    scraped.setCameraModel(title);
                                    scraped.setListedPrice(convPrice);
                                    scraped.setListingUrl(cleanUrl);
                                    scraped.setCurrency(rule.getCurrency());
                                    scraped.setPlatformSource(rule.getPlatform());
                                    scraped.setArbitrageOpportunity(false);

                                    service.saveListing(scraped);
                                    System.out.println("SAVED: " + title + " | " + convPrice);
                                }
                            }
                        } catch (Exception innerE) {

                        }
                    }
                } catch (Exception e) {
                    System.err.println("Failed on rule: " + e.getMessage());
                }

                Thread.sleep(3000);
            }
        } catch (Exception e) {
            System.err.println("Playwright engine critical failure: " + e.getMessage());
        }
    }
}