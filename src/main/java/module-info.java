module com.example.ticketapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.zxing;
    requires com.google.zxing.javase;

    opens com.example.ticketapp to javafx.fxml;
    exports com.example.ticketapp;
}