package com.example.ticketapp;

import java.util.ArrayList;

public class Row {
    private String row;
    private ArrayList<Seat> seats;

    public Row(){

    }

    public Row(String row, ArrayList<Seat> seats){
        this.row = row;
        this.seats = seats;
    }

    public void setRowPrice(Integer tier, Double price){
        for (Seat seat: seats){
            seat.setTier(tier);
            seat.setPrice(price);
        }
    }

    public void setPrice(Double price){
        for (Seat seat: seats){
            seat.setPrice(price);
        }
    }

    public Seat getSeat(String seatNumber){
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat;
            }
        }
        return null;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }


    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
}
