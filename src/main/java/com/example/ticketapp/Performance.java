package com.example.ticketapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Performance {
    private Venue associatedVenue;

    private Integer performanceID;
    private String perfName;
    private String date;
    private String time;//Sat Dec 15 2024 7:30 PM   for ticket headers
    private boolean isGA;

    private Map<String, String> holdPurpose;
    private Map<Integer, Double> priceByTier;

    private ArrayList<Customer> customers;

    private Map<String, Double> feeByType;

    private ArrayList<Section> sections;

    private Double stateTax;

    public Performance() {
        super();
        this.customers = new ArrayList<>();
        this.holdPurpose = new HashMap<>();
        this.holdPurpose.put("v", "VIP");
        this.holdPurpose.put("t", "Temporary");
        this.holdPurpose.put("o", "Open");
        this.holdPurpose.put("s", "Sold");
        this.feeByType = new HashMap<>();
        this.sections = new ArrayList<>();
        this.priceByTier = new HashMap<>();
        this.priceByTier.put(1, 25.50);
        this.priceByTier.put(2, 40.50);
        this.priceByTier.put(3, 75.50);

    }

    public Performance(String perfName, String date, String time, boolean isGA, Integer performanceID, Double stateTax) {

        this.stateTax = stateTax;
        this.perfName = perfName;
        this.date = date;
        this.time = time;
        this.isGA = isGA;
        this.performanceID = performanceID;
        this.customers = new ArrayList<>();
        this.holdPurpose = new HashMap<>();
        this.holdPurpose.put("v", "VIP");
        this.holdPurpose.put("t", "Temporary");
        this.holdPurpose.put("o", "Open");
        this.holdPurpose.put("s", "Sold");
        this.feeByType = new HashMap<>();
        this.sections = new ArrayList<>();
        this.priceByTier = new HashMap<>();
        this.priceByTier.put(1, 25.50);
        this.priceByTier.put(2, 40.50);
        this.priceByTier.put(3, 75.50);
    }


    public String getPerfName() {
        return perfName;
    }

    public void setPerfName(String perfName) {
        this.perfName = perfName;
    }


    public boolean isGA() {
        return isGA;
    }

    public void setGA(boolean GA) {
        isGA = GA;
    }


    public void addCustomer(Customer customer){
        this.customers.add(customer);
    }

    public ArrayList<Customer> getCustomerList(){
        return this.customers;
    }

    public void setHoldPurpose(Map<String, String> holdPurpose) {
        this.holdPurpose = holdPurpose;
    }

    public Map<String, String> getHoldPurpose(){
        return this.holdPurpose;
    }

    public void addPriceByHold(String hold, String purpose) {
        this.holdPurpose.put(hold, purpose);
    }

    public String getPurposeByHold(String hold){
        return this.holdPurpose.get(hold);
    }

    public void setFeeByType(Map<String, Double> feeByType) {
        this.feeByType = feeByType;
    }

    public Map<String, Double> getFeeByTypeByHold(){
        return this.feeByType;
    }

    public void addFeeByType(String feeType, Double price) {
        this.feeByType.put(feeType, price);
    }

    public Double getFee(String feeType){
        return this.feeByType.get(feeType);
    }

    public void setFeeForType(String type, Double fee) {
        this.feeByType.replace(type, feeByType.get(type), fee);
    }

    public Integer getPerformanceID() {
        return performanceID;
    }

    public void setPerformanceID(Integer performanceID) {
        this.performanceID = performanceID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public Section getSection(String sectionName) {
        for (Section section : sections) {
            if (section.getSectionName().equals(sectionName)) {
                return section;
            }
        }
        return null; // or throw an exception
    }
    public Double getPriceByTier(Integer tier){
        return priceByTier.get(tier);
    }

    public Double getTotalFees(){
        double total = 0;
        for (Map.Entry<String, Double> entry: feeByType.entrySet()){
            String feeName = entry.getKey();
            Double feeAmount = entry.getValue();
            total += feeAmount;
        }
        return total;
    }

    public String getFeesString() {
        StringBuilder feesStringBuilder = new StringBuilder();

        for (Map.Entry<String, Double> entry : feeByType.entrySet()) {
            String feeName = entry.getKey();
            Double feeAmount = entry.getValue();
            feesStringBuilder.append(feeName).append(": $").append(String.format("%.2f", feeAmount)).append("\n");
        }

        return feesStringBuilder.toString();
    }

    public Double getStateTax() {
        return stateTax;
    }

    public void setStateTax(Double stateTax) {
        this.stateTax = stateTax;
    }
    public Map<String, Double> getFeeByType(){
        return this.feeByType;
    }

    public Map<Integer, Double> getTierPricing(){
        return this.priceByTier;
    }

}
