package com.example.bonisrl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TimeZone;
import java.sql.*;

public class NewEmployeeController {

    @FXML private DatePicker birthdayDatePicker;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;

    @FXML
    void handleAddEmployee(ActionEvent event) {

    }

    @FXML
    void handleCancelOperation(ActionEvent event) {

    }
}
