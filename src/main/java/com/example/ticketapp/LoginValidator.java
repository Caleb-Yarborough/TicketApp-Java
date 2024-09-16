package com.example.ticketapp;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LoginValidator {

    private static String clientLogins = "client_logins.txt";
    private static String customerLogins = "customer_logins.txt";

    public static boolean validateLogin(String inputUsername, String inputPassword, boolean isCustomer) {
        String filePath;
        if (isCustomer) {
            filePath = customerLogins;
        } else {
            filePath = clientLogins;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2) {
                    String username = credentials[0];
                    String password = credentials[1];
                    if (username.equals(inputUsername) && password.equals(inputPassword)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static Map<String, String> getUsers(){
        Map<String, String> users = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(clientLogins))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2) {
                    String username = credentials[0];
                    String password = credentials[1];
                    users.put(username, password);

                }
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean registerUser(String username, String password, boolean isCustomer) {
        String filePath = isCustomer ? customerLogins : clientLogins;

        // Check if user already exists
        if (userExists(username, filePath)) {
            return false; // User already exists
        }

        // Append new user credentials to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(username + "," + password);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean userExists(String username, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username)) {
                    return true; // User already exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
