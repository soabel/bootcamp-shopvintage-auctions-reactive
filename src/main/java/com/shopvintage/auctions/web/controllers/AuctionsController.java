package com.shopvintage.auctions.web.controllers;

import com.shopvintage.auctions.persistence.entities.Auction;
import com.shopvintage.auctions.persistence.entities.Bid;
import com.shopvintage.auctions.persistence.repositories.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("auctions")
public class AuctionsController {

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private AuctionRepository auctionRepository;

    @GetMapping
    Flux<Auction> findAll() {
        return this.auctionRepository.findAll();
    }

    @PostMapping
    Mono<Auction> save(@RequestBody Auction auction) {

        return this.auctionRepository.save(auction);
    }

//    @PostMapping
//    Mono<String> save(@RequestBody Auction auction ){
//
//       return this.auctionRepository
//                .save(auction)
//                .map(p-> p.getId());
//    }

    @PutMapping
    Mono<Auction> update(@RequestBody Auction auction) {
        return this.auctionRepository.save(auction);
    }

    //
//    @PatchMapping
//    Mono<Auction> updatePatch(@RequestBody Auction auction ){
//        return this.auctionRepository.save(auction);
//    }
//
    @PostMapping("{id}/bids")
    void addBid(@PathVariable String id, @RequestBody Bid bid) {
        System.out.println(" addBid id = " + id);
        bid.setDate(new Date());
        bid.setAuctionId(id);
        streamBridge.send("new-output", bid);

//        return this.auctionRepository
//                .findById(id)
//                .flatMap(au -> {
//                    if (au.getBids() == null) {
//                        au.setBids(new ArrayList<>());
//                    }
//                    bid.setDate(new Date());
//                    au.getBids().add(bid);
//                    return this.auctionRepository.save(au);
//                });


//        this.auctionRepository
//                .findById(id)
//                .subscribe(au -> {
//                    if (au.getBids() == null) {
//                        au.setBids(new ArrayList<>());
//                    }
//                    bid.setDate(new Date());
//                    au.getBids().add(bid);
//                    this.auctionRepository.save(au)
//                            .subscribe(aux-> System.out.println("aux = " + aux));
//                });


    }

}
