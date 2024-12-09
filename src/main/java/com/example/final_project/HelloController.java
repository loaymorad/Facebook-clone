package com.example.final_project;

//import com.dlsc.formsfx.model.structure.PasswordField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.example.final_project.Persons.persons;

public class HelloController {











    public void open() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        Scene scene = new Scene(root);
        HelloApplication.stage.setTitle("register");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();






    }



}