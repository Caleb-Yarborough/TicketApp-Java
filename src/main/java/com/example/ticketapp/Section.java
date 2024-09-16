package com.example.ticketapp;

import java.util.ArrayList;

public class Section {
    private ArrayList<Row> rows;
    private String sectionName;

    public Section(String sectionName, ArrayList<Row> rows) {
        this.sectionName = sectionName;
        this.rows = rows;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Row getRow(String rowName) {
        for (Row row : rows) {
            if (row.getRow().equals(rowName)) {
                return row;
            }
        }
        return null; // or throw an exception
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Section: ").append(sectionName).append("\n");

        for (Row row : rows) {
            builder.append(row.getRow()).append(": ");
            for (Seat seat : row.getSeats()) {
                if (seat.isPadding()) {
                    builder.append("  "); // Three spaces for padding
                } else {
                    builder.append(seat.getHoldStatus()).append(" ");
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public void setSectionPrice(Integer tier, Double price){
        for (Row row: rows) {
            row.setRowPrice(tier, price);
        }
    }

    public void setPrice(Double price){
        for (Row row: rows){
            row.setPrice(price);
        }
    }
}
