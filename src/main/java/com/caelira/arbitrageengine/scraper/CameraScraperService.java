package com.caelira.arbitrageengine.scraper;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Service
public class CameraScraperService {

    @Scheduled(fixedRate = 10000)
    public void scrapeMarket(){
    //scrape every 10 seconds -> lessen if too much
        try{

            String url = "https://www.ebay.com/sch/i.html?_nkw=sony+cyber-shot+dsc-p5";
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .header("Accept-Language", "en-US,en;q=0.9")
                    .get();
            System.out.println("Scraper executed successfully!");
            System.out.println("Page Title: " + doc.title());
        }catch (Exception e){
            System.err.println("Scraping failed. Error: " + e.getMessage());
        }

    }

}
