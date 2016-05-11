package com.devfreaks.tripper.entities;

import com.devfreaks.tripper.entities.enums.FlightStatus;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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

    @ManyToOne
    @JoinColumn(name = "from_airport", nullable = false)
    private Airport from;

    @ManyToOne
    @JoinColumn(name = "to_airport", nullable = false)
    private Airport to;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date departure;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date arrival;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double baggageLimit;

    @Column(nullable = false)
    private FlightStatus status;

    @Column(nullable = false)
    private String gate;

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

    public Double getBaggageLimit() {
        return baggageLimit;
    }

    public void setBaggageLimit(Double baggageLimit) {
        this.baggageLimit = baggageLimit;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }
}
