package com.example.ticketapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VenueMainPageController {

    @FXML
    public GridPane mainGrid;
    @FXML
    private Button Perf1Button;

    @FXML
    private Label Perf1Name;

    @FXML
    private Button Perf2Button;

    @FXML
    private Label Perf2Name;

    @FXML
    private Label VenueName;

    private Stage stage;
    private Scene mainScene;
    private TicketCompany app;

    private Venue venue;
    private String userID;
    private Performance performance1;
    private Performance performance2;




    public void initialize(URL url, ResourceBundle resourceBundle) {
        app = TicketCompany.getInstance();
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        mainGrid.setBackground(new Background(backgroundFill));
        Perf1Name.setStyle("-fx-font-size: 12px; " +
                "-fx-letter-spacing: 2px; " +
                "-fx-word-spacing: 2px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-weight: normal; " +
                "-fx-text-decoration: none; " +
                "-fx-font-style: normal; " +
                "-fx-font-variant: normal; " +
                "-fx-text-transform: none;");
        Perf2Name.setStyle("-fx-font-size: 12px; " +
                "-fx-letter-spacing: 2px; " +
                "-fx-word-spacing: 2px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-weight: normal; " +
                "-fx-text-decoration: none; " +
                "-fx-font-style: normal; " +
                "-fx-font-variant: normal; " +
                "-fx-text-transform: none;");
        VenueName.setStyle("-fx-font-family: 'Arial Black', Gadget, sans-serif; " +
                "-fx-font-size: 21px; " +
                "-fx-letter-spacing: 2px; " +
                "-fx-word-spacing: 2px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-weight: normal; " +
                "-fx-text-decoration: none; " +
                "-fx-font-style: normal; " +
                "-fx-font-variant: normal; " +
                "-fx-text-transform: none;");
        Perf1Button.setStyle("-fx-background-color: linear-gradient(to bottom, #f24537 5%, #c62d1f 100%), #f24537; " +
                "-fx-background-radius: 6px; " +
                "-fx-border-color: #d02718; " +
                "-fx-border-radius: 6px; " +
                "-fx-text-fill: #ffffff; " +
                "-fx-font-family: Arial; " +
                "-fx-font-size: 15px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 6px 24px;");
        Perf2Button.setStyle("-fx-background-color: linear-gradient(to bottom, #f24537 5%, #c62d1f 100%), #f24537; " +
                "-fx-background-radius: 6px; " +
                "-fx-border-color: #d02718; " +
                "-fx-border-radius: 6px; " +
                "-fx-text-fill: #ffffff; " +
                "-fx-font-family: Arial; " +
                "-fx-font-size: 15px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 6px 24px;");



    }

    public void setUserID(String userID){
        this.userID = userID;
        updateUI();
    }

    @FXML
    void Perf1GO(ActionEvent event) throws IOException {
        app = TicketCompany.getInstance();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("catsalepage.fxml"));
        Scene nextScene = new Scene(loader.load());
        CatPerformancePageController performancePageController = loader.getController();
        performancePageController.setApp(app);
        performancePageController.setPerformance(performance1);
        performancePageController.setStage(stage);
        performancePageController.setScene(nextScene);
        stage.setScene(nextScene);
    }

    @FXML
    void Perf2GO(ActionEvent event) {

    }

    @FXML
    void PurchasesGO(ActionEvent event) {

    }
    public void removeLabel(Label label) {
        mainGrid.getChildren().remove(label);
    }
    public void removeButton(Button button){
        mainGrid.getChildren().remove(button);
    }

    public void setStage(Stage stage){this.stage = stage;}
    public void setScene(Scene scene){this.mainScene = scene;}
    public void updateUI() {
        Perf1Button.setText("Buy Tickets");
        Perf2Button.setText("Buy Tickets");
        app = TicketCompany.getInstance();
        this.venue = app.getclientAssociatedVenue(userID);

        VenueName.setText(venue.getVenue_name());

        ArrayList<Performance> performances = venue.getPerformances();
        if (performances.isEmpty()){
            Perf1Name.setText("Empty");
        } else {
            int count  = 0;
            for (Performance performance: performances) {
                if (count == 0){
                    Perf1Name.setText(performances.get(0).getPerfName());
                    performance1 = performance;
                    count++;
                    if (performances.size() == 1){
                        removeLabel(Perf2Name);
                        removeButton(Perf2Button);
                    }
                } else {
                    Perf2Name.setText(performances.get(count).getPerfName());
                    performance2 = performance;
                }
            }
        }

        // Assuming you have at least one performance
//        if (!performances.isEmpty()) {
//            Perf1Name.setText(performances.get(0).getPerfName());
//            Perf1Button.setUserData(performances.get(0)); // Store performance in button's user data for later use
//
//            // If there's a second performance, update its label and button as well
//            if (performances.size() > 1) {
//                Perf2Name.setText(performances.get(1).getPerfName());
//                Perf2Button.setUserData(performances.get(1));
//            }
//        } else {
//            Perf1Name.setText("EMPTY");
//        }
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        mainGrid.setBackground(new Background(backgroundFill));
        Perf1Name.setStyle("-fx-font-size: 12px; " +
                "-fx-letter-spacing: 2px; " +
                "-fx-word-spacing: 2px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-weight: normal; " +
                "-fx-text-decoration: none; " +
                "-fx-font-style: normal; " +
                "-fx-font-variant: normal; " +
                "-fx-text-transform: none;");
        Perf2Name.setStyle("-fx-font-size: 12px; " +
                "-fx-letter-spacing: 2px; " +
                "-fx-word-spacing: 2px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-weight: normal; " +
                "-fx-text-decoration: none; " +
                "-fx-font-style: normal; " +
                "-fx-font-variant: normal; " +
                "-fx-text-transform: none;");
        VenueName.setStyle("-fx-font-family: 'Arial Black', Gadget, sans-serif; " +
                "-fx-font-size: 21px; " +
                "-fx-letter-spacing: 2px; " +
                "-fx-word-spacing: 2px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-weight: normal; " +
                "-fx-text-decoration: none; " +
                "-fx-font-style: normal; " +
                "-fx-font-variant: normal; " +
                "-fx-text-transform: none;");
        Perf1Button.setStyle("-fx-background-color: linear-gradient(to bottom, #f24537 5%, #c62d1f 100%), #f24537; " +
                "-fx-background-radius: 6px; " +
                "-fx-border-color: #d02718; " +
                "-fx-border-radius: 6px; " +
                "-fx-text-fill: #ffffff; " +
                "-fx-font-family: Arial; " +
                "-fx-font-size: 15px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 6px 24px;");
        Perf2Button.setStyle("-fx-background-color: linear-gradient(to bottom, #f24537 5%, #c62d1f 100%), #f24537; " +
                "-fx-background-radius: 6px; " +
                "-fx-border-color: #d02718; " +
                "-fx-border-radius: 6px; " +
                "-fx-text-fill: #ffffff; " +
                "-fx-font-family: Arial; " +
                "-fx-font-size: 15px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 6px 24px;");



    }

    public void setApp(TicketCompany app){
        this.app = app;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }
}
