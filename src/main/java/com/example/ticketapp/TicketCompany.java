package com.example.ticketapp;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketCompany {

    private static TicketCompany instance;
    static {
        try {
            instance = new TicketCompany();
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception or handle it as required
            instance = null; // Set instance to null if initialization fails
        }
    }

    private final String catOrchestra = "CATOrch.csv";
    private final String catLoge = "CATLoge.csv";
    private final String gaSection = "GASection.csv";

    private ArrayList<Performance> performances;
    private ArrayList<Organization> orgs;
    private ArrayList<Customer> customers;
    private Map<String, ArrayList<Order>> customerOrders; //userID, ArrayList of Orders
    private ArrayList<Integer> serials;
    private ArrayList<Integer> orderIds;
    private ArrayList<Client> clients;
    private ArrayList<Venue> venues;
    private Map<String, Integer> clientAssociatedVenue;
    private final String orderID_file = "id_store.txt";
    private final String serialID_file = "serial_store.txt";


    public TicketCompany() throws IOException {
        this.orgs = new ArrayList<>();
        orgs.add(getCATOrg());
        orgs.add(getLincolnOrg());
        this.venues = new ArrayList<>();
        Venue venue1 = getCATVenue();
        Performance performance1 = getCATPerformance();
        Performance performance2 = getLincolnPerformance();
        venue1.add_Perf(performance1);
        venues.add(venue1);
        Venue venue2 = getLincolnVenue();
        venue2.add_Perf(performance2);
        venues.add(venue2);
        this.customerOrders = new HashMap<>();
        this.serials = new ArrayList<>();
        this.orderIds= new ArrayList<Integer>();
        this.customerOrders = new HashMap<>();
        this.clients = getExistingClients();

        this.performances = new ArrayList<>();
        performances.add(performance1);
        performances.add(performance2);
        this.customers = new ArrayList<>();

    }

    private void loadNumbersFromFile() {
        try {
            List<String> existingOrders = Files.readAllLines(new File(orderID_file).toPath());
            List<String> existingSerials = Files.readAllLines(new File(serialID_file).toPath());
            for (String line : existingOrders) {
                // Assuming one number per line
                orderIds.add(Integer.parseInt(line.trim()));
            }
            for (String line : existingSerials) {
                serials.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Handle exceptions or throw them further
        }
    }

    public ArrayList<Client> getExistingClients(){
        ArrayList<Client> clients = new ArrayList<>();
        Client client1 = new Client("ltg@gmail.com", "password123!");
        client1.setVenueIDs(6679);
        clients.add(client1);
        Client client2 = new Client("ctg@gmail.com", "password123!");
        client2.setVenueIDs(657);
        clients.add(client2);
        Client client3 = new Client("client@gmail.com", "password123!");
        client3.setVenueIDs(6679);
        clients.add(client3);
        return clients;
    }

    public void addOrg(Organization organization) {
        orgs.add(organization);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addOrder(String userID, Order order) {
        // Check if the customer already has an orders list
        if (customerOrders.containsKey(userID)) {
            // If yes, get the list and add the new order
            ArrayList<Order> orders = customerOrders.get(userID);
            orders.add(order);
        } else {
            // If not, create a new list, add the order, and put it in the map
            ArrayList<Order> newOrders = new ArrayList<>();
            newOrders.add(order);
            customerOrders.put(userID, newOrders);
        }
    }

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
        orchestra.setSectionPrice(2, 40.00);
        pinkFloyd.addSection(orchestra);

        Section loge = sectionBuilder("P", skipped, catLoge);
        loge.setSectionName("Loge");
        loge.setSectionPrice(1, 30.00);
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


    public static TicketCompany getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Instance of TicketCompany was not initialized.");
        }
        return instance;
    }

    public Venue getclientAssociatedVenue(String userID){
        for (Client client: clients){
            if(client.getClientUSERID().equals(userID)){
                Integer venueID = client.getVenueID();
                for (Venue venue: venues){
                    if(venue.getVenueID().equals(venueID)){
                        return venue;
                    }
                }
            }
        }
        return null;
    }
}
