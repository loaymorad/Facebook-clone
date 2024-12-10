package com.example.final_project;

//import com.dlsc.formsfx.model.structure.PasswordField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import static com.example.final_project.Persons.Online;
import static com.example.final_project.Persons.persons;

public class HelloController {
    @FXML
    private Button create_but;

    @FXML
    private TextField L_email;

    @FXML
    private PasswordField L_password;

    @FXML
    private Button login_but;

    @FXML
    private BorderPane login_form;

    public void loginaccount() throws IOException {
        Alert alert;

        try {
            if (L_email.getText().isEmpty() || L_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                Online.email = L_email.getText();
                Online.password = L_password.getText();
                boolean Exist = false;

                for (int i = 0; i < persons.size(); i++) {
                    // Check if email and password match for the current person
                    if (Online.email.equals(persons.get(i).email) && Online.password.equals(persons.get(i).password)) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully logged in");
                        alert.showAndWait();
                       Exist = true;
                        break; // Exit the loop once login is successful
                    }
                }

                // If no match was found after checking all persons
                if (!Exist) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect email or password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }





}











    public void open() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        Scene scene = new Scene(root);
        HelloApplication.stage.setTitle("register");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();







    }



}