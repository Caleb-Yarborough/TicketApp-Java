package com.example.ticketapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Login_Util {
    private static final String CUSTOMER_FILE = "customer_logins.txt";
    private static final String CLIENT_FILE = "client_logins.txt";

    public static boolean registerUser(String username, String password, boolean isCustomer) {
        String filePath = isCustomer ? CUSTOMER_FILE : CLIENT_FILE;
        try {
            Path path = Paths.get(filePath);
            List<String> existingUsers = Files.readAllLines(path);
            String newUserEntry = username + ", " + password;
            if (existingUsers.stream().anyMatch(line -> line.split(", ")[0].equals(username))) {
                return false; // Username already exists
            }
            Files.write(path, (newUserEntry + "\n").getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateLogin(String username, String password, boolean isCustomer) {
        String filePath = isCustomer ? CUSTOMER_FILE : CLIENT_FILE;
        try {
            List<String> users = Files.readAllLines(Paths.get(filePath));
            String userEntry = username + ", " + password;
            return users.stream().anyMatch(line -> line.equals(userEntry));
        } catch (IOException e) {
            return false;
        }
    }
}
