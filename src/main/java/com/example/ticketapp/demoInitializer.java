package com.example.ticketapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class demoInitializer {
    private final String catOrchestra = "CATOrch.csv";
    private final String catLoge = "CATLoge.csv";
    private final String gaSection = "GASection.csv";

    public Organization getCATOrg() throws IOException{
        Organization cpag = new Organization("Cary Performing Arts Group","123 Cary Parkway",
                "NC","27513","cpag@gmail.com","9195552857");
        return cpag;
    }

    public Venue getCATVenue(){
        Venue cpac = new Venue("Cary Performing Arts Center","219 Walnut St","NC","27518",
                657);
        return cpac;
    }
    public Performance getCATPerformance() throws IOException {
        Performance pinkFloyd = new Performance("Pink Floyd - The Wall","January 25, 2024","7:00 PM",false,1129,0.075);
        ArrayList<String> skipped = new ArrayList<>();
        skipped.add("I");
        Section orchestra = sectionBuilder("A", skipped, catOrchestra);
        orchestra.setSectionName("Orchestra");
        pinkFloyd.addSection(orchestra);

        Section loge = sectionBuilder("P", skipped, catLoge);
        loge.setSectionName("Loge");
        pinkFloyd.addSection(loge);
        return pinkFloyd;


    }

    public Organization getLincolnOrg(){
        Organization lincoln = new Organization("Lincoln Theatre Group", "2222 Downtown Somewhere St", "NC","27513","ltg@gmail.com","9195552222");
        return lincoln;
    }

    public Venue getLincolnVenue(){
        Venue lincoln = new Venue("Lincoln Theater", "111 Downtown St", "NC", "27519",6679);
        return lincoln;
    }

    public Performance getLincolnPerformance(){
        Performance gratefulRoots = new Performance("Grateful Roots Tour", "February 22, 2024", "7:00 PM", true, 2247, 0.075);
        ArrayList<String> skipped = new ArrayList<>();
        Section gaFloor = gaSectionBuilder(gaSection);
        gaFloor.setSectionName("GA Floor");
        Section gaBalcony = gaSectionBuilder(gaSection);
        gaBalcony.setSectionName("GA Balcony");
        for (Row row: gaFloor.getRows()){
            for (Seat seat: row.getSeats()){
                seat.setPrice(30.00);
            }
        }
        for (Row row: gaBalcony.getRows()) {
            for (Seat seat: row.getSeats()){
                seat.setPrice(40.00);
            }
        }
        return gratefulRoots;
    }

    public static Section gaSectionBuilder(String filename){
        String currentRowChar = "GA";
        int count = 1;
        int seatLocationCounter = 0;
        ArrayList<Row> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<Seat> seatsInRow = new ArrayList<>();
                String[] seatInfos = line.split(",");
                for (String seatInfo : seatInfos) {
                    Seat seat;
                    if (seatInfo.equals("*/*")) {
                        seat = new Seat("*", "*", "*", seatLocationCounter, true);
                    } else {
                        String[] parts = seatInfo.split("/");
                        String rowName = currentRowChar + String.valueOf(count);
                        seat = new Seat(rowName, parts[0], parts[1], seatLocationCounter, false);
                    }
                    seatsInRow.add(seat);
                    seatLocationCounter++;
                }

                String rowName = currentRowChar + String.valueOf(count);
                rows.add(new Row(rowName, seatsInRow));
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Section(extractSectionName(filename), rows); // Replace "SectionName" with actual section name if needed
    }

    public static Section sectionBuilder(String startRow, ArrayList<String> skippedRows, String filename) throws IOException {
        char currentRowChar = startRow.charAt(0);
        ArrayList<Row> rows = new ArrayList<>();
        int seatLocationCounter = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Check if the current row should be skipped
                if (skippedRows.contains(String.valueOf(currentRowChar))) {
                    currentRowChar++;
                    continue;
                }

                ArrayList<Seat> seatsInRow = new ArrayList<>();
                String[] seatInfos = line.split(",");
                for (String seatInfo : seatInfos) {
                    Seat seat;
                    if (seatInfo.equals("*/*")) {
                        seat = new Seat("*", "*", "*", seatLocationCounter, true);
                    } else {
                        String[] parts = seatInfo.split("/");
                        seat = new Seat(String.valueOf(currentRowChar), parts[0], parts[1], seatLocationCounter, false);
                    }
                    seatsInRow.add(seat);
                    seatLocationCounter++;
                }

                rows.add(new Row(String.valueOf(currentRowChar), seatsInRow));
                currentRowChar++;
            }
        }

        return new Section(extractSectionName(filename), rows); // Replace "SectionName" with actual section name if needed
    }

    private static String extractSectionName(String filePath) {
        // Extracting the file name without extension
        String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }
}
