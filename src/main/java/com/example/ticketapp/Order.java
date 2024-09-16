package com.example.ticketapp;

import java.util.ArrayList;

public class Order extends ArrayList<Order> {
    private Customer customer;
    private int orderID;
    private ArrayList<Ticket> tickets;
    public Order(int orderID, Customer customer) {
        this.orderID = orderID;
        this.tickets = new ArrayList<>();
        this.customer = customer;
    }

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

    public ArrayList<Ticket> getTickets(){
        return this.tickets;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

}
