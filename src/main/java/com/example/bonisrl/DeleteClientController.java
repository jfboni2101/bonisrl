package com.example.bonisrl;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.*;
import java.util.TimeZone;

public class DeleteClientController {

    @FXML
    private ComboBox<String> selectComboBox;

    public static final String JDBC_Driver_MySQL = "com.mysql.cj.jdbc.Driver";
    public static final String JDBC_URL_MySQL = "jdbc:mysql://localhost:3306/boni_srl?user=boniSrl&password" +
            "=Magamago2101!" + "&serverTimezone=" + TimeZone.getDefault().getID();

    Person person;

    @FXML
    public void initialize() {
        try {
            Class.forName(JDBC_Driver_MySQL);
            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);

            PreparedStatement statement = c.prepareStatement(   "SELECT C._id AS 'idClient', C.lastName AS " +
                    "'lastname', C.firstName AS 'firstname', C.birthday AS 'birthday'\n" + "FROM boni_srl.Client AS " +
                    "C\n" + "LEFT JOIN boni_srl.Job AS J ON C._id = J.idClient\n" + "WHERE J.idClient IS NULL;");
            ResultSet rs = statement.executeQuery();
            String string;
            while(rs.next()) {
                string = "";
                string += rs.getInt("idClient");
                string += "-";
                string += rs.getString("lastname");
                string += " ";
                string += rs.getString("firstname");
                string += " ";
                string += rs.getString("birthday");
                selectComboBox.getItems().add(string);
            }
            selectComboBox.getSelectionModel().select("0-");

            selectComboBox.valueProperty().addListener((observable, oldValue, newValue) -> person.idPersonProperty().set(Integer.valueOf(newValue.substring(0, newValue.indexOf('-')))));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void update() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        update();
    }
}
