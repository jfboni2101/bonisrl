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

    @FXML private TableView<TypeOfJob> tableTypeOfJob;
    @FXML private TableColumn<TypeOfJob, String> nameOfJob;
    @FXML private TableColumn<TypeOfJob, String> descriptionOfJob;

    @FXML private TableView<Job> tableJob;
    @FXML private TableColumn<Job, String> lastNameClientJob;
    @FXML private TableColumn<Job, String> firstNameClientJob;
    @FXML private TableColumn<Job, String> lastNameEmployeeJob;
    @FXML private TableColumn<Job, String> firstNameEmployeeJob;
    @FXML private TableColumn<Job, LocalDate> dateOfJob;
    @FXML private TableColumn<Job, LocalTime> hoursOfJob;
    @FXML private TableColumn<Job, Integer> sizeOfJob;
    @FXML private TableColumn<Job, Integer> addressOfJob;
    @FXML private TableColumn<Job, String> typeOfJob;



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

        typeOfJob.setCellValueFactory(new PropertyValueFactory<>("nameType"));
        lastNameClientJob.setCellValueFactory(new PropertyValueFactory<>("lastNameClient"));
        firstNameClientJob.setCellValueFactory(new PropertyValueFactory<>("firstNameClient"));
        lastNameEmployeeJob.setCellValueFactory(new PropertyValueFactory<>("lastNameEmployee"));
        firstNameEmployeeJob.setCellValueFactory(new PropertyValueFactory<>("firstNameEmployee"));
        dateOfJob.setCellValueFactory(new PropertyValueFactory<>("dateOfJob"));
        hoursOfJob.setCellValueFactory(new PropertyValueFactory<>("hours"));
        sizeOfJob.setCellValueFactory(new PropertyValueFactory<>("size"));
        addressOfJob.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableJob.setItems(getJobData());

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
        ObservableList<TypeOfJob> types = FXCollections.observableArrayList();
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
                types.add(new TypeOfJob(name, description));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return types;
    }

    public ObservableList<Job> getJobData() {
        ObservableList<Job> jobs = FXCollections.observableArrayList();
        try {
            Class.forName(JDBC_Driver_MySQL);
            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
            PreparedStatement statement = c.prepareStatement(   "SELECT T.name, C.lastName, C.firstName, E.lastName, E.firstName, J.dateOfJob, J.hours,  J.size, J.address\n" +
                                                                    "FROM Job AS J\n" +
                                                                    "JOIN Client AS C ON (C._id=J.idClient)\n" +
                                                                    "JOIN Employee AS E ON (E._id=J.idEmployee)\n" +
                                                                    "JOIN Type AS T ON (T.name=J.nameType);");

            ResultSet rs = statement.executeQuery();
            String nameType;
            String firstNameClient;
            String lastNameClient;
            String firstNameEmployee;
            String lastNameEmployee;
            LocalDate dateOfJob;
            LocalTime hours;
            float size;
            String address;

            while (rs.next()) {
                nameType = rs.getString(1);
                firstNameClient = rs.getString(2);
                lastNameClient = rs.getString(3);
                firstNameEmployee = rs.getString(4);
                lastNameEmployee = rs.getString(5);
                dateOfJob = rs.getDate(6).toLocalDate();
                hours = rs.getTime(7).toLocalTime();
                size = rs.getInt(8);
                address = rs.getString(9);
                jobs.add(new Job(nameType, firstNameClient, lastNameClient, firstNameEmployee, lastNameEmployee,
                         dateOfJob, hours, size, address));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return jobs;
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
