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

public class FirstPageController {

    public static final String JDBC_Driver_MySQL = "com.mysql.cj.jdbc.Driver";
    public static final String JDBC_URL_MySQL = "jdbc:mysql://localhost:3306/boni_srl?user=boniSrl&password" +
            "=Magamago2101!" + "&serverTimezone=" + TimeZone.getDefault().getID();

    @FXML private BorderPane root;


    @FXML private TableView<Person> tableEmployee;
    @FXML private TableColumn<Person, String> lastNameEmployee;
    @FXML private TableColumn<Person, String> firstNameEmployee;
    @FXML private TableColumn<Person, LocalDate> birthdayEmployee;

    @FXML private TableView<Person> tableClient;
    @FXML private TableColumn<Person, String> lastNameClient;
    @FXML private TableColumn<Person, String> firstNameClient;
    @FXML private TableColumn<Person, String> birthdayClient;


    @FXML private TableView<Person> tableJob;
    @FXML private TableColumn<Person, String> lastNameClientJob;
    @FXML private TableColumn<Person, String> firstNameClientJob;
    @FXML private TableColumn<Person, String> lastNameEmployeeJob;
    @FXML private TableColumn<Person, String> firstNameEmployeeJob;
    @FXML private TableColumn<Person, LocalDate> dateOfJob;
    @FXML private TableColumn<Person, LocalTime> hoursOfJob;
    @FXML private TableColumn<Person, String> typeOfJob;

    @FXML private TableView<TypeOfJob> tableTypeOfJob;
    @FXML private TableColumn<TypeOfJob, String> nameOfJob;
    @FXML private TableColumn<TypeOfJob, String> descriptionOfJob;

    @FXML
    public void initialize() {

        lastNameEmployee.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameEmployee.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        birthdayEmployee.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        tableEmployee.setItems(getEmployeeData());

        lastNameClient.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameClient.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        birthdayClient.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        tableClient.setItems(getClientsData());

        nameOfJob.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionOfJob.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableTypeOfJob.setItems(getTypeData());

    }



    public ObservableList<Person> getEmployeeData() {
        ObservableList<Person> persons = FXCollections.observableArrayList();
        try {
            Class.forName(JDBC_Driver_MySQL);
            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
            PreparedStatement statement = c.prepareStatement("SELECT E.* FROM Employee AS E");

            ResultSet rs = statement.executeQuery();
            String firstname;
            String lastname;
            String birthday;

            while (rs.next()) {
                lastname = rs.getString("lastName");
                firstname = rs.getString("firstName");
                birthday = rs.getString("birthday");
                persons.add(new Person(lastname, firstname, birthday));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    public ObservableList<Person> getClientsData() {
        ObservableList<Person> persons = FXCollections.observableArrayList();
        try {
            Class.forName(JDBC_Driver_MySQL);
            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
            PreparedStatement statement = c.prepareStatement("SELECT C.* FROM Client AS C");

            ResultSet rs = statement.executeQuery();
            String firstname;
            String lastname;
            String birthday;

            while (rs.next()) {
                lastname = rs.getString("lastName");
                firstname = rs.getString("firstName");
                birthday = rs.getString("birthday");
                persons.add(new Person(lastname, firstname, birthday));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    public ObservableList<TypeOfJob> getTypeData() {
        ObservableList<TypeOfJob> persons = FXCollections.observableArrayList();
        try {
            Class.forName(JDBC_Driver_MySQL);
            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
            PreparedStatement statement = c.prepareStatement("SELECT T.* FROM Type AS T");

            ResultSet rs = statement.executeQuery();
            String name;
            String description;

            while (rs.next()) {
                name = rs.getString("name");
                description = rs.getString("description");
                persons.add(new TypeOfJob(name, description));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    public ObservableList<Person> getJobData() {
        ObservableList<Person> persons = FXCollections.observableArrayList();
        try {
            Class.forName(JDBC_Driver_MySQL);
            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
            PreparedStatement statement = c.prepareStatement("SELECT C.* FROM Client AS C");

            ResultSet rs = statement.executeQuery();
            String firstname;
            String lastname;
            String birthday;

            while (rs.next()) {
                lastname = rs.getString("lastName");
                firstname = rs.getString("firstName");
                birthday = rs.getString("birthday");
                persons.add(new Person(lastname, firstname, birthday));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }
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
