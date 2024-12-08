package com.example.final_project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class HelloController {

    public void open() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        Scene scene = new Scene(root);
        HelloApplication.stage.setTitle("register");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
    }








}