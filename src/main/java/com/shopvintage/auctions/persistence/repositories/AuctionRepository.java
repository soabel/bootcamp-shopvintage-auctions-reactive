package com.shopvintage.auctions.persistence.repositories;

import com.shopvintage.auctions.persistence.entities.Auction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AuctionRepository extends ReactiveMongoRepository<Auction, String> {

}
