module test {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires controlsfx;
    requires java.mail;
    requires stripe.java;
    requires org.apache.pdfbox;
    requires java.desktop;
    opens entity;

    opens test to javafx.fxml;
    exports test;
    exports Controller;
    opens Controller to javafx.fxml;
}