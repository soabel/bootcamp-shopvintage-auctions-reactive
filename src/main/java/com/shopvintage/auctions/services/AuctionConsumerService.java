package com.shopvintage.auctions.services;

import com.shopvintage.auctions.persistence.entities.Bid;
import com.shopvintage.auctions.persistence.repositories.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;

@Service
public class AuctionConsumerService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Bean
    public Consumer<Bid> bidConsumer() {
        return (bid) -> {
            System.out.println("bidConsumer - message");
            System.out.println(bid.toString());

            this.auctionRepository
                    .findById(bid.getAuctionId())
                    .flatMap(au -> {
                        if (au.getBids() == null) {
                            au.setBids(new ArrayList<>());
                        }
                        bid.setDate(new Date());
                        au.getBids().add(bid);
                        return this.auctionRepository.save(au);
                    }).subscribe(au -> System.out.println("auction updated = " + au));

        };
    }

}
