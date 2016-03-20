package com.devfreaks.tripper.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "fli_flight")
public class Flight {

    @Id
    private String id;

}
