package com.example.ticketapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CatPerformancePageController {
    @FXML
    private ImageView myImageView;

    @FXML
    private Image chartImage;


    @FXML
    private Button logeButton;

    @FXML
    private Button orchestraButton;



    @FXML
    private Label performanceTitleLabel;

    @FXML
    private Button returnButton;
    private Stage stage;
    private Scene scene;

    private Performance performance;
    private TicketCompany app;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.app = TicketCompany.getInstance();

    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene mainScene){
        this.scene = mainScene;
    }
    public void setPerformance(Performance performance){
        this.performance = performance;
        updateGUI();
    }

    public Scene getScene() {
        return scene;
    }

    public Performance getPerformance() {
        return performance;
    }

    public TicketCompany getApp() {
        return app;
    }

    public void setApp(TicketCompany app) {
        this.app = app;
    }

    public void updateGUI(){
        performanceTitleLabel.setText(performance.getPerfName());
    }
    public static Section Builder(String section) {
        try {
            if (section.equals("loge")) {
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
            } else {
                ArrayList<String> skippedRows = new ArrayList<>(Arrays.asList("I"));
                ArrayList<Section> sections = new ArrayList<>();
                Section orchestra = SectionBuilder.sectionBuilder("A", skippedRows, "CATOrch.csv");
                orchestra.setSectionName("Orchestra");
                Section loge = SectionBuilder.sectionBuilder("P", skippedRows, "CATLoge.csv");
                loge.setSectionName("Loge");
                sections.add(orchestra);
                sections.add(loge);
                SeatingChart cat = new SeatingChart("Cary Art Center", sections);
                return orchestra;
            }

        } catch (Exception e) {
            System.out.println("Error");
        }
        return null;
    }

    @FXML
    private void onOrchestraButtonClicked(ActionEvent event) throws IOException {
        openOrchScene();
    }

    @FXML
    private void onLogeButtonClicked(ActionEvent event) throws IOException{
        openLogeScene();
    }

    private void openLogeScene() throws IOException{
        // Load the FXML for the section chart and get the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SectionChart.fxml")); // Correct the path if needed
        Parent root = loader.load();
        SectionBuilderController controller = loader.getController();

        controller.setApp(app);
        controller.setPerformance(performance);
        controller.setStage(stage);

        // Build the section and set it in the controller
        Section section = Builder("loge"); // Assumes Builder() is a correct, static method
        controller.setSectionBuild(section, 40.00);

        // Load the image and create an ImageView to add to the scene
        Image backgroundImage = new Image(getClass().getResourceAsStream("/img/theWall2.jpg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Create a StackPane to layer the image and the original root
        StackPane layeredPane = new StackPane();
        layeredPane.getChildren().addAll(backgroundImageView, root);

        // Create and set the new scene
        Scene newScene = new Scene(layeredPane, 800, 600); // No need for a StackPane if there's no background image
        scene.getStylesheets().add(getClass().getResource("sectionStyle.css").toExternalForm()); // Load the CSS
        stage.setScene(newScene);
    }



    private void openOrchScene() throws IOException{
        // Load the FXML for the section chart and get the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SectionChart.fxml")); // Correct the path if needed
        Parent root = loader.load();
        SectionBuilderController controller = loader.getController();
        controller.setApp(app);
        controller.setPerformance(performance);
        controller.setStage(stage);

        // Build the section and set it in the controller
        Section section = Builder("orchestra"); // Assumes Builder() is a correct, static method
        controller.setSectionBuild(section, 50.00);
        // Load the image and create an ImageView to add to the scene
        Image backgroundImage = new Image(getClass().getResourceAsStream("/img/theWall2.jpg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Create a StackPane to layer the image and the original root
        StackPane layeredPane = new StackPane();
        layeredPane.getChildren().addAll(backgroundImageView, root);

        // Create and set the new scene
        Scene newScene = new Scene(layeredPane, 800, 600); // No need for a StackPane if there's no background image
        scene.getStylesheets().add(getClass().getResource("sectionStyle.css").toExternalForm()); // Load the CSS
        stage.setScene(newScene);
    }
}

