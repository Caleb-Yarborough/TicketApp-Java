package com.example.ticketapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.util.ArrayList;

public class SectionBuilderController {

    @FXML
    private GridPane sectionGridPane;
    private Section section;
    private Stage stage;
    private Scene scene;
    private TicketCompany app;
    private Performance performance;
    private ArrayList<Seat> selectedSeats = new ArrayList<>();
    private ArrayList<RadioButton> selectedRadioButtons = new ArrayList<>();

    public void initialize(){
        app = TicketCompany.getInstance();
    }

    public void setSectionBuild(Section section, Double price) {
        this.section = section;
        this.section.setPrice(price);
        createSectionUI(section);
    }



    // Method to initialize UI for a given section
    public void createSectionUI(Section section) {
        GridPane buttonGridPane = new GridPane();
        buttonGridPane.setHgap(10); // Set the horizontal gap between grid cells

//        Button returnButton = new Button("Return to Seating Chart");
//        returnButton.setOnAction(e -> {
//            try {
//                handleReturnToSeatingChart();
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
        Button addToCartButton = new Button("Add Seats to Cart");
        addToCartButton.setOnAction(e -> {
            try {
                handleAddToCart();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

// Configure your buttons as required

        //buttonGridPane.add(returnButton, 0, 0); // Add the return button to the first column
        buttonGridPane.add(addToCartButton, 1, 0); // Add the add to cart button to the second column



        //GridPane.setHalignment(returnButton, HPos.LEFT); // Align the return button to the left
        GridPane.setHalignment(addToCartButton, HPos.RIGHT); // Align the add to cart button to the right
        // Add a label for the section name at the top
        Label sectionLabel = new Label("Section: " + section.getSectionName());
        // Initialize and configure the buttons

        sectionLabel.setAlignment(Pos.CENTER); // Center the label text
        sectionLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: red; -fx-font-weight: bold;"); // Optional: style the label
        sectionGridPane.add(sectionLabel, 0, 0, getMaxColumnCount(section), 1); // Span the label across the top

        int rowIndex = 1; // Start from the second row to leave space for the section label
        for (Row row : section.getRows()) {
            // Add a label for the row name
            Label rowLabel = new Label(row.getRow());
            rowLabel.setAlignment(Pos.CENTER_RIGHT); // Align to the right of the cell
            rowLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: red; -fx-font-weight: bold; -fx-padding: 0 10 0 0;"); // Add padding on the right
            rowLabel.setMinWidth(Label.USE_PREF_SIZE); // Use preferred size to accommodate text

            // Set the column index to leave space for the row label
            int columnIndex = 1; // Adjust as necessary for your layout
            sectionGridPane.add(rowLabel, columnIndex, rowIndex); // Add the row label to the first column
            columnIndex++;
            for (Seat seat : row.getSeats()) {
                RadioButton rb = new RadioButton();
                rb.setOnAction(e -> {
                    if (rb.isSelected()) {
                        updateSelectedRadioButtons(rb, seat);
                    } else {
                        selectedSeats.remove(seat);
                    }
                });
                if (!seat.isPadding() && "o".equals(seat.getHoldStatus())) {
                    String tooltipText = "Section: " + section.getSectionName() + "\nRow: " + row.getRow() + "\nSeat: " + seat.getSeatNumber() + "\nPrice: " + seat.getPrice();
                    Tooltip tooltip = new Tooltip(tooltipText);
                    tooltip.setStyle("-fx-font-size: 12px; -fx-background-color: black; -fx-text-fill: red; -fx-border-color: #b3b3b3; -fx-border-width: 1px; -fx-padding: 5px; -fx-opacity: 0.9;");
                    tooltip.setShowDelay(Duration.ZERO);
                    rb.setTooltip(tooltip);
                } else {
                    rb.setDisable(true);
                    rb.setVisible(false);
                    rb.setManaged(false);
                }
                sectionGridPane.add(rb, columnIndex, rowIndex);
                columnIndex++;
            }
            rowIndex++;
        }

        // Set constraints after adding all elements
        setGridPaneConstraints(sectionGridPane, rowIndex, getMaxColumnCount(section) + 1);
        sectionGridPane.add(buttonGridPane, 0, getMaxColumnCount(section) + 2, getMaxColumnCount(section), 1); // Span across all columns
    }
    private void setGridPaneConstraints(GridPane gridPane, int numRows, int numColumns) {
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(10); // Set minimum height
            rowConstraints.setPrefHeight(30); // Set preferred height
            gridPane.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0; i < numColumns; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setMinWidth(10); // Set minimum width
            columnConstraints.setPrefWidth(30); // Set preferred width
            gridPane.getColumnConstraints().add(columnConstraints);
        }
    }

    private int getMaxColumnCount(Section section) {
        int maxColumns = 0;
        for (Row row : section.getRows()) {
            if (row.getSeats().size() > maxColumns) {
                maxColumns = row.getSeats().size();
            }
        }
        return maxColumns;
    }

    private void handleAddToCart() throws IOException {
        // Implementation for adding seats to cart
        SerialManager serialManager = new SerialManager();
        ArrayList<Ticket> tickets = new ArrayList<>();
        // Implementation for returning to seating chart
        for (Seat seat : selectedSeats) {
            // Change seat hold status
            seat.setHoldStatus("t");

            // Create a ticket for each seat
            //Ticket(int serial, Performance performance, Seat seat)
            int serialNumber = new SerialManager().getNextSerial();
            serialManager.appendSerial(serialNumber);
            Ticket ticket = new Ticket(serialManager.getNextSerial(), performance,seat);
            // Add ticket to some collection or processing system
            tickets.add(ticket);
            // Update UI if necessary to reflect the seat's new status
        }

        // Show checkout scene (assuming checkoutScene is a method that does this)
        checkoutScene(tickets);
    }

    // Method to update the selected RadioButtons
    private void updateSelectedRadioButtons(RadioButton rb, Seat seat) {
        if (rb.isSelected()) {
            if (selectedRadioButtons.size() >= 5) {
                // If we already have 5 selected, immediately deselect the new one
                rb.setSelected(false);
                // Optionally show a message to the user
                showAlertMaxSeatsReached();
            } else {
                selectedRadioButtons.add(rb);
                selectedSeats.add(seat);
            }
        } else {
            selectedRadioButtons.remove(rb);
            selectedSeats.remove(seat);
        }
    }

    private void showAlertMaxSeatsReached() {
        // Use your preferred method to alert the user that the max number of seats has been reached
        // For example, using an Alert dialog in JavaFX:
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Maximum Seats Selected");
        alert.setHeaderText(null);
        alert.setContentText("You can only select up to 5 seats.");
        alert.showAndWait();
    }

    private void handleReturnToSeatingChart() throws IOException {

    }

    public void checkoutScene(ArrayList<Ticket> tickets) throws IOException {
        // Load checkout FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("orderPage.fxml"));
        Scene nextScene = new Scene(loader.load());

        // If you need to pass data to the checkout controller
        CheckoutController checkoutController = loader.getController();
        checkoutController.setPerformance(performance);
        checkoutController.setApp(TicketCompany.getInstance());
        checkoutController.setSectionName(section.getSectionName());
        checkoutController.setTickets(tickets); // Method to pass selected seats or tickets
        checkoutController.setStage(stage);
        checkoutController.setScene(nextScene);

        stage.setScene(nextScene);
        //             FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientLogin.fxml"));
        //            Scene loginScene = new Scene(loader.load());
        //            ClientLoginController loginController = loader.getController();
        //            loginController.setApp(app);
        //            loginController.setStage(stage);
        //            loginController.setMainScene(mainScene);
        //            stage.setScene(loginScene);
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void setApp(TicketCompany app){
        this.app = app;
    }

    public void setPerformance(Performance performance){
        this.performance = performance;
    }
}