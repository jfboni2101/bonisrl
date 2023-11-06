package com.example.bonisrl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
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
    @FXML private TableColumn<Job, Integer> idJob;
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

        idJob.setCellValueFactory(new PropertyValueFactory<>("idJob"));
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
            String firstname, lastname;
            Integer id;
            LocalDate birthday;

            while (rs.next()) {
                id = rs.getInt("_id");
                lastname = rs.getString("lastName");
                firstname = rs.getString("firstName");
                birthday = LocalDate.parse(rs.getString("birthday"));
                employee.add(new Person(id, lastname, firstname, birthday));
            }
        } catch (SQLException e) {
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("ERRORE!");
            alert3.setHeaderText("Errore SQL");
            alert3.setContentText("C'è stato un errore nell'SQL");
            alert3.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert alert4 = new Alert(Alert.AlertType.ERROR);
            alert4.setTitle("ERRORE!");
            alert4.setHeaderText("Errore nella creazione Class.forName()");
            alert4.setContentText("C'è stato un errore nella creazione di Class.forName()");
            alert4.showAndWait();
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
            String firstname, lastname;
            Integer id;
            LocalDate birthday;

            while (rs.next()) {
                id = rs.getInt("_id");
                lastname = rs.getString("lastName");
                firstname = rs.getString("firstName");
                birthday = LocalDate.parse(rs.getString("birthday"));
                client.add(new Person(id, lastname, firstname, birthday));
            }
        } catch (SQLException e) {
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("ERRORE!");
            alert3.setHeaderText("Errore SQL");
            alert3.setContentText("C'è stato un errore nell'SQL");
            alert3.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert alert4 = new Alert(Alert.AlertType.ERROR);
            alert4.setTitle("ERRORE!");
            alert4.setHeaderText("Errore nella creazione Class.forName()");
            alert4.setContentText("C'è stato un errore nella creazione di Class.forName()");
            alert4.showAndWait();
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
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("ERRORE!");
            alert3.setHeaderText("Errore SQL");
            alert3.setContentText("C'è stato un errore nell'SQL");
            alert3.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert alert4 = new Alert(Alert.AlertType.ERROR);
            alert4.setTitle("ERRORE!");
            alert4.setHeaderText("Errore nella creazione Class.forName()");
            alert4.setContentText("C'è stato un errore nella creazione di Class.forName()");
            alert4.showAndWait();
        }
        return type;
    }
    public ObservableList<Job> getJobData() {
        job = FXCollections.observableArrayList();
        try {
            Class.forName(JDBC_Driver_MySQL);
            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);

            PreparedStatement statement = c.prepareStatement("""
                    SELECT J._id AS 'id', T.name AS 'name', C._id AS 'idClient',  C.lastName AS 'lastNameClient', C.firstName AS 'firstNameClient', E._id AS 'idEmployee', E.lastName AS 'lastNameEmployee', E.firstName AS 'firstNameEmployee', J.dateOfJob, J.hours,  J.size, J.address
                    FROM Job AS J
                    JOIN Client AS C ON (C._id=J.idClient)
                    JOIN Employee AS E ON (E._id=J.idEmployee)
                    JOIN Type AS T ON (T.name=J.nameType)
                    ORDER BY J._id;""");

            ResultSet rs = statement.executeQuery();
            Integer id;
            String nameType;
            Integer idClient;
            String firstNameClient;
            String lastNameClient;
            Integer idEmployee;
            String firstNameEmployee;
            String lastNameEmployee;
            LocalDate dateOfJob;
            Float hours;
            Float size;
            String address;

            while (rs.next()) {
                id = rs.getInt("id");
                nameType = rs.getString("name");
                idClient = rs.getInt("idClient");
                firstNameClient = rs.getString("firstNameClient");
                lastNameClient = rs.getString("lastNameClient");
                idEmployee = rs.getInt("idEmployee");
                firstNameEmployee = rs.getString("firstNameEmployee");
                lastNameEmployee = rs.getString("lastNameEmployee");
                dateOfJob = rs.getDate("dateOfJob").toLocalDate();
                hours = rs.getFloat("hours");
                size = rs.getFloat("size");
                address = rs.getString("address");
                job.add(new Job(id, nameType, idClient, firstNameClient, lastNameClient, idEmployee, firstNameEmployee,
                        lastNameEmployee, dateOfJob, hours,
                        size, address));
            }
        } catch (SQLException e) {
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("ERRORE!");
            alert3.setHeaderText("Errore SQL");
            alert3.setContentText("C'è stato un errore nell'SQL");
            alert3.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert alert4 = new Alert(Alert.AlertType.ERROR);
            alert4.setTitle("ERRORE!");
            alert4.setHeaderText("Errore nella creazione Class.forName()");
            alert4.setContentText("C'è stato un errore nella creazione di Class.forName()");
            alert4.showAndWait();
        }
        return job;
    }

    @FXML
    void handleAddEmployee(ActionEvent event) {
        try {
            String firstName, lastName;
            LocalDate birthday;
            Integer id;
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
                    if (firstName.equals("NULL") || lastName.equals("NULL") || LocalDate.now().getYear() - birthday.getYear() < 18 || (LocalDate.now().getYear() - birthday.getYear() == 18 && LocalDate.now().getMonth().compareTo(birthday.getMonth()) < 0) || (LocalDate.now().getYear() - birthday.getYear() == 18 && LocalDate.now().getDayOfYear() - birthday.getDayOfYear() < 0)) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Inserimento non corretto!");
                        alert2.setHeaderText("Inserimento non corretto");
                        alert2.setContentText("Hai sbagliato a scrivere il nome o il cognome o la data non è da maggiorenne");
                        alert2.showAndWait();
                    } else {
                        Person newEmployee = controller.getPerson();

                        //Add the new Employee
                        try {
                            Class.forName(JDBC_Driver_MySQL);
                            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
                            PreparedStatement statement = c.prepareStatement("INSERT INTO Employee (lastName, firstName, birthday) VALUES (?,?,?);");
                            statement.setString(1, lastName);
                            statement.setString(2, firstName);
                            statement.setString(3, String.valueOf(birthday));
                            statement.executeUpdate();

                            statement = c.prepareStatement("SELECT _id  FROM Employee WHERE lastName = ? AND " +
                                    "firstName =" +
                                    " ? AND birthday = ?");
                            statement.setString(1, lastName);
                            statement.setString(2, firstName);
                            statement.setString(3, String.valueOf(birthday));
                            ResultSet rs = statement.executeQuery();
                            rs.next();
                            id = rs.getInt("_id");
                            newEmployee.setIdPerson(id);
                            employee.add(newEmployee);
                            break;
                        } catch (SQLException e) {
                            Alert alert3 = new Alert(Alert.AlertType.ERROR);
                            alert3.setTitle("ERRORE!");
                            alert3.setHeaderText("Errore SQL");
                            alert3.setContentText("C'è stato un errore nell'SQL");
                            alert3.showAndWait();
                        } catch (ClassNotFoundException e) {
                            Alert alert4 = new Alert(Alert.AlertType.ERROR);
                            alert4.setTitle("ERRORE!");
                            alert4.setHeaderText("Errore nella creazione Class.forName()");
                            alert4.setContentText("C'è stato un errore nella creazione di Class.forName()");
                            alert4.showAndWait();
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
            Integer id;
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
                    if (firstName.equals("NULL") || lastName.equals("NULL") || LocalDate.now().getYear() - birthday.getYear() < 18 || (LocalDate.now().getYear() - birthday.getYear() == 18 && LocalDate.now().getMonth().compareTo(birthday.getMonth()) < 0) || (LocalDate.now().getYear() - birthday.getYear() == 18 && LocalDate.now().getDayOfYear() - birthday.getDayOfYear() < 0)) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Inserimento non corretto!");
                        alert2.setHeaderText("Inserimento non corretto");
                        alert2.setContentText("Hai sbagliato a scrivere il nome o il cognome o la data non è da maggiorenne");
                        alert2.showAndWait();
                    } else {
                        Person newClient = controller.getPerson();
                        //Add the new Employee
                        try {
                            Class.forName(JDBC_Driver_MySQL);
                            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
                            PreparedStatement statement = c.prepareStatement("INSERT INTO Client (lastName, firstName, birthday) VALUES (?,?,?);");
                            statement.setString(1, lastName);
                            statement.setString(2, firstName);
                            statement.setString(3, String.valueOf(birthday));
                            statement.executeUpdate();

                            statement = c.prepareStatement("SELECT _id  FROM Client WHERE lastName = ? AND " +
                                    "firstName =" +
                                    " ? AND birthday = ?");
                            statement.setString(1, lastName);
                            statement.setString(2, firstName);
                            statement.setString(3, String.valueOf(birthday));
                            ResultSet rs = statement.executeQuery();
                            rs.next();
                            id = rs.getInt("_id");
                            newClient.setIdPerson(id);
                            client.add(newClient);


                            break;
                        } catch (SQLException e) {
                            Alert alert3 = new Alert(Alert.AlertType.ERROR);
                            alert3.setTitle("ERRORE!");
                            alert3.setHeaderText("Errore SQL");
                            alert3.setContentText("C'è stato un errore nell'SQL");
                            alert3.showAndWait();
                        } catch (ClassNotFoundException e) {
                            Alert alert4 = new Alert(Alert.AlertType.ERROR);
                            alert4.setTitle("ERRORE!");
                            alert4.setHeaderText("Errore nella creazione Class.forName()");
                            alert4.setContentText("C'è stato un errore nella creazione di Class.forName()");
                            alert4.showAndWait();
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
                    if (name.equals("")) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Inserimento non corretto!");
                        alert2.setHeaderText("Inserimento non corretto");
                        alert2.setContentText("Hai sbagliato a scrivere il nome del tipo di lavoro");
                        alert2.showAndWait();
                    } else {
                        TypeOfJob newType = controller.getType();
                        type.add(newType);
                        //Add the new Employee
                        try {
                            Class.forName(JDBC_Driver_MySQL);
                            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
                            PreparedStatement statement = c.prepareStatement("INSERT INTO Type (name, description) VALUES (?,?);");
                            statement.setString(1, name);
                            statement.setString(2, description);
                            statement.executeUpdate();
                            break;
                        } catch (SQLException e) {
                            Alert alert3 = new Alert(Alert.AlertType.ERROR);
                            alert3.setTitle("ERRORE!");
                            alert3.setHeaderText("Errore SQL");
                            alert3.setContentText("C'è stato un errore nell'SQL");
                            alert3.showAndWait();
                        } catch (ClassNotFoundException e) {
                            Alert alert4 = new Alert(Alert.AlertType.ERROR);
                            alert4.setTitle("ERRORE!");
                            alert4.setHeaderText("Errore nella creazione Class.forName()");
                            alert4.setContentText("C'è stato un errore nella creazione di Class.forName()");
                            alert4.showAndWait();
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
    void handleAddJob(ActionEvent event) {
        try {
            String name, address, nameEmployee, lastnameEmployee, nameClient, lastnameClient;
            Integer idJob, idClient, idEmployee;
            Float size, hours;
            LocalDate dateOfJob;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add-job.fxml"));
            DialogPane view = loader.load();
            NewJobController controller = loader.getController();

            // Set an empty person into the controller
            controller.setJob(new Job());
            while(true) {
                // Create the dialog to add an Employee
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("New Job");
                dialog.initModality(Modality.WINDOW_MODAL);
                dialog.setDialogPane(view);

                // Show the dialog and wait until the user closes it
                Optional<ButtonType> clickedButton = dialog.showAndWait();
                if (clickedButton.orElse(ButtonType.CANCEL) == ButtonType.OK) {
                    name = controller.getJob().getNameType();
                    idClient = controller.getJob().getIdClient();
                    idEmployee = controller.getJob().getIdEmployee();
                    address = controller.getJob().getAddress();
                    size = controller.getJob().getSize();
                    dateOfJob = controller.getJob().getDateOfJob();
                    hours = controller.getJob().getHours();
                    //Control about the inserted variables
                    if (name.equals("") || idClient == 0 || idEmployee == 0 || address.equals("") || size <= 0 || dateOfJob.equals(null) || hours <= 0) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Inserimento non corretto!");
                        alert2.setHeaderText("Inserimento non corretto");
                        alert2.setContentText("Hai sbagliato a scrivere qualcosa");
                        alert2.showAndWait();
                    } else {

                        //Add the new Employee
                        try {
                            Class.forName(JDBC_Driver_MySQL);
                            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
                            PreparedStatement statement = c.prepareStatement(   "SELECT firstName AS 'firstName', " +
                                    "lastName AS 'lastName'  FROM Employee " +
                                    "WHERE _id = ?");
                            statement.setString(1, String.valueOf(idEmployee));
                            ResultSet rs = statement.executeQuery();
                            rs.next();
                            nameEmployee = rs.getString("firstName");
                            lastnameEmployee = rs.getString("lastName");

                            statement = c.prepareStatement(   "SELECT firstName AS 'firstName', " +
                                    "lastName AS 'lastName'  FROM Client " +
                                    "WHERE _id = ?");
                            statement.setString(1, String.valueOf(idClient));
                            rs = statement.executeQuery();
                            rs.next();
                            nameClient = rs.getString("firstName");
                            lastnameClient = rs.getString("lastName");

                            statement = c.prepareStatement("INSERT INTO Job (nameType, idClient, " +
                                    "idEmployee, size, address, dateOfJob, hours) VALUES (?,?,?,?,?,?,?);");
                            statement.setString(1, name);
                            statement.setInt(2, idClient);
                            statement.setInt(3, idEmployee);
                            statement.setFloat(4, size);
                            statement.setString(5, address);
                            statement.setDate(6, Date.valueOf(dateOfJob));
                            statement.setFloat(7, hours);
                            statement.executeUpdate();

                            System.out.println("sono qui1");

                            statement = c.prepareStatement(   "SELECT _id AS 'id' FROM Job AS J WHERE J.nameType = ? " +
                                    "AND J.idClient = ? AND J.idEmployee = ? AND J.size = ? AND J.address = ? AND J.dateOfJob = ? AND J.hours = ?;");
                            statement.setString(1, name);
                            statement.setInt(2, idClient);
                            statement.setInt(3, idEmployee);
                            statement.setFloat(4, size);
                            statement.setString(5, address);
                            statement.setDate(6, Date.valueOf(dateOfJob));
                            statement.setFloat(7, hours);
                            rs = statement.executeQuery();
                            if (rs.next()) {
                                idJob = rs.getInt("id");
                            }else {
                                idJob = -1;
                            }

                            System.out.println("sono qui2");


                            Job newJob = new Job(idJob, name, idClient, nameClient, lastnameClient, idEmployee,
                                    nameEmployee,
                                    lastnameEmployee, dateOfJob, hours, size, address);
                            job.add(newJob);
                            break;
                        } catch (SQLException e) {
                            Alert alert3 = new Alert(Alert.AlertType.ERROR);
                            alert3.setTitle("ERRORE!");
                            alert3.setHeaderText("Errore SQL");
                            alert3.setContentText("C'è stato un errore nell'SQL");
                            alert3.showAndWait();
                        } catch (ClassNotFoundException e) {
                            Alert alert4 = new Alert(Alert.AlertType.ERROR);
                            alert4.setTitle("ERRORE!");
                            alert4.setHeaderText("Errore nella creazione Class.forName()");
                            alert4.setContentText("C'è stato un errore nella creazione di Class.forName()");
                            alert4.showAndWait();
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
    void handleDeleteEmployee(ActionEvent event) {
        try {
            Integer id;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("delete-employee.fxml"));
            DialogPane view = loader.load();
            DeleteEmployeeController controller = loader.getController();

            // Set an empty person into the controller
            controller.setPerson(new Person());
            while(true) {
                // Create the dialog to delete an Employee
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("New Employee");
                dialog.initModality(Modality.WINDOW_MODAL);
                dialog.setDialogPane(view);

                // Show the dialog and wait until the user closes it
                Optional<ButtonType> clickedButton = dialog.showAndWait();
                if (clickedButton.orElse(ButtonType.CANCEL) == ButtonType.OK) {
                    id = controller.getPerson().getIdPerson();
                    //Control about the inserted variables
                    if (id.equals(0)) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Inserimento non corretto!");
                        alert2.setHeaderText("Inserimento non corretto");
                        alert2.setContentText("Non hai selezionato un lavoratore, se non ne sono presenti è perché " +
                                "hanno effettuato un lavoro");
                        alert2.showAndWait();
                    } else {
                        Person newPerson = null;
                        for(Person p : employee) {
                            if(p.getIdPerson().equals(id)) {
                                newPerson = p;
                                break;
                            }
                        }
                        employee.remove(newPerson);
                        //Add the new Employee
                        try {
                            Class.forName(JDBC_Driver_MySQL);
                            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
                            PreparedStatement statement = c.prepareStatement("DELETE FROM Employee\n" + "WHERE _id = " +
                                    "?;");
                            statement.setString(1, String.valueOf(id));
                            statement.executeUpdate();
                            break;
                        } catch (SQLException e) {
                            Alert alert3 = new Alert(Alert.AlertType.ERROR);
                            alert3.setTitle("ERRORE!");
                            alert3.setHeaderText("Errore SQL");
                            alert3.setContentText("C'è stato un errore nell'SQL");
                            alert3.showAndWait();
                        } catch (ClassNotFoundException e) {
                            Alert alert4 = new Alert(Alert.AlertType.ERROR);
                            alert4.setTitle("ERRORE!");
                            alert4.setHeaderText("Errore nella creazione Class.forName()");
                            alert4.setContentText("C'è stato un errore nella creazione di Class.forName()");
                            alert4.showAndWait();
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
    void handleDeleteClient(ActionEvent event) {
        try {
            Integer id;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("delete-client.fxml"));
            DialogPane view = loader.load();
            DeleteClientController controller = loader.getController();

            // Set an empty person into the controller
            controller.setPerson(new Person());
            while(true) {
                // Create the dialog to delete an Employee
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Delete Client");
                dialog.initModality(Modality.WINDOW_MODAL);
                dialog.setDialogPane(view);

                // Show the dialog and wait until the user closes it
                Optional<ButtonType> clickedButton = dialog.showAndWait();
                if (clickedButton.orElse(ButtonType.CANCEL) == ButtonType.OK) {
                    id = controller.getPerson().getIdPerson();
                    //Control about the inserted variables
                    if (id.equals(0)) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Inserimento non corretto!");
                        alert2.setHeaderText("Inserimento non corretto");
                        alert2.setContentText("Non hai selezionato un cliente, se non ne sono presenti è perché gli è" +
                                " stata fatta una lavorazione");
                        alert2.showAndWait();
                    } else {
                        Person newPerson = null;
                        for(Person p : client) {
                            if(p.getIdPerson().equals(id)) {
                                newPerson = p;
                                break;
                            }
                        }
                        client.remove(newPerson);
                        //Add the new Employee
                        try {
                            Class.forName(JDBC_Driver_MySQL);
                            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
                            PreparedStatement statement = c.prepareStatement("DELETE FROM Client\n" + "WHERE _id = " +
                                    "?;");
                            statement.setString(1, String.valueOf(id));
                            statement.executeUpdate();
                            break;
                        } catch (SQLException e) {
                            Alert alert3 = new Alert(Alert.AlertType.ERROR);
                            alert3.setTitle("ERRORE!");
                            alert3.setHeaderText("Errore SQL");
                            alert3.setContentText("C'è stato un errore nell'SQL");
                            alert3.showAndWait();
                        } catch (ClassNotFoundException e) {
                            Alert alert4 = new Alert(Alert.AlertType.ERROR);
                            alert4.setTitle("ERRORE!");
                            alert4.setHeaderText("Errore nella creazione Class.forName()");
                            alert4.setContentText("C'è stato un errore nella creazione di Class.forName()");
                            alert4.showAndWait();
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
    void handleDeleteType(ActionEvent event) {
        try {
            String name;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("delete-type.fxml"));
            DialogPane view = loader.load();
            DeleteTypeController controller = loader.getController();

            // Set an empty person into the controller
            controller.setType(new TypeOfJob());
            while(true) {
                // Create the dialog to delete an Employee
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Delete Type");
                dialog.initModality(Modality.WINDOW_MODAL);
                dialog.setDialogPane(view);

                // Show the dialog and wait until the user closes it
                Optional<ButtonType> clickedButton = dialog.showAndWait();
                if (clickedButton.orElse(ButtonType.CANCEL) == ButtonType.OK) {
                    name = controller.getType().getName();
                    //Control about the inserted variables
                    if (name.equals("")) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Inserimento non corretto!");
                        alert2.setHeaderText("Inserimento non corretto");
                        alert2.setContentText("Non hai selezionato un lavoro, se non ne sono presenti è perché sono " +
                                "stati effettuati ad un qualche cliente");
                        alert2.showAndWait();
                    } else {
                        TypeOfJob newType = null;
                        for(TypeOfJob t : type) {
                            if(t.getName().equals(name)) {
                                newType = t;
                                break;
                            }
                        }
                        type.remove(newType);
                        //Add the new Employee
                        try {
                            Class.forName(JDBC_Driver_MySQL);
                            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
                            PreparedStatement statement = c.prepareStatement("DELETE FROM Type\n" + "WHERE name = " +
                                    "?;");
                            statement.setString(1, name);
                            statement.executeUpdate();
                            break;
                        } catch (SQLException e) {
                            Alert alert3 = new Alert(Alert.AlertType.ERROR);
                            alert3.setTitle("ERRORE!");
                            alert3.setHeaderText("Errore SQL");
                            alert3.setContentText("C'è stato un errore nell'SQL");
                            alert3.showAndWait();
                        } catch (ClassNotFoundException e) {
                            Alert alert4 = new Alert(Alert.AlertType.ERROR);
                            alert4.setTitle("ERRORE!");
                            alert4.setHeaderText("Errore nella creazione Class.forName()");
                            alert4.setContentText("C'è stato un errore nella creazione di Class.forName()");
                            alert4.showAndWait();
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
    void handleDeleteJob(ActionEvent event) {
        try {
            Integer id;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("delete-job.fxml"));
            DialogPane view = loader.load();
            DeleteJobController controller = loader.getController();

            // Set an empty person into the controller
            controller.setJob(new Job());
            while(true) {
                // Create the dialog to delete an Employee
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Delete Type");
                dialog.initModality(Modality.WINDOW_MODAL);
                dialog.setDialogPane(view);

                // Show the dialog and wait until the user closes it
                Optional<ButtonType> clickedButton = dialog.showAndWait();
                if (clickedButton.orElse(ButtonType.CANCEL) == ButtonType.OK) {
                    id = controller.getJob().getIdJob();
                    System.out.println(id);
                    //Control about the inserted variables
                    if (id.equals(0)) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Inserimento non corretto!");
                        alert2.setHeaderText("Inserimento non corretto");
                        alert2.setContentText("Non hai selezionato un lavoro");
                        alert2.showAndWait();
                    } else {
                        Job deleteJob = null;
                        for(Job j : job) {
                            if(j.getIdJob().equals(id)) {
                                deleteJob = j;
                                break;
                            }
                        }
                        job.remove(deleteJob);
                        //Add the new Employee
                        try {
                            Class.forName(JDBC_Driver_MySQL);
                            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);
                            PreparedStatement statement = c.prepareStatement("DELETE FROM Job\n" + "WHERE _id = " +
                                    "?;");
                            statement.setString(1, String.valueOf(id));
                            statement.executeUpdate();
                            break;
                        } catch (SQLException e) {
                            Alert alert3 = new Alert(Alert.AlertType.ERROR);
                            alert3.setTitle("ERRORE!");
                            alert3.setHeaderText("Errore SQL");
                            alert3.setContentText("C'è stato un errore nell'SQL");
                            alert3.showAndWait();
                        } catch (ClassNotFoundException e) {
                            Alert alert4 = new Alert(Alert.AlertType.ERROR);
                            alert4.setTitle("ERRORE!");
                            alert4.setHeaderText("Errore nella creazione Class.forName()");
                            alert4.setContentText("C'è stato un errore nella creazione di Class.forName()");
                            alert4.showAndWait();
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
    void handleAbout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("about.fxml"));
            DialogPane view = loader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Delete Type");
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.setDialogPane(view);

            // Show the dialog and wait until the user closes it
            Optional<ButtonType> clickedButton = dialog.showAndWait();
            while(true) {
                if (clickedButton.orElse(ButtonType.CLOSE) == ButtonType.OK) {
                    break;
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            }
        });
    }
}


