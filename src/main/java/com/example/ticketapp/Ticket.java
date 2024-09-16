package com.example.ticketapp;

import java.util.Map;

public class Ticket {
    private Performance performance;
    private Seat seat;

    private double ticketPrice;


    private int serial;

    private Map<String, Double> fees;
    private double tax;


    public Ticket(int serial, Performance performance, Seat seat) {
        this.serial = serial;
        this.performance = performance;
        this.seat = seat;
        this.fees = performance.getFeeByType();
        this.ticketPrice = seat.getPrice();

    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }


    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
}