package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("Mohan", "Priya", "Thuhira"));
    }

    public Mono<String> nameMono() {
        return Mono.just("Mohan Eswaran");
    }

    public Flux<String> namesFluxMap() {
        return Flux.fromIterable(List.of("Mohan", "Priya", "Thuhira")).map(String::toUpperCase).log();
    }

    public Flux<String> namesFluxMap_Immutability() {
        var namesFlux = Flux.fromIterable(List.of("Mohan", "Priya", "Thuhira"));
        return namesFlux.map(String::toUpperCase);
        // return namesFlux - This is will fail because each operation returns new flux
    }

    public Flux<String> namesFlux_Map_filter(int StringLength) {
        return Flux.fromIterable(List.of("Mohan", "Priya", "Thuhira")).map(String::toUpperCase).
                filter(name -> name.length() > StringLength).
                map(modified_name -> modified_name.length() + "-" + modified_name).
                log();
    }

    public Flux<String> namesFlux_FlatMap(int StringLength) {
        return Flux.fromIterable(List.of("Mohan", "Priya", "Thuhira")).map(String::toUpperCase).
                filter(name -> name.length() > StringLength).flatMap(name -> splitString(name)).log();
    }

    public Flux<String> splitString(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    public Flux<String> namesFlux_FlatMap_Async(int StringLength) {
        return Flux.fromIterable(List.of("Mohan", "Priya", "Thuhira")).map(String::toUpperCase).
                filter(name -> name.length() > StringLength).flatMap(name -> splitString_withdelay(name)).log();
    }

    public Flux<String> splitString_withdelay(String name) {
        var charArray = name.split("");
//        var delay = new Random().nextInt(1000);
        var delay = 1000;
        return Flux.fromArray(charArray).delayElements(Duration.ofMillis(delay));
    }

    // Concat Map - Ordering is preserved compare to FlatMap
    public Flux<String> namesFlux_ConcatMap(int StringLength) {
        return Flux.fromIterable(List.of("Mohan", "Priya", "Thuhira", "Eswaran")).map(String::toUpperCase).
                filter(name -> name.length() > StringLength).concatMap(name -> splitString_withdelay(name)).log();
    }

    public Mono<List<String>> namesMono_flatMap(int StringLength) {
        return Mono.just("Thuhira").map(String::toUpperCase).filter(name -> name.length() > StringLength).
                flatMap(name -> splitnameMono(name)).log();
    }

    public Mono<List<String>> splitnameMono(String name) {
        var charArray = name.split("");
        return Mono.just(List.of(charArray));
    }

    public Flux<String> namesMono_flatMapMany(int StringLength) {
        return Mono.just("Thuhira").map(String::toUpperCase).filter(name -> name.length() > StringLength).
                flatMapMany(name -> splitString(name)).log();
    }

    public Flux<String> namesFlux_Transform(int StringLength) {

        Function<Flux<String>, Flux<String>> nameTransformed = name -> name.map(String::toUpperCase).filter(s -> s.length() > StringLength);

        return Flux.fromIterable(List.of("Mohan", "Priya", "Thuhira")).
                transform(nameTransformed).
                flatMap(name -> splitString(name)).
                defaultIfEmpty("Hello").
                log();
    }

    public Flux<String> namesFlux_Transform_SwitchIfEmpty(int StringLength) {

        Function<Flux<String>, Flux<String>> nameTransformed = name -> name.map(String::toUpperCase).
                filter(s -> s.length() > StringLength).flatMap(split_name -> splitString(split_name));

        var defaultFlux = Flux.just("MohanEswaran").transform(nameTransformed);

        return Flux.fromIterable(List.of("Mohan", "Priya", "Thuhira")).
                transform(nameTransformed).
                switchIfEmpty(defaultFlux).
                log();
    }

    public Flux<String> explore_concat(){
        var abcFlux = Flux.just("A","B","C");
        var defFlux = Flux.just("D","E","F");

        return Flux.concat(abcFlux,defFlux).log();
    }

    public Flux<String> explore_concatWith(){
        var abcFlux = Flux.just("A","B","C");
        var defFlux = Flux.just("D","E","F");

        return abcFlux.concatWith(defFlux).log();
    }

    public Flux<String> mono_concatWith(){
        var aMono = Mono.just("A");
        var dMono = Mono.just("D");

        return aMono.concatWith(dMono).log();
    }

    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
        fluxAndMonoGeneratorService.namesFlux().subscribe(name -> {
            System.out.println("Flux Stream name is " + name);
        });

        fluxAndMonoGeneratorService.nameMono().subscribe(name -> System.out.println("Mono Stream name is " + name));
    }
}
