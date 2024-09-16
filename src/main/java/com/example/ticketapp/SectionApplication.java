package com.example.ticketapp;
//Only purpose to test section loading

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class SectionApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SectionChart.fxml"));
        Parent root = loader.load();

        SectionBuilderController controller = loader.getController();
        Section section = Builder();
        controller.setSectionBuild(section, 40.00);

        // Load the image and create an ImageView to add to the scene
        Image backgroundImage = new Image(getClass().getResourceAsStream("com/example/ticketapp/abstract-background.jpg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Create a StackPane to layer the image and the original root
        StackPane layeredPane = new StackPane();
        layeredPane.getChildren().addAll(backgroundImageView, root);

        Scene scene = new Scene(layeredPane, 800, 600); // Set scene size
        scene.getStylesheets().add(getClass().getResource("sectionStyle.css").toExternalForm()); // Load the CSS
        primaryStage.setScene(scene);
        primaryStage.setTitle("Seating Chart"); // Set window title
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Section Builder() {
        try {
            ArrayList<String> skippedRows = new ArrayList<>(Arrays.asList("I"));
            ArrayList<Section> sections = new ArrayList<>();
            Section orchestra = SectionBuilder.sectionBuilder("A", skippedRows, "CATOrch.csv");
            orchestra.setSectionName("Orchestra");
            Section loge = SectionBuilder.sectionBuilder("P", skippedRows, "CATLoge.csv");
            loge.setSectionName("Loge");
            sections.add(orchestra);
            sections.add(loge);
            SeatingChart cat = new SeatingChart("Cary Art Center", sections);
            return loge;
        } catch (Exception e) {
            System.out.println("Error");
        }
        return null;
    }
}

