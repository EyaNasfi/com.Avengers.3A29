module test {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens test to javafx.fxml;
    exports test;
    exports Controller;
    opens Controller to javafx.fxml;
}