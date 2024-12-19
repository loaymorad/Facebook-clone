package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Objects;

import static com.example.finalproject.Persons.Online;
import static com.example.finalproject.Persons.Online;

public class SearchPageController extends MainController {
    public static int Index;
    @FXML
    private ScrollPane scrollingPane;
    @FXML
    private TextField searchField;
    @FXML
    private VBox peopleList;
    @FXML
    private VBox NotFoundVbox;
    @FXML
    private ImageView searchIcon;

    public void initialize() {

        scrollingPane.setStyle("-fx-background-color: #1877F2; -fx-background: #1877F2;");
        scrollingPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollingPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        peopleList.setStyle("-fx-background-color: #f4f4f4; -fx-background: #f4f4f4;");

    }

    @FXML
    private void searchPeople() throws IOException {
        String searchQuery = searchField.getText().toLowerCase().trim();

        peopleList.getChildren().clear();



        if (searchQuery.isEmpty()) {
            return;
        }

        boolean found = false;
        int userIndex=0;
        for (Person person : Persons.persons) {
            System.out.println(person.name);


            if (!(Persons.persons.get(Online).name.equals(person.name))) {


                if (person.getName().toLowerCase().contains(searchQuery)) {
                    displayUser(person, userIndex);
                    found = true;
                }

            }
            userIndex++;
        }



        if (!found) {
            peopleList.setStyle("-fx-background-color: #1877F2; -fx-background: #1877F2;");
            Text noResults = new Text("No results found");
            noResults.setStyle("-fx-font-size: 22px; color: gray; -fx-font-weight: Bold");
            peopleList.getChildren().add(noResults);

        }
    }



    private void displayUser(Person person,int userPosition) throws IOException {
        peopleList.setStyle("-fx-background-color: #f4f4f4; -fx-background: #f4f4f4;");

        HBox personBox = new HBox();
        personBox.setStyle("-fx-padding: 10; -fx-border-color: lightgray; -fx-border-radius: 5; -fx-background-color: #f0f0f0;");

        Label nameLabel = new Label(person.getName());
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: black;");
        nameLabel.setMaxWidth(350);


        HBox.setHgrow(nameLabel, Priority.ALWAYS);

        Button profileButton = new Button("View Profile");
        profileButton.setStyle("-fx-background-radius: 8;");
        profileButton.setStyle("-fx-background-color: #2388df; -fx-text-fill: White; " +
                "-fx-background-radius: 13; -fx-font-weight: bold; -fx-font-size: 12;");

        HBox.setHgrow(profileButton, Priority.NEVER);

        personBox.getChildren().addAll(nameLabel, profileButton);
        personBox.setPrefWidth(424);

        profileButton.setOnAction(event -> {
            try {
                Index=userPosition;
                //Parent root=new FXMLLoader().load(Objects.requireNonNull(getClass().getResource("afterSearch.fxml")));
                Parent roott=new FXMLLoader().load(Objects.requireNonNull(getClass().getResource("afterSearch.fxml")));
                Scene nextScene=new Scene(roott);
                Facebook.stage.setScene(nextScene);
                Facebook.stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });



        peopleList.getChildren().add(personBox);
        peopleList.setPadding(new Insets(3));
        peopleList.setPadding(new Insets(6,6,6,6));
    }

    @Override
    public void search() {
        try {
            Facebook.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SearchPage.fxml")));
            Facebook.scene = new Scene(Facebook.root,Facebook.width, Facebook.height);
            Facebook.stage.setScene(Facebook.scene);
            Facebook.stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }



}
