package com.example.final_project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import static com.example.final_project.Persons.Online;
import static com.example.final_project.Persons.persons;





public class Register_cont implements Initializable  {



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
    protected Person register=new Person();











    public void back_to_login() throws IOException {
        Alert alert;

        try {
            if (f_email.getText().isEmpty()||f_username.getText().isEmpty()||f_password.getText().isEmpty()||combobox_year.getValue().isEmpty()||combobox_month.getValue().isEmpty()||combobox_day.getValue().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else {
                register.email=f_email.getText();
                register.name=f_username.getText();
                register.password=f_password.getText();
                register.d_date=combobox_day.getValue();
                register.m_date=combobox_month.getValue();
                register.y_date=combobox_year.getValue();


                if (f_male.isSelected()) {
                    register.gender = "male";
                } else if (f_female.isSelected()) {
                    register.gender = "female";
                }

                boolean Exist = false;

                for (int i = 0; i < persons.size(); i++) {
                    // Check if email and password match for the current person
                    if (f_email.getText().equals(persons.get(i).email)) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error message");
                        alert.setHeaderText(null);
                        alert.setContentText("This Email Used");
                        alert.showAndWait();
                        Exist = true;
                        break;
                        // Exit the loop once login is successful
                    }
                }

                if(!Exist){
                    register.id=persons.size()+1;
                    persons.add(register);
                    Online=persons.size()-1;



                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Reguster");
                    alert.showAndWait();

                }


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }






        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene scene = new Scene(root);
        HelloApplication.stage.setTitle("Login");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();






    }













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
