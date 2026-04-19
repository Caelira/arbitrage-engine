package com.caelira.arbitrageengine.repository;

import com.caelira.arbitrageengine.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Long> {

    boolean existsByListingUrl(String listingUrl);
}
