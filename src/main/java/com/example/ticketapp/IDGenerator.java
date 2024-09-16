package com.example.ticketapp;

import java.io.*;
import java.nio.file.*;

public class IDGenerator {
    private static final String ID_STORE_FILE = "id_store.txt";
    private static int lastID;

    static {
        try {
            String lastIDStr = new String(Files.readAllBytes(Paths.get(ID_STORE_FILE)));
            lastID = Integer.parseInt(lastIDStr.trim());
        } catch (IOException | NumberFormatException e) {
            lastID = 0;
        }
    }

    public static synchronized int getNextID() {
        lastID++;
        try {
            Files.write(Paths.get(ID_STORE_FILE), String.valueOf(lastID).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastID;
    }
}