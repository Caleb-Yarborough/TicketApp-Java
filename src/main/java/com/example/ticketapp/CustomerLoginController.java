package com.example.ticketapp;
//Deleted from implementation exists still in case of implementing customer login controls

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
//import static com.example.ticketproject.Login_Util.registerUser;
//import static com.example.ticketproject.Login_Util.validateLogin;


public class CustomerLoginController implements Initializable {
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

    private Stage stage;
    private Scene mainScene;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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
            String enteredUsername = new String(UsernameTextField.getText());
            String enteredPassword = new String(PasswordTextField.getText());

            if(NewUser){
                validUser= LoginValidator.registerUser(enteredUsername,enteredPassword,true);
            }
            else {
                validUser= LoginValidator.validateLogin(enteredUsername,enteredPassword,true);

            }

            if(validUser){
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



    public void backToMainScene(){
        stage.setScene(mainScene);
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }




}
