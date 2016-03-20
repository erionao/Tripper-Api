package com.devfreaks.tripper.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tck_ticket")
public class Ticket {

    @Id
    private String id;

}
