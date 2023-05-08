package com.example.bonisrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.TimeZone;
import java.sql.*;

public class FirstPageController {


    @FXML private BorderPane root;
    @FXML
    void handleQuit(ActionEvent event) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Logout");
        alert1.setHeaderText("Sicuro?");
        alert1.setContentText("Vuoi effettuare il logout?");
        alert1.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
            } else {

            }
        });
    }
}
