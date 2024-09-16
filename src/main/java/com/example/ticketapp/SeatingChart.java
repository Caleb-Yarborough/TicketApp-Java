package com.example.ticketapp;

import java.util.ArrayList;
public class SeatingChart {
    private String seatingChartName;
    private ArrayList<Section> sections;

    public SeatingChart(String seatingChartName, ArrayList<Section> sections) {
        this.seatingChartName = seatingChartName;
        this.sections = sections;
    }

    public String getSeatingChartName() {
        return seatingChartName;
    }

    public void setSeatingChartName(String seatingChartName) {
        this.seatingChartName = seatingChartName;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public Section getSection(String sectionName) {
        for (Section section : sections) {
            if (section.getSectionName().equals(sectionName)) {
                return section;
            }
        }
        return null; // or throw an exception
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Seating Chart: ").append(seatingChartName).append("\n");

        for (Section section : sections) {

            builder.append(section.toString()).append("\n");
        }

        return builder.toString();
    }
}
