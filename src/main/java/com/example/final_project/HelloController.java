package com.example.final_project;

//import com.dlsc.formsfx.model.structure.PasswordField;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import static com.example.final_project.Persons.Online;
import static com.example.final_project.Persons.persons;

public class HelloController implements Initializable {
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

    @FXML
    private ToggleButton pass_btn;

    @FXML
    private Label shownpassword;
    @FXML
    void password_field(KeyEvent event) {
        shownpassword.textProperty().bind(Bindings.concat(L_password.getText()));

    }

    @FXML
    void show(ActionEvent event) {
        if(pass_btn.isSelected()){
            shownpassword.setVisible(true);
            shownpassword.textProperty().bind(Bindings.concat(L_password.getText()));
            pass_btn.setText("Hide");

        }else {
            shownpassword.setVisible(false);
            pass_btn.setText("Show");
        }


    }
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        shownpassword.setVisible(false);
    }


    protected Person login=new Person();

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
                login.email = L_email.getText();
                login.password = L_password.getText();
                boolean Exist = false;

                for (int i = 0; i < persons.size(); i++) {
                    // Check if email and password match for the current person
                    if (login.email.equals(persons.get(i).email) && login.password.equals(persons.get(i).password)) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully logged in");
                        alert.showAndWait();
                       Exist = true;
                       Online=persons.size()-1;
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
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(Persons.persons.get(i).email);
            System.out.println(persons.get(i).password);
            System.out.println(persons.get(i).id);
            System.out.println(persons.get(i).gender);
            System.out.println(persons.get(i).date);

        }







    }



}