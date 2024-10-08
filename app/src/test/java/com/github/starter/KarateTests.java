package com.github.starter;

import com.intuit.karate.junit5.Karate;

public class KarateTests {

    @Karate.Test
    Karate dummyTest() {
        return Karate.run("classpath:karate/placeholder.feature");
    }

    @Karate.Test
    Karate statusTest() {
        return Karate.run("classpath:karate/status.feature");
    }

    @Karate.Test
    Karate pokemonTest() {return Karate.run("classpath:karate/pokemon/pokemon.feature");}


}

