package com.example.bonisrl;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NewPersonController {

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private DatePicker birthdayDatePicker;
    Person person;

    @FXML
    public void initialize() {
        firstNameTextField.textProperty().addListener((observable, oldValue, newValue) -> person.firstNameProperty().set(newValue));
        lastNameTextField.textProperty().addListener((observable, oldValue, newValue) -> person.lastNameProperty().set(newValue));
        birthdayDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> person.birthdayProperty().set(newValue));
    }


    void update() {
        firstNameTextField.textProperty().set(person.getFirstName());
        lastNameTextField.textProperty().set(person.getLastName());
        birthdayDatePicker.valueProperty().set(person.getBirthday());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        update();
    }

}
