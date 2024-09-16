package com.example.ticketapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

public class MainScreenController implements Initializable {
    @FXML
    private GridPane gridPane;
    @FXML
    public Label welcomeTextLabel;
    private TicketCompany app;

    @FXML
    private Button clientLoginButton;
    private Stage stage;
    private Scene mainScene;
    private final String catOrchestra = "CATOrch.csv";
    private final String catLoge = "CATLoge.csv";



    @FXML
    private void openLoginScreen(String userType) throws IOException {
        if (userType.equals("Client")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientLogin.fxml"));
            Scene loginScene = new Scene(loader.load());
            ClientLoginController loginController = loader.getController();
            loginController.setApp(app);
            loginController.setStage(stage);
            loginController.setMainScene(mainScene);
            stage.setScene(loginScene);
        } else if (userType.equals("None")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pricingTierScene.fxml"));
            Scene pricingScene = new Scene(loader.load());
            PricingBuilderController pricingBuilderController = loader.getController();
            Performance pinkFloyd = new Performance("Pink Floyd - The Wall","January 25, 2024","7:00 PM",false,1129,0.075);
            ArrayList<String> skipped = new ArrayList<>();
            skipped.add("I");
            pinkFloyd.addSection(sectionBuilder("A", skipped, catOrchestra));
            pinkFloyd.addSection(sectionBuilder("P", skipped, catLoge));
            pricingBuilderController.setSectionBuild(pinkFloyd, pinkFloyd.getSection(catOrchestra));
            pricingBuilderController.setStage(stage);

        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerLogin.fxml"));
            Scene loginScene = new Scene(loader.load());
            CustomerLoginController loginController = loader.getController();
            loginController.setStage(stage);
            loginController.setMainScene(mainScene);
            stage.setScene(loginScene);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainScene(Scene mainScene){
        this.mainScene = mainScene;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        app = TicketCompany.getInstance();
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        gridPane.setBackground(new Background(backgroundFill));
        welcomeTextLabel.setStyle("-fx-font-family: 'Arial Black', Gadget, sans-serif; " +
                "-fx-font-size: 21px; " +
                "-fx-letter-spacing: 2px; " +
                "-fx-word-spacing: 2px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-weight: normal; " +
                "-fx-text-decoration: none; " +
                "-fx-font-style: normal; " +
                "-fx-font-variant: normal; " +
                "-fx-text-transform: none;");

        clientLoginButton.setStyle("-fx-background-color: linear-gradient(to bottom, #f24537 5%, #c62d1f 100%), #f24537; " +
                "-fx-background-radius: 6px; " +
                "-fx-border-color: #d02718; " +
                "-fx-border-radius: 6px; " +
                "-fx-text-fill: #ffffff; " +
                "-fx-font-family: Arial; " +
                "-fx-font-size: 15px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 6px 24px;");

// Hover effect
        clientLoginButton.setOnMouseEntered(e -> clientLoginButton.setStyle(clientLoginButton.getStyle() + "-fx-background-color: linear-gradient(to bottom, #c62d1f 5%, #f24537 100%), #c62d1f;"));
        clientLoginButton.setOnMouseExited(e -> clientLoginButton.setStyle(clientLoginButton.getStyle() + "-fx-background-color: linear-gradient(to bottom, #f24537 5%, #c62d1f 100%), #f24537;"));

// Active effect
        clientLoginButton.setOnMousePressed(e -> clientLoginButton.setStyle(clientLoginButton.getStyle() + "-fx-translate-y: 1px;"));
        clientLoginButton.setOnMouseReleased(e -> clientLoginButton.setStyle(clientLoginButton.getStyle() + "-fx-translate-y: 0px;"));
        clientLoginButton.setOnAction(event -> {
            try {
                openLoginScreen("Client");
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        });

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

    public TicketCompany getApp() {
        return app;
    }

    public void setApp(TicketCompany app) {
        this.app = app;
    }
}
