package com.shopvintage.auctions.web.controllers;

import com.shopvintage.auctions.persistence.entities.Auction;
import com.shopvintage.auctions.persistence.repositories.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("auctions")
public class AuctionsController {

    @Autowired
    private AuctionRepository auctionRepository;

    @GetMapping
    Flux<Auction> findAll(){
        return this.auctionRepository.findAll();
    }

    @PostMapping
    Mono<Auction> save(@RequestBody Auction auction ){
        return this.auctionRepository.save(auction);
    }

}
