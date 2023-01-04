package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("Mohan", "Priya", "Thuhira"));
    }

    public Mono<String> nameMono(){
        return Mono.just("Mohan Eswaran");
    }

    public static void main(String[] args) {
      FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
      fluxAndMonoGeneratorService.namesFlux().subscribe(name -> {
          System.out.println("Flux Stream name is " + name);
      });

      fluxAndMonoGeneratorService.nameMono().subscribe(name -> System.out.println("Mono Stream name is " + name));
    }
}
