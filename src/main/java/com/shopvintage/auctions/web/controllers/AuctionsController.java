package com.shopvintage.auctions.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("auctions")
public class AuctionsController {

    @GetMapping
    Mono<String> findAll(){
        return Mono.just("Hello");
    }

}
