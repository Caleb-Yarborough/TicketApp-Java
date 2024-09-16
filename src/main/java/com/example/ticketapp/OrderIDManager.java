package com.example.ticketapp;

import java.io.*;

public class OrderIDManager {
    private String filePath;

    public OrderIDManager() {
        this.filePath = "id_store.txt";
    }

    public int getNextOrderID() throws IOException {
        int lastNumber = 0;
        String currentLine;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.trim().isEmpty()) {
                    lastNumber = Integer.parseInt(currentLine.trim());
                }
            }
        }

        return lastNumber + 1;
    }

    public void appendOrderID(int number) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.newLine(); // Start a new line
            writer.write(Integer.toString(number)); // Append the number
        }
    }
}
