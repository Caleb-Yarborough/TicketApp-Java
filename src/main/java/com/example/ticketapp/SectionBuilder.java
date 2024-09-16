package com.example.ticketapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SectionBuilder {

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
