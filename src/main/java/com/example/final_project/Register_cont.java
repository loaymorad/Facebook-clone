package com.example.final_project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import static com.example.final_project.Persons.Online;


public class Register_cont implements Initializable  {





    public void back_to_login() throws IOException {
//        Online =Persons.persons.getFirst();
        Persons.run();
        if (Persons.Online != null) {
            Online.name=f_username.getText();
            Online.email=f_email.getText();
            Online.password=f_password.getText();
            Online.d_date=combobox_day.getValue();
            Online.m_date=combobox_month.getValue();
            Online.y_date=combobox_year.getValue();
            Persons.persons.add(Online);
            System.out.println(Online.name);

        } else {

            System.out.println("Online is null");
        }

//        System.out.println("this is my email "+Online.email);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene scene = new Scene(root);
        HelloApplication.stage.setTitle("Login");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();



    }








    @FXML
    private ComboBox<String> combobox_day;

    @FXML
    private ComboBox<String> combobox_month;

    @FXML
    private ComboBox<String> combobox_year;

    @FXML
    private TextField f_email;

    @FXML
    private RadioButton f_female;

    @FXML
    private RadioButton f_male;

    @FXML
    private PasswordField f_password;

    @FXML
    private TextField f_username;
    @FXML
    private ToggleGroup f_genger;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [] items = IntStream.rangeClosed(1, 31)
                .mapToObj(Integer::toString)
                .toArray(String[]::new);
        combobox_day.getItems().addAll(items);
        String [] item = {
                "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        combobox_month.getItems().addAll(item);

        String [] items3 = IntStream.rangeClosed(1905, 2024)
                .mapToObj(Integer::toString)
                .toArray(String[]::new);
        combobox_year.getItems().addAll(items3);


    }



}
