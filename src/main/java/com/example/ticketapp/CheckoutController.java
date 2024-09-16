package com.example.ticketapp;

import com.google.zxing.WriterException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CheckoutController {

    @FXML StackPane stackPane;
    @FXML GridPane gridPane;

    @FXML
    private Label addressLabel;

    @FXML
    private Label ticket1Label;
    @FXML
    private Label ticket2Label;
    @FXML
    private Label ticket3Label;
    @FXML
    private Label ticket4Label;
    @FXML
    private Label ticket5Label;

    @FXML
    private Label serial1Label;

    @FXML
    private Label serial2Label;
    @FXML
    private Label serial3Label;
    @FXML
    private Label serial4Label;
    @FXML
    private Label serial5Label;


    @FXML
    private TextField addressTextField;

    @FXML Button checkoutButton;

    @FXML
    private Button buttonTicket1;

    @FXML
    private Button buttonTicket2;

    @FXML
    private Button buttonTicket3;

    @FXML
    private Button buttonTicket4;

    @FXML
    private Button buttonTicket5;

    @FXML
    private Label cartLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private ImageView imgView1;

    @FXML
    private ImageView imgView2;

    @FXML
    private ImageView imgView3;

    @FXML
    private ImageView imgView4;

    @FXML
    private ImageView imgView5;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label cityLabel;

    @FXML
    private TextField cityTextField;

    @FXML
    private Label orderDetailsLabel;

    @FXML
    private Label orderNumberLabel;

    @FXML
    private Label performanceLabel1;

    @FXML
    private Label performanceLabel2;

    @FXML
    private Label performanceLabel3;

    @FXML
    private Label performanceLabel4;

    @FXML
    private Label performanceLabel5;

    @FXML
    private Label priceLabel1;

    @FXML
    private Label priceLabel2;

    @FXML
    private Label priceLabel3;

    @FXML
    private Label priceLabel4;

    @FXML
    private Label priceLabel5;

    @FXML
    private Label rowLabel1;

    @FXML
    private Label rowLabel2;

    @FXML
    private Label rowLabel3;

    @FXML
    private Label rowLabel4;

    @FXML
    private Label rowLabel5;

    @FXML
    private Label seatLabel1;

    @FXML
    private Label seatLabel2;

    @FXML
    private Label seatLabel3;

    @FXML
    private Label seatLabel4;

    @FXML
    private Label seatLabel5;

    @FXML
    private Label sectionLabel1;

    @FXML
    private Label sectionLabel2;

    @FXML
    private Label sectionLabel3;

    @FXML
    private Label sectionLabel4;

    @FXML
    private Label sectionLabel5;

    @FXML
    private Label stateLabel;

    @FXML
    private TextField stateTextField;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Label zipLabel;

    @FXML
    private TextField zipTextField;

    private Stage stage;
    private Scene scene;
    private TicketCompany app;
    private Performance performance;
    private ArrayList<Ticket> tickets;
    private String sectionName;

    public void initialize() {
        // Define the CSS for red text and black background
        String redTextStyle = "-fx-text-fill: red;";
        String blackBackgroundStyle = "-fx-background-color: black;";

        // Apply the black background to the GridPane
        gridPane.setStyle(blackBackgroundStyle);

        // Apply the red text style to all labels within the GridPane
        for (Node node : gridPane.lookupAll(".label")) {
            if (node instanceof Label) {
                node.setStyle(redTextStyle);
            }
        }
        // Define the CSS style for the text fields
        String textFieldStyle = "-fx-padding: 5px; " +
                "-fx-font-size: 12px; " +
                "-fx-border-width: 3px; " +
                "-fx-border-color: #ffffff; " +
                "-fx-background-color: #000000; " +
                "-fx-text-fill: #ff0000; " +
                "-fx-border-style: solid; " +
                "-fx-border-radius: 0px;";

        // Apply the style to all text fields within the GridPane
        for (Node node : gridPane.lookupAll(".text-field")) {
            if (node instanceof TextField) {
                node.setStyle(textFieldStyle);
            }
        }
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setApp(TicketCompany app){
        this.app = app;
    }

    public void setPerformance(Performance performance){
        this.performance = performance;
    }
    public void setSectionName(String sectionName){this.sectionName = sectionName;}

    public void setTickets(ArrayList<Ticket> tickets){
        this.tickets = tickets;
        updateCartUI();

    }

    public void removeLabel(Label label){
        gridPane.getChildren().remove(label);
    }
    public void removeLabels5() {
        removeLabel(performanceLabel5);
        removeLabel(sectionLabel5);
        removeLabel(rowLabel5);
        removeLabel(seatLabel5);
        removeLabel(priceLabel5);
        removeButton(buttonTicket5);
        removeLabel(ticket5Label);
        removeLabel(serial5Label);

    }

    public void removeLabels4(){
        removeLabel(performanceLabel4);
        removeLabel(sectionLabel4);
        removeLabel(rowLabel4);
        removeLabel(seatLabel4);
        removeLabel(priceLabel4);
        removeButton(buttonTicket4);
        removeLabel(ticket4Label);
        removeLabel(serial4Label);

    }

    public void setCssStyle(){
        // Define the CSS for red text and black background
        String redTextStyle = "-fx-text-fill: red;";
        String blackBackgroundStyle = "-fx-background-color: black;";

        // Apply the black background to the GridPane
        gridPane.setStyle(blackBackgroundStyle);

        // Apply the red text style to all labels within the GridPane
        for (Node node : gridPane.lookupAll(".label")) {
            if (node instanceof Label) {
                node.setStyle(redTextStyle);
            }
        }
        // Define the CSS style for the text fields
        String textFieldStyle = "-fx-padding: 5px; " +
                "-fx-font-size: 12px; " +
                "-fx-border-width: 3px; " +
                "-fx-border-color: #ffffff; " +
                "-fx-background-color: #000000; " +
                "-fx-text-fill: #ff0000; " +
                "-fx-border-style: solid; " +
                "-fx-border-radius: 0px;";

        // Apply the style to all text fields within the GridPane
        for (Node node : gridPane.lookupAll(".text-field")) {
            if (node instanceof TextField) {
                node.setStyle(textFieldStyle);
            }
        }
    }
    public void removeLabels3(){
        removeLabel(performanceLabel3);
        removeLabel(sectionLabel3);
        removeLabel(rowLabel3);
        removeLabel(seatLabel3);
        removeLabel(priceLabel3);
        removeButton(buttonTicket3);
        removeLabel(ticket3Label);
        removeLabel(serial3Label);
    }
    public void removeLabels2(){
        removeLabel(performanceLabel2);
        removeLabel(sectionLabel2);
        removeLabel(rowLabel2);
        removeLabel(seatLabel2);
        removeLabel(priceLabel2);
        removeButton(buttonTicket2);
        removeLabel(ticket2Label);
        removeLabel(serial2Label);
    }
    public void removeButton(Button button){
        gridPane.getChildren().remove(button);
    }

    public void setTextLabels1(Ticket ticket){
        performanceLabel1.setText(performance.getPerfName());
        sectionLabel1.setText("Section: " + sectionName);
        rowLabel1.setText("Row: " + ticket.getSeat().getRow());
        seatLabel1.setText("Seat: " + ticket.getSeat().getSeatNumber());
        double price = ticket.getSeat().getPrice();
        String formattedPrice = String.format("%.2f", price);
        priceLabel1.setText("Price: $" + formattedPrice);
        buttonTicket1.setText("Remove");
        setCssStyle();
    }
    public void setTextLabels2(Ticket ticket){
        performanceLabel2.setText(performance.getPerfName());
        sectionLabel2.setText("Section: " + sectionName);
        rowLabel2.setText("Row: " + ticket.getSeat().getRow());
        seatLabel2.setText("Seat: " + ticket.getSeat().getSeatNumber());
        double price = ticket.getSeat().getPrice();
        String formattedPrice = String.format("%.2f", price);
        priceLabel2.setText("Price: $" + formattedPrice);
        buttonTicket2.setText("Remove");
        setCssStyle();
    }

    public void setTextLabels3(Ticket ticket){
        performanceLabel3.setText(performance.getPerfName());
        sectionLabel3.setText("Section: " + sectionName);
        rowLabel3.setText("Row: " + ticket.getSeat().getRow());
        seatLabel3.setText("Seat: " + ticket.getSeat().getSeatNumber());
        double price = ticket.getSeat().getPrice();
        String formattedPrice = String.format("%.2f", price);
        priceLabel3.setText("Price: $" + formattedPrice);
        buttonTicket3.setText("Remove");
        setCssStyle();
    }
    public void setTextLabels4(Ticket ticket){
        performanceLabel4.setText(performance.getPerfName());
        sectionLabel4.setText("Section: " + sectionName);
        rowLabel4.setText("Row: " + ticket.getSeat().getRow());
        seatLabel4.setText("Seat: " + ticket.getSeat().getSeatNumber());
        double price = ticket.getSeat().getPrice();
        String formattedPrice = String.format("%.2f", price);
        priceLabel4.setText("Price: $" + formattedPrice);
        buttonTicket4.setText("Remove");
        setCssStyle();
    }

    public void setTextLabels5(Ticket ticket){
        performanceLabel5.setText(performance.getPerfName());
        sectionLabel5.setText("Section: " + sectionName);
        rowLabel5.setText("Row: " + ticket.getSeat().getRow());
        seatLabel5.setText("Seat: " + ticket.getSeat().getSeatNumber());
        double price = ticket.getSeat().getPrice();
        String formattedPrice = String.format("%.2f", price);
        priceLabel5.setText("Price: $" + formattedPrice);
        buttonTicket5.setText("Remove");
        setCssStyle();
    }

    public void updateCartUI() {
        int numberOfTickets = tickets.size();
        int count = 0;
        double total = 0;
        for (Ticket ticket : tickets){
            if (count == 0){
                setTextLabels1(ticket);
                total += ticket.getSeat().getPrice();
                count++;
            } else if (count == 1){
                setTextLabels2(ticket);
                total += ticket.getSeat().getPrice();
                count++;
            } else if (count == 2){
                setTextLabels3(ticket);
                total += ticket.getSeat().getPrice();
                count++;
            } else if (count == 3){
                setTextLabels4(ticket);
                total += ticket.getSeat().getPrice();
                count++;
            } else {
                setTextLabels5(ticket);
                total += ticket.getSeat().getPrice();
            }
        }
        if (tickets.size() != 5){
            if (tickets.size() == 4){
                removeLabels5();
            } else if (tickets.size() == 3){
                removeLabels5();
                removeLabels4();
            } else if (tickets.size() == 2){
                removeLabels5();
                removeLabels4();
                removeLabels3();
            } else if (tickets.size() == 1){
                removeLabels5();
                removeLabels4();
                removeLabels3();
                removeLabels2();
            }
        }

        String formattedTotal = String.format("%.2f", total);
        totalPriceLabel.setText(formattedTotal);
        setCssStyle();

    }


    public void setLabel(Label label, String text){
        label.setText(text);
    }


    public void handleCheckout(){
    }

    private void removeTicketFromCart(Ticket ticket) {
        ticket.getSeat().setHoldStatus("o");
        tickets.remove(ticket);
        updateCartUI();
    }

    public void onButton1Clicked(ActionEvent e) {
        removeTicketFromCart(tickets.get(0));
        updateCartUI();
    }
    public void onButton2Clicked(ActionEvent e){
        removeTicketFromCart(tickets.get(1));
        updateCartUI();
    }
    public void onButton3Clicked(ActionEvent e){
        removeTicketFromCart(tickets.get(2));
        updateCartUI();
    }
    public void onButton4Clicked(ActionEvent e){
        removeTicketFromCart(tickets.get(3));
        updateCartUI();
    }
    public void onButton5Clicked(ActionEvent e){
        removeTicketFromCart((tickets.get(4)));
        updateCartUI();;
    }

    public void hideDetails(){
        nameLabel.setOpacity(0);
        nameTextField.setOpacity(0);
        addressLabel.setOpacity(0);
        addressTextField.setOpacity(0);
        cityLabel.setOpacity(0);
        cityTextField.setOpacity(0);
        stateLabel.setOpacity(0);
        stateTextField.setOpacity(0);
        zipLabel.setOpacity(0);
        zipTextField.setOpacity(0);
        emailLabel.setOpacity(0);
        emailTextField.setOpacity(0);
    }

    public void onCheckOutButtonClicked(ActionEvent e) throws IOException, WriterException {
        hideDetails();
        orderDetailsLabel.setOpacity(1);
        orderNumberLabel.setOpacity(1);
        //String customerName, String cust_add, String cust_City, String cust_State, String cust_Zip, String email
        Customer customer = new Customer(nameTextField.getText(), addressTextField.getText(), cityTextField.getText(),
                stateTextField.getText(), zipTextField.getText(), emailTextField.getText());
        OrderIDManager orderIDManager = new OrderIDManager();
        int orderNum = orderIDManager.getNextOrderID();

        Order order = new Order(orderNum, customer);
        orderNumberLabel.setText("Order# " + String.valueOf(orderNum));

        int count = 0;
        for (Ticket ticket : tickets){
            order.addTicket(ticket);
            ticket.getSeat().setHoldStatus("s");
            if (count == 0){
                ticket1Label.setOpacity(1);
                serial1Label.setText(String.valueOf(ticket.getSerial()));
                serial1Label.setOpacity(1);

                String barCodePath = BarCode.generateBarcode(ticket.getSerial());
                File file = new File(barCodePath);
                String imageURL = file.toURI().toURL().toExternalForm();
                Image image = new Image(imageURL);
                imgView1.setImage(image);
                imgView1.setFitWidth(100); // Set the desired width
                imgView1.setFitHeight(30); // Set the desired height
                imgView1.setPreserveRatio(true); // Optional, to preserve the aspect ratio
                count++;
            } else if (count == 1){
                ticket2Label.setOpacity(1);
                serial2Label.setText(String.valueOf(ticket.getSerial()));
                serial2Label.setOpacity(1);
                String barCodePath = BarCode.generateBarcode(ticket.getSerial());
                File file = new File(barCodePath);
                String imageURL = file.toURI().toURL().toExternalForm();
                Image image = new Image(imageURL);
                imgView2.setImage(image);
                imgView2.setFitWidth(100); // Set the desired width
                imgView2.setFitHeight(30); // Set the desired height
                imgView2.setPreserveRatio(true); // Optional, to preserve the aspect ratio
                count++;
            } else if (count == 2){
                ticket3Label.setOpacity(1);
                serial3Label.setText(String.valueOf(ticket.getSerial()));
                serial3Label.setOpacity(1);
                String barCodePath = BarCode.generateBarcode(ticket.getSerial());
                File file = new File(barCodePath);
                String imageURL = file.toURI().toURL().toExternalForm();
                Image image = new Image(imageURL);
                imgView3.setImage(image);
                imgView3.setFitWidth(100); // Set the desired width
                imgView3.setFitHeight(30); // Set the desired height
                imgView3.setPreserveRatio(true); // Optional, to preserve the aspect ratio
                count++;
            } else if (count == 3){
                ticket4Label.setOpacity(1);
                serial4Label.setText(String.valueOf(ticket.getSerial()));
                serial4Label.setOpacity(1);
                String barCodePath = BarCode.generateBarcode(ticket.getSerial());
                File file = new File(barCodePath);
                String imageURL = file.toURI().toURL().toExternalForm();
                Image image = new Image(imageURL);
                imgView4.setImage(image);
                imgView4.setFitWidth(100); // Set the desired width
                imgView4.setFitHeight(30); // Set the desired height
                imgView4.setPreserveRatio(true); // Optional, to preserve the aspect ratio
                count++;
            } else {
                ticket5Label.setOpacity(1);
                serial5Label.setText(String.valueOf(ticket.getSerial()));
                serial5Label.setOpacity(1);
                String barCodePath = BarCode.generateBarcode(ticket.getSerial());
                File file = new File(barCodePath);
                String imageURL = file.toURI().toURL().toExternalForm();
                Image image = new Image(imageURL);
                imgView5.setImage(image);
                imgView5.setFitWidth(100); // Set the desired width
                imgView5.setFitHeight(30); // Set the desired height
                imgView5.setPreserveRatio(true); // Optional, to preserve the aspect ratio
            }
        }

    }


    public Stage getStage() {
        return stage;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }
}
