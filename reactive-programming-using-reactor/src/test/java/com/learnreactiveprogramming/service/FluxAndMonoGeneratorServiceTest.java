package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {
    
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
    @Test
    void namesFlux() {
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();
        StepVerifier.create(namesFlux).expectNext("Mohan", "Priya", "Thuhira").verifyComplete();
        StepVerifier.create(namesFlux).expectNextCount(3).verifyComplete();
    }
}