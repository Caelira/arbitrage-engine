package com.caelira.arbitrageengine.service;

import com.caelira.arbitrageengine.entity.Listing;

import java.util.List;

public interface ListingService {

    public void saveListing(Listing listing);
    public boolean existsByListingUrl(String url);
    public List<Listing> getAllListings();
}
