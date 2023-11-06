package com.example.bonisrl;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.*;
import java.util.TimeZone;

public class DeleteTypeController {

    @FXML
    private ComboBox<String> selectComboBox;

    public static final String JDBC_Driver_MySQL = "com.mysql.cj.jdbc.Driver";
    public static final String JDBC_URL_MySQL = "jdbc:mysql://localhost:3306/boni_srl?user=boniSrl&password" +
            "=Magamago2101!" + "&serverTimezone=" + TimeZone.getDefault().getID();

    TypeOfJob type;

    @FXML
    public void initialize() {
        try {
            Class.forName(JDBC_Driver_MySQL);
            Connection c = DriverManager.getConnection(JDBC_URL_MySQL);

            PreparedStatement statement = c.prepareStatement(   "\n" + "SELECT T.name AS 'name', T.description AS " +
                    "'description'\n" +
                    "FROM boni_srl.Type AS T\n" + "LEFT JOIN boni_srl.Job AS J ON T.name = J.nameType\n" + "WHERE J.idEmployee IS NULL;");
            ResultSet rs = statement.executeQuery();
            String string;
            while(rs.next()) {
                string = "";
                string += rs.getString("name");
                string += "-";
                string += rs.getString("description");
                selectComboBox.getItems().add(string);
            }
            selectComboBox.getSelectionModel().select("0");

            selectComboBox.valueProperty().addListener((observable, oldValue, newValue) -> type.nameProperty().set(newValue.substring(0, newValue.indexOf('-'))));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void update() {
    }

    public TypeOfJob getType() {
        return type;
    }

    public void setJob(TypeOfJob type) {
        this.type = type;
        update();
    }
}
