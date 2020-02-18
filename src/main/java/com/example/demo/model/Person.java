package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Person {
    private final UUID id;

    //    @NotBlank
    private final String name;

    private final String country;

//    public Person(@JsonProperty("id") UUID id,
//                  @JsonProperty("name") String name) {
//        this.id = id;
//        this.name = name;
//        this.country = null;
//    }

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("country") String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
