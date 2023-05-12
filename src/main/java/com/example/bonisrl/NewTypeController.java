package com.example.bonisrl;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NewTypeController {

    @FXML private TextField nameTextField;
    @FXML private TextArea descriptionTextField;
    TypeOfJob type;

    @FXML
    public void initialize() {
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> type.nameProperty().set(newValue));
        descriptionTextField.textProperty().addListener((observable, oldValue, newValue) -> type.descriptionProperty().set(newValue));
    }

    void update() {
        nameTextField.textProperty().set(type.getName());
        descriptionTextField.textProperty().set(type.getDescription());
    }

    public TypeOfJob getType() {
        return type;
    }

    public void set(TypeOfJob type) {
        this.type = type;
        update();
    }

}
