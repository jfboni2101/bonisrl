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
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
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

    private ObservableList<Person> employee;

    @FXML private TableView<Person> tableClient;
    @FXML private TableColumn<Person, String> lastNameClient;
    @FXML private TableColumn<Person, String> firstNameClient;
    @FXML private TableColumn<Person, String> birthdayClient;

    private ObservableList<Person> client;

    @FXML private TableView<TypeOfJob> tableTypeOfJob;
    @FXML private TableColumn<TypeOfJob, String> nameOfJob;
    @FXML private TableColumn<TypeOfJob, String> descriptionOfJob;

    private ObservableList<TypeOfJob> type;

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

    private ObservableList<Job> job;

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
        employee = FXCollections.observableArrayList();
        try {
            Class.forName(JDBC_Driver_MySQL);
            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
            PreparedStatement statement = c.prepareStatement("SELECT E.* FROM Employee AS E");

            ResultSet rs = statement.executeQuery();
            String firstname;
            String lastname;
            LocalDate birthday;

            while (rs.next()) {
                lastname = rs.getString("lastName");
                firstname = rs.getString("firstName");
                birthday = LocalDate.parse(rs.getString("birthday"));
                employee.add(new Person(lastname, firstname, birthday));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }
    public ObservableList<Person> getClientsData() {
        client = FXCollections.observableArrayList();
        try {
            Class.forName(JDBC_Driver_MySQL);
            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
            PreparedStatement statement = c.prepareStatement("SELECT C.* FROM Client AS C");

            ResultSet rs = statement.executeQuery();
            String firstname;
            String lastname;
            LocalDate birthday;

            while (rs.next()) {
                lastname = rs.getString("lastName");
                firstname = rs.getString("firstName");
                birthday = LocalDate.parse(rs.getString("birthday"));
                client.add(new Person(lastname, firstname, birthday));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return client;
    }
    public ObservableList<TypeOfJob> getTypeData() {
        type = FXCollections.observableArrayList();
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
                type.add(new TypeOfJob(name, description));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return type;
    }
    public ObservableList<Job> getJobData() {
        job = FXCollections.observableArrayList();
        try {
            Class.forName(JDBC_Driver_MySQL);
            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
            PreparedStatement statement = c.prepareStatement(   "SELECT T.name, C.lastName AS 'lastNameClient', C.firstName AS 'firstNameClient', E.lastName AS 'lastNameEmployee', E.firstName AS 'firstNameEmployee', J.dateOfJob, J.hours,  J.size, J.address\n" + "FROM Job AS J\n" +
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
                nameType = rs.getString("name");
                lastNameClient = rs.getString("lastNameClient");
                firstNameClient = rs.getString("firstNameClient");
                lastNameEmployee = rs.getString("lastNameEmployee");
                firstNameEmployee = rs.getString("firstNameEmployee");
                dateOfJob = rs.getDate("dateOfJob").toLocalDate();
                hours = rs.getTime("hours").toLocalTime();
                size = rs.getInt("size");
                address = rs.getString("address");
                job.add(new Job(nameType, firstNameClient, lastNameClient, firstNameEmployee, lastNameEmployee,
                         dateOfJob, hours, size, address));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return job;
    }

    @FXML
    void handleAddEmployee(ActionEvent event) {
        try {
            String firstName, lastName;
            LocalDate birthday;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add-employee.fxml"));
            DialogPane view = loader.load();
            NewPersonController controller = loader.getController();

            // Set an empty person into the controller
            controller.setPerson(new Person());
            while(true) {
            // Create the dialog to add an Employee
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("New Employee");
                dialog.initModality(Modality.WINDOW_MODAL);
                dialog.setDialogPane(view);

                // Show the dialog and wait until the user closes it
                Optional<ButtonType> clickedButton = dialog.showAndWait();
                if (clickedButton.orElse(ButtonType.CANCEL) == ButtonType.OK) {
                    firstName = controller.getPerson().getFirstName();
                    lastName = controller.getPerson().getLastName();
                    birthday = controller.getPerson().getBirthday();
                    //Control about the inserted variables
                    if (firstName == "NULL" || lastName == "NULL" || LocalDate.now().getYear() - birthday.getYear() < 18 || (LocalDate.now().getYear() - birthday.getYear() == 18 && LocalDate.now().getMonth().compareTo(birthday.getMonth()) < 0) || (LocalDate.now().getYear() - birthday.getYear() == 18 && LocalDate.now().getDayOfYear() - birthday.getDayOfYear() < 0)) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Inserimento non corretto!");
                        alert2.setHeaderText("Inserimento non corretto");
                        alert2.setContentText("Hai sbagliato a scrivere il nome o il cognome o la data non è da maggiorenne");
                        alert2.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {

                            } else {

                            }
                        });
                        continue;
                    } else {
                        Person newEmployee = controller.getPerson();
                        employee.add(newEmployee);
                        //Add the new Employee
                        try {
                            Class.forName(JDBC_Driver_MySQL);
                            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
                            PreparedStatement statement = c.prepareStatement("INSERT INTO Employee (lastName, firstName, birthday) " + "VALUES (?,?,?);");
                            statement.setString(1, lastName);
                            statement.setString(2, firstName);
                            statement.setString(3, String.valueOf(birthday));
                            statement.executeUpdate();
                            break;
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handleAddClient(ActionEvent event) {
        try {
            String firstName, lastName;
            LocalDate birthday;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add-client.fxml"));
            DialogPane view = loader.load();
            NewPersonController controller = loader.getController();

            // Set an empty person into the controller
            controller.setPerson(new Person());
            while(true) {
                // Create the dialog to add an Employee
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("New Client");
                dialog.initModality(Modality.WINDOW_MODAL);
                dialog.setDialogPane(view);

                // Show the dialog and wait until the user closes it
                Optional<ButtonType> clickedButton = dialog.showAndWait();
                if (clickedButton.orElse(ButtonType.CANCEL) == ButtonType.OK) {
                    firstName = controller.getPerson().getFirstName();
                    lastName = controller.getPerson().getLastName();
                    birthday = controller.getPerson().getBirthday();
                    //Control about the inserted variables
                    if (firstName == "NULL" || lastName == "NULL" || LocalDate.now().getYear() - birthday.getYear() < 18 || (LocalDate.now().getYear() - birthday.getYear() == 18 && LocalDate.now().getMonth().compareTo(birthday.getMonth()) < 0) || (LocalDate.now().getYear() - birthday.getYear() == 18 && LocalDate.now().getDayOfYear() - birthday.getDayOfYear() < 0)) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Inserimento non corretto!");
                        alert2.setHeaderText("Inserimento non corretto");
                        alert2.setContentText("Hai sbagliato a scrivere il nome o il cognome o la data non è da maggiorenne");
                        alert2.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {

                            } else {

                            }
                        });
                        continue;
                    } else {
                        Person newClient = controller.getPerson();
                        client.add(newClient);
                        //Add the new Employee
                        try {
                            Class.forName(JDBC_Driver_MySQL);
                            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
                            PreparedStatement statement = c.prepareStatement("INSERT INTO Client (lastName, firstName, birthday) " + "VALUES (?,?,?);");
                            statement.setString(1, lastName);
                            statement.setString(2, firstName);
                            statement.setString(3, String.valueOf(birthday));
                            statement.executeUpdate();
                            break;
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handleAddType(ActionEvent event) {
        try {
            String name, description;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add-type.fxml"));
            DialogPane view = loader.load();
            NewTypeController controller = loader.getController();

            // Set an empty person into the controller
            controller.set(new TypeOfJob());
            while(true) {
                // Create the dialog to add an Employee
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("New Type Of Job");
                dialog.initModality(Modality.WINDOW_MODAL);
                dialog.setDialogPane(view);

                // Show the dialog and wait until the user closes it
                Optional<ButtonType> clickedButton = dialog.showAndWait();
                if (clickedButton.orElse(ButtonType.CANCEL) == ButtonType.OK) {
                    name = controller.getType().getName();
                    description = controller.getType().getDescription();
                    //Control about the inserted variables
                    if (name == "") {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Inserimento non corretto!");
                        alert2.setHeaderText("Inserimento non corretto");
                        alert2.setContentText("Hai sbagliato a scrivere il nome del tipo di lavoro");
                        alert2.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {

                            } else {

                            }
                        });
                        continue;
                    } else {
                        TypeOfJob newType = controller.getType();
                        type.add(newType);
                        //Add the new Employee
                        try {
                            Class.forName(JDBC_Driver_MySQL);
                            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
                            PreparedStatement statement = c.prepareStatement("INSERT INTO Type (name, description)" +
                                    "VALUES (?,?);");
                            statement.setString(1, name);
                            statement.setString(2, description);
                            statement.executeUpdate();
                            break;
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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


