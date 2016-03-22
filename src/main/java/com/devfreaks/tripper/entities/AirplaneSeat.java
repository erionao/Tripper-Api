package com.devfreaks.tripper.entities;

import com.devfreaks.tripper.entities.enums.SeatType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "air_seat")
public class AirplaneSeat {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @Column(name = "type", nullable = false)
    private SeatType type;

}
