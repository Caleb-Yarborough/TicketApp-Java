package com.example.ticketapp;

import java.util.ArrayList;

public class Venue {
    private String venue_name;
    private String venue_add;
    private String venue_State;
    private String venue_Zip;

    private ArrayList<Performance> performances;

    private SeatingChart defaultSeatingChart;

    private String seatingChartLocation;

    private Integer venueID;


    //If using inherited refund/exchange policies for Org
    public Venue(String venueName, String venue_add, String venue_State, String venue_Zip, Integer venueID) {
        this.venue_add = venue_add;
        this.venue_name = venueName;
        this.venue_State = venue_State;
        this.venue_Zip = venue_Zip;
        this.venueID = venueID;
        this.performances = new ArrayList<>();

    }


    public Venue() {

    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getVenue_add() {
        return venue_add;
    }

    public void setVenue_add(String venue_add) {
        this.venue_add = venue_add;
    }

    public String getVenue_Zip() {
        return venue_Zip;
    }

    public void setVenue_Zip(String venue_Zip) {
        this.venue_Zip = venue_Zip;
    }

    public void add_Perf(Performance performance) {
        performances.add(performance);
    }
    public void remove_Perf(Performance performance) {
        performances.remove(performance);
    }

    public void setDefaultSeatingChart(SeatingChart seatingChart) {
        this.defaultSeatingChart = seatingChart;
    }

    public String getSeatingChartLocation(){return this.seatingChartLocation;}

    public void setSeatingChartLocation(String seatingChartLocation) {this.seatingChartLocation = seatingChartLocation;}

    public Integer getVenueID() {
        return venueID;
    }

    public void setVenueID(Integer venueID) {
        this.venueID = venueID;
    }

    public ArrayList<Performance> getPerformances(){
        return this.performances;
    }
}
