package com.example.ticketapp;

public class Seat {
    private String row;
    private String seatNumber;
    private String holdStatus;

    private int seatLocation;

    private boolean isPadding;
    private int tier;

    private double price;

    public Seat(String row, String seatNumber, String holdStatus, int seatLocation, boolean isPadding){
        this.row = row;
        this.seatNumber = seatNumber;
        this.holdStatus = holdStatus;
        this.seatLocation = seatLocation;
        this.isPadding = isPadding;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getHoldStatus() {
        return holdStatus;
    }

    public void setHoldStatus(String holdStatus) {
        this.holdStatus = holdStatus;
    }

    public int getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(int seatLocation) {
        this.seatLocation = seatLocation;
    }

    public boolean isPadding() {
        return isPadding;
    }

    public void setPadding(boolean padding) {
        isPadding = padding;
    }
    public void setTier(int tier){this.tier = tier;}
    public Integer getTier(){return this.tier;}

    public void setPrice(Double price){
        this.price = price;
    }

    public Double getPrice(){return this.price;}
}
