package com.example.ticketapp;

import java.util.ArrayList;

public class Organization {
    private String org_name;
    private String org_address;
    private String primary_contact_email;
    private String primary_contact_phone;

    private String org_State;
    private String org_Zip;


    private ArrayList<Integer> venueIDS;

    public Organization(){
        this.venueIDS = new ArrayList<>();

    }

    public Organization(String org_name, String org_address, String org_State, String org_Zip, String primary_contact_email,
                        String primary_contact_phone) {
        this.org_name = org_name;
        this.org_address = org_address;
        this.org_State = org_State;
        this.org_Zip = org_Zip;
        this.primary_contact_email = primary_contact_email;
        this.primary_contact_phone = primary_contact_phone;
        this.venueIDS = new ArrayList<>();
    }


    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getOrg_address() {
        return org_address;
    }

    public void setOrg_address(String org_address) {
        this.org_address = org_address;
    }

    public String getPrimary_contact_email() {
        return primary_contact_email;
    }

    public void setPrimary_contact_email(String primary_contact_email) {
        this.primary_contact_email = primary_contact_email;
    }

    public String getPrimary_contact_phone() {
        return primary_contact_phone;
    }

    public void setPrimary_contact_phone(String primary_contact_phone) {
        this.primary_contact_phone = primary_contact_phone;
    }

    public String getOrg_State() {
        return org_State;
    }

    public void setOrg_State(String org_State) {
        this.org_State = org_State;
    }

    public String getOrg_Zip() {
        return org_Zip;
    }

    public void setOrg_Zip(String org_Zip) {
        this.org_Zip = org_Zip;
    }


    public ArrayList<Integer> getVenueIDS(){
        return this.venueIDS;
    }

    public void addVenue(int venueID) {
        venueIDS.add(venueID);
    }
}
