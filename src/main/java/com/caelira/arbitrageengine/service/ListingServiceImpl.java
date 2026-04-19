package com.caelira.arbitrageengine.service;

import com.caelira.arbitrageengine.entity.Listing;
import com.caelira.arbitrageengine.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingServiceImpl implements ListingService{
    private ListingRepository repository;

    public ListingServiceImpl(ListingRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveListing(Listing listing) {
        repository.save(listing);
    }

    @Override
    public boolean existsByListingUrl(String url) {
        return repository.existsByListingUrl(url);
    }

    @Override
    public List<Listing> getAllListings() {
        return repository.findAll();
    }
}
