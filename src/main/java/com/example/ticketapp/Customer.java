package com.example.ticketapp;

import java.util.ArrayList;

public class Customer {
    private String customerName;
    private String cust_add;
    private String cust_City;
    private String cust_State;
    private String cust_Zip;

    private ArrayList<Order> orders;

    private String email;

    public Customer() {
        this.orders = new ArrayList<>();
    }

    public Customer(String customerName, String cust_add, String cust_City, String cust_State, String cust_Zip, String email) {
        this.customerName = customerName;
        this.cust_add = cust_add;
        this.cust_City = cust_City;
        this.cust_State = cust_State;
        this.cust_Zip = cust_Zip;
        this.email = email;
        this.orders = new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCust_add() {
        return cust_add;
    }

    public void setCust_add(String cust_add) {
        this.cust_add = cust_add;
    }

    public String getCust_City() {
        return cust_City;
    }

    public void setCust_City(String cust_City) {
        this.cust_City = cust_City;
    }

    public String getCust_State() {
        return cust_State;
    }

    public void setCust_State(String cust_State) {
        this.cust_State = cust_State;
    }

    public String getCust_Zip() {
        return cust_Zip;
    }

    public void setCust_Zip(String cust_Zip) {
        this.cust_Zip = cust_Zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    public ArrayList<Order> getOrders(){
        return this.orders;
    }
}
