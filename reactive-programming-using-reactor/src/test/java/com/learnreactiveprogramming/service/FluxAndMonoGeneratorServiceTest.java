package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();
        StepVerifier.create(namesFlux).expectNext("Mohan", "Priya", "Thuhira").verifyComplete();
        StepVerifier.create(namesFlux).expectNextCount(3).verifyComplete();
    }

    @Test
    void namesFluxMap() {

        var namesFluxMap = fluxAndMonoGeneratorService.namesFluxMap();
        StepVerifier.create(namesFluxMap).
                expectNext("MOHAN", "PRIYA", "THUHIRA").verifyComplete();
    }

    @Test
    void namesFluxMap_Immutability() {
        var namesFlux_Immutable = fluxAndMonoGeneratorService.namesFluxMap_Immutability();
        StepVerifier.create(namesFlux_Immutable).
                expectNext("MOHAN", "PRIYA", "THUHIRA").verifyComplete();
    }

    @Test
    void namesFlux_Map_filter() {
        var StringLength = 5;
        var namesFluxMapFilter = fluxAndMonoGeneratorService.namesFlux_Map_filter(StringLength);
        StepVerifier.create(namesFluxMapFilter).expectNext("7-THUHIRA").verifyComplete();
    }

    @Test
    void namesFlux_FlatMap() {
//        var name = "THUHIRA";
//        var charArray = name.split("");
//        System.out.println(Arrays.toString(charArray));
        var StringLength = 5;
        var namesFluxFlatMap = fluxAndMonoGeneratorService.namesFlux_FlatMap(StringLength);
        StepVerifier.create(namesFluxFlatMap).expectNext("T", "H", "U", "H", "I", "R", "A").verifyComplete();
    }

    @Test
    void namesFlux_FlatMap_Async() {
        var StringLength = 5;
        var namesFlux_flatMap_async = fluxAndMonoGeneratorService.namesFlux_FlatMap_Async(StringLength);
        StepVerifier.create(namesFlux_flatMap_async).expectNext("T", "H", "U", "H", "I", "R", "A").verifyComplete();
    }

    @Test
    void namesFlux_ConcatMap() {
        var StringLength = 5;
        var namesFlux_concatMap = fluxAndMonoGeneratorService.namesFlux_ConcatMap(StringLength);
        StepVerifier.create(namesFlux_concatMap).expectNext("T", "H", "U", "H", "I", "R", "A","E","S","W","A","R","A","N").verifyComplete();
    }

    @Test
    void namesMono_flatMap() {
        var StringLength = 5;
        var namesMono_flatMap = fluxAndMonoGeneratorService.namesMono_flatMap(StringLength);
        StepVerifier.create(namesMono_flatMap).expectNext(List.of("T","H","U","H","I","R","A")).verifyComplete();
    }

    @Test
    void namesMono_flatMapMany() {
        var StringLength = 5;
        var namesMonoFlatMapMany = fluxAndMonoGeneratorService.namesMono_flatMapMany(StringLength);
        StepVerifier.create(namesMonoFlatMapMany).expectNext("T", "H", "U", "H", "I", "R", "A").verifyComplete();
    }

    @Test
    void namesFlux_Transform() {
        var StringLength = 5;
        var namesFlux_Transform = fluxAndMonoGeneratorService.namesFlux_Transform(StringLength);
        StepVerifier.create(namesFlux_Transform).expectNext("T", "H", "U", "H", "I", "R", "A").verifyComplete();
    }

    @Test
    void namesFlux_Transform_negative() {
        var StringLength = 8;
        var namesFlux_Transform = fluxAndMonoGeneratorService.namesFlux_Transform(StringLength);
        StepVerifier.create(namesFlux_Transform).expectNext("Hello").verifyComplete();
    }

    @Test
    void namesFlux_Transform_SwitchIfEmpty() {
        var StringLength = 8;
        var namesFlux_transform_switchIfEmpty = fluxAndMonoGeneratorService.namesFlux_Transform_SwitchIfEmpty(StringLength);
        StepVerifier.create(namesFlux_transform_switchIfEmpty).expectNext("M","O","H","A","N","E","S","W","A","R","A","N").verifyComplete();
    }

    @Test
    void explore_concat() {
        var concatFlux = fluxAndMonoGeneratorService.explore_concat();
        StepVerifier.create(concatFlux).expectNext("A","B","C","D","E","F").verifyComplete();
    }

    @Test
    void explore_concatWith() {
        var concatWithFlux = fluxAndMonoGeneratorService.explore_concatWith();
        StepVerifier.create(concatWithFlux).expectNext("A","B","C","D","E","F").verifyComplete();
    }

    @Test
    void mono_concatWith() {
        var concatWithMono = fluxAndMonoGeneratorService.mono_concatWith();
        StepVerifier.create(concatWithMono).expectNext("A","D").verifyComplete();
    }
}