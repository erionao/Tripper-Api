package com.devfreaks.tripper.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "air_airport")
public class Airport {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
