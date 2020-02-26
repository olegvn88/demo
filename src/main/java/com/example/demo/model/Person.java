package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Person {
    private UUID id;

    //    @NotBlank
    private final String name;

    private final String country;

    private final String email;



//    public Person(@JsonProperty("id") UUID id,
//                  @JsonProperty("name") String name) {
//        this.id = id;
//        this.name = name;
//        this.country = null;
//    }
    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("country") String country,
                  @JsonProperty("email") String email) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.email = email;
    }

//    public Person(@JsonProperty("name") String name,
//                  @JsonProperty("country") String country,
//                  @JsonProperty("email") String email) {
//        this(null, name, country, email);
//    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }
}
