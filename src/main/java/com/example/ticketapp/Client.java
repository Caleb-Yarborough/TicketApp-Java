package com.example.ticketapp;

import java.util.ArrayList;

public class Client {
    private Integer venueID;
    private String clientUSERID;

    private String clientPassword;

    public Client(String clientUSERID, String clientPassword){
        this.clientUSERID = clientUSERID;
        this.clientPassword = clientPassword;
    }

    public Integer getVenueID() {
        return venueID;
    }

    public void setVenueIDs(Integer venueID) {
        this.venueID = venueID;
    }

    public String getClientUSERID() {
        return clientUSERID;
    }

    public void setClientUSERID(String clientUSERID) {
        this.clientUSERID = clientUSERID;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

}
