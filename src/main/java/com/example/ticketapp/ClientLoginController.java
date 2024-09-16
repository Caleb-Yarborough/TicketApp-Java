package com.example.ticketapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
//import static com.example.ticketproject.Login_Util.registerUser;
//import static com.example.ticketproject.Login_Util.validateLogin;
import static com.example.ticketapp.LoginValidator.validateLogin;
import static com.example.ticketapp.LoginValidator.registerUser;


public class ClientLoginController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label mainLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordLabel;

    @FXML
    private TextField UsernameTextField;

    @FXML
    private TextField PasswordTextField;

    @FXML
    private Button SubmitButton;

    @FXML
    private CheckBox NewUserCheck;

    @FXML
    private Label errorLabel;

    private String userID;

    private Stage stage;
    private Scene mainScene;
    private TicketCompany app;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        app = TicketCompany.getInstance();
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        gridPane.setBackground(new Background(backgroundFill));
        errorLabel.setStyle("-fx-font-family: 'Arial Black', Gadget, sans-serif; " +
                "-fx-font-size: 14px; " +
                "-fx-letter-spacing: 2px; " +
                "-fx-word-spacing: 2px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-weight: normal; " +
                "-fx-text-decoration: none; " +
                "-fx-font-style: normal; " +
                "-fx-font-variant: normal; " +
                "-fx-text-transform: none;");

        passwordLabel.setStyle("-fx-font-size: 12px; " +
                "-fx-letter-spacing: 2px; " +
                "-fx-word-spacing: 2px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-weight: normal; " +
                "-fx-text-decoration: none; " +
                "-fx-font-style: normal; " +
                "-fx-font-variant: normal; " +
                "-fx-text-transform: none;");
        emailLabel.setStyle("-fx-font-size: 14px; " +
                "-fx-letter-spacing: 2px; " +
                "-fx-word-spacing: 2px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-weight: normal; " +
                "-fx-text-decoration: none; " +
                "-fx-font-style: normal; " +
                "-fx-font-variant: normal; " +
                "-fx-text-transform: none;");
        mainLabel.setStyle("-fx-font-family: 'Arial Black', Gadget, sans-serif; " +
                "-fx-font-size: 21px; " +
                "-fx-letter-spacing: 2px; " +
                "-fx-word-spacing: 2px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-weight: normal; " +
                "-fx-text-decoration: none; " +
                "-fx-font-style: normal; " +
                "-fx-font-variant: normal; " +
                "-fx-text-transform: none;");

        SubmitButton.setStyle("-fx-background-color: linear-gradient(to bottom, #f24537 5%, #c62d1f 100%), #f24537; " +
                "-fx-background-radius: 6px; " +
                "-fx-border-color: #d02718; " +
                "-fx-border-radius: 6px; " +
                "-fx-text-fill: #ffffff; " +
                "-fx-font-family: Arial; " +
                "-fx-font-size: 15px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 6px 24px;");
        UsernameTextField.setStyle(
                "-fx-padding: 5px; " +
                        "-fx-font-size: 12px; " +
                        "-fx-border-width: 3px; " +
                        "-fx-border-color: #ffffff; " +
                        "-fx-background-color: #000000; " +
                        "-fx-text-fill: #ff0000; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-radius: 0px; "
        );
        PasswordTextField.setStyle(
                "-fx-padding: 5px; " +
                        "-fx-font-size: 12px; " +
                        "-fx-border-width: 3px; " +
                        "-fx-border-color: #ffffff; " +
                        "-fx-background-color: #000000; " +
                        "-fx-text-fill: #ff0000; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-radius: 0px; "
        );
        NewUserCheck.setStyle("-fx-font-size: 12px; " +
                "-fx-letter-spacing: 2px; " +
                "-fx-word-spacing: 2px; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-font-weight: normal; " +
                "-fx-text-decoration: none; " +
                "-fx-font-style: normal; " +
                "-fx-font-variant: normal; " +
                "-fx-text-transform: none;");

// Hover effect
        SubmitButton.setOnMouseEntered(e -> SubmitButton.setStyle(SubmitButton.getStyle() + "-fx-background-color: linear-gradient(to bottom, #c62d1f 5%, #f24537 100%), #c62d1f;"));
        SubmitButton.setOnMouseExited(e -> SubmitButton.setStyle(SubmitButton.getStyle() + "-fx-background-color: linear-gradient(to bottom, #f24537 5%, #c62d1f 100%), #f24537;"));

// Active effect
        SubmitButton.setOnMousePressed(e -> SubmitButton.setStyle(SubmitButton.getStyle() + "-fx-translate-y: 1px;"));
        SubmitButton.setOnMouseReleased(e -> SubmitButton.setStyle(SubmitButton.getStyle() + "-fx-translate-y: 0px;"));

    }

    Boolean NewUser = false;

    Boolean validUser;

    @FXML
    private void NewUserChecked(ActionEvent event){

        if(NewUserCheck.isSelected()){
            NewUser=true;
        }
        else {
            NewUser=false;
        }


    }

    @FXML
    private void SubmitButtonPressed(ActionEvent event){
        try {
            String enteredUsername = UsernameTextField.getText();
            String enteredPassword = PasswordTextField.getText();

            if(NewUser){
                validUser=registerUser(enteredUsername,enteredPassword,false);
            }
            else {
                validUser=validateLogin(enteredUsername,enteredPassword,false);

            }

            if(validUser){
                userID = enteredUsername;
                openNextScreen();
                errorLabel.setText("Information Accepted");
            }
            else{
                errorLabel.setText("Invalid Login");
                UsernameTextField.setText("");
                PasswordTextField.setText("");
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    private void openNextScreen() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VenuePage.fxml"));
            Scene loginScene = new Scene(loader.load());
            VenueMainPageController venueMainPageController = loader.getController();
            venueMainPageController.setUserID(userID);
            venueMainPageController.setApp(app);
            venueMainPageController.setStage(stage);
            venueMainPageController.setScene(loginScene);
            stage.setScene(loginScene);

    }

    public void backToMainScene(){
        stage.setScene(mainScene);
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }


    public TicketCompany getApp() {
        return app;
    }

    public void setApp(TicketCompany app) {
        this.app = app;
    }
}

