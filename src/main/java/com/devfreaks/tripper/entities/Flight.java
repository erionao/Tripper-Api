package com.devfreaks.tripper.entities;

import com.devfreaks.tripper.entities.enums.FlightStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "fli_flight")
public class Flight {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "from_airport", nullable = false)
    private Airport from;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "to_airport", nullable = false)
    private Airport to;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(nullable = false)
    private Date departure;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(nullable = false)
    private Date arrival;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private FlightStatus status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }
}
