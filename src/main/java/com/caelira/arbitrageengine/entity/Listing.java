package com.caelira.arbitrageengine.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "listing")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listingId;

    @Column(name = "camera_model", nullable = false)
    private String cameraModel;
    @Column(name = "platform_source", nullable = false)
    private String platformSource;
    @Column(name = "listed_price", nullable = false)
    private BigDecimal listedPrice;
    @Column(name = "currency", nullable = false)
    private String currency;
    @Column(name = "listing_url", nullable = false)
    private String listingUrl;
    @Column(name = "scraped_at", nullable = false)
    private LocalDateTime scrapedAt;
    @Column(name = "is_arbitrage_opportunity", nullable = false)
    private Boolean isArbitrageOpportunity;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "raw_payload", columnDefinition = "jsonb")
    private String rawPayload;

    public Listing() {
    }

    public Listing(Long listingId, String cameraModel, String platformSource, BigDecimal listedPrice, String currency, String listingUrl, LocalDateTime scrapedAt, Boolean isArbitrageOpportunity, String rawPayload) {
        this.listingId = listingId;
        this.cameraModel = cameraModel;
        this.platformSource = platformSource;
        this.listedPrice = listedPrice;
        this.currency = currency;
        this.listingUrl = listingUrl;
        this.scrapedAt = scrapedAt;
        this.isArbitrageOpportunity = isArbitrageOpportunity;
        this.rawPayload = rawPayload;
    }

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public String getPlatformSource() {
        return platformSource;
    }

    public void setPlatformSource(String platformSource) {
        this.platformSource = platformSource;
    }

    public BigDecimal getListedPrice() {
        return listedPrice;
    }

    public void setListedPrice(BigDecimal listedPrice) {
        this.listedPrice = listedPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getListingUrl() {
        return listingUrl;
    }

    public void setListingUrl(String listingUrl) {
        this.listingUrl = listingUrl;
    }

    public LocalDateTime getScrapedAt() {
        return scrapedAt;
    }

    public void setScrapedAt(LocalDateTime scrapedAt) {
        this.scrapedAt = scrapedAt;
    }

    public Boolean getArbitrageOpportunity() {
        return isArbitrageOpportunity;
    }

    public void setArbitrageOpportunity(Boolean arbitrageOpportunity) {
        isArbitrageOpportunity = arbitrageOpportunity;
    }

    public String getRawPayload() {
        return rawPayload;
    }

    public void setRawPayload(String rawPayload) {
        this.rawPayload = rawPayload;
    }
}
