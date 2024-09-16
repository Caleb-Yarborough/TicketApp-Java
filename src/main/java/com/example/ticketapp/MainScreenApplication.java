package com.example.ticketapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
        Parent root = mainLoader.load();
        MainScreenController mainController = mainLoader.getController();
        Scene mainScene = new Scene(root);
        mainController.setStage(stage);
        mainController.setMainScene(mainScene);

        stage.setScene(mainScene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}