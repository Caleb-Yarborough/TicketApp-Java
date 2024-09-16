package com.example.ticketapp;
//Deleted from implementation


    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.geometry.HPos;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.layout.ColumnConstraints;
    import javafx.scene.layout.GridPane;
    import javafx.scene.layout.RowConstraints;
    import javafx.stage.Stage;
    import javafx.util.Duration;

    import java.net.URL;
    import java.util.Map;
    import java.util.ResourceBundle;

public class PricingBuilderController implements Initializable {

    @FXML
    private GridPane sectionGridPane;
    private Section section;
    private Performance performance;
    private Stage stage;
    private Scene mainScene;



    public void setSectionBuild(Performance performance, Section section) {
        this.section = section;
        this.performance = performance;
        createSectionUI(performance, section);
    }

    // Method to initialize UI for a given section
    public void createSectionUI(Performance performance, Section section) {
        GridPane buttonGridPane = new GridPane();
        buttonGridPane.setHgap(10); // Set the horizontal gap between grid cells

        Button returnButton = new Button("Return to Seating Chart");
        Button addToCartButton = new Button("Add Seats to Cart");

// Configure your buttons as required

        buttonGridPane.add(returnButton, 0, 0); // Add the return button to the first column
        buttonGridPane.add(addToCartButton, 1, 0); // Add the add to cart button to the second column

        GridPane.setHalignment(returnButton, HPos.LEFT); // Align the return button to the left
        GridPane.setHalignment(addToCartButton, HPos.RIGHT); // Align the add to cart button to the right
        // Add a label for the section name at the top
        Label sectionLabel = new Label("Section: " + section.getSectionName());
        // Initialize and configure the buttons

        sectionLabel.setAlignment(Pos.CENTER); // Center the label text
        sectionLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: bold;"); // Optional: style the label
        sectionGridPane.add(sectionLabel, 0, 0, getMaxColumnCount(section), 1); // Span the label across the top

        int rowIndex = 1; // Start from the second row to leave space for the section label
        for (Row row : section.getRows()) {
// Row Label
            Label rowLabel = new Label(row.getRow());
            // ... rowLabel styling ...
            sectionGridPane.add(rowLabel, 0, rowIndex); // Place rowLabel in the first column

            // Tier ChoiceBox
            ChoiceBox<String> tierChoiceBox = new ChoiceBox<>();
            populateTierChoiceBox(tierChoiceBox, performance.getTierPricing()); // Populate ChoiceBox with tiers
            sectionGridPane.add(tierChoiceBox, 1, rowIndex); // Place tierChoiceBox in the second column

            //Price Label
            Label priceLabel = new Label(populatePriceLabel(tierChoiceBox.getValue()));

            // Apply Tier Pricing Button
            Button applyTierButton = new Button("Apply Tier");
            applyTierButton.setOnAction(event -> {
                String selectedTierInfo = tierChoiceBox.getValue();
                applyTierPricing(row, selectedTierInfo, priceLabel.getText());
            });
            sectionGridPane.add(applyTierButton, 3, rowIndex); // Place applyTierButton in the fourth column

            // Seat RadioButtons
            int columnIndex = 4; // Starting column index for seats
            for (Seat seat : row.getSeats()) {
                RadioButton rb = new RadioButton();
                if (!seat.isPadding() && "o".equals(seat.getHoldStatus())) {
                    String tooltipText = "Section: " + section.getSectionName() + "\nRow: " + row.getRow() + "\nSeat: " + seat.getSeatNumber();
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


    private void applyTierPricing(Row row, String tierInfo, String priceText) {
        try {
            String[] parts = tierInfo.split(": ");
            int tier = Integer.parseInt(parts[0].replace("Tier ", ""));
            String pricing = priceText.replace("$", "");
            double price = Double.parseDouble(pricing);
            for (Seat seat : row.getSeats()) {
                seat.setTier(tier);
                seat.setPrice(price);
                // Update UI or model as needed
            }
        } catch (NumberFormatException e) {
            // Handle invalid input
        }
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

    private void handleAddToCart() {
        // Implementation for adding seats to cart
    }

    private void handleReturnToSeatingChart() {
        // Implementation for returning to seating chart
    }

    private void populateTierChoiceBox(ChoiceBox<String> tierChoiceBox, Map<Integer, Double> priceByTier) {
        for (Map.Entry<Integer, Double> entry : priceByTier.entrySet()) {
            String tierInfo = "Tier " + entry.getKey();
            tierChoiceBox.getItems().add(tierInfo);
        }
    }

    private String populatePriceLabel(String tier){
        return "$" + performance.getPriceByTier(Integer.parseInt(tier));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }
}