module com.example.bonisrl {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.zaxxer.hikari;
    requires java.sql;

    opens com.example.bonisrl to javafx.fxml;
    exports com.example.bonisrl;
}