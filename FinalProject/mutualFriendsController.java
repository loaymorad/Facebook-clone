package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.*;

import static com.example.finalproject.Persons.Online;
import static com.example.finalproject.Persons.Online;

public class mutualFriendsController {

    @FXML
    public VBox friendsContainer;  // Container for all rows
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public ImageView returnn;
    @FXML
    public Text subTitle;
    @FXML
    public Text mutualFriends;
    @FXML
    public StackPane centerText;

    private boolean[] noUsers=new boolean[4];
    //public static HashMap<String, Boolean> status=new HashMap<>();

    static private Label friendText;


    public void initialize() throws IOException {
        Arrays.fill(noUsers, true);
        subTitle.setText("mutual friends with " + Persons.persons.get(SearchPageController.Index).name);

        centerText.setAlignment(Pos.CENTER);

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        checkExistance(Persons.persons.get(SearchPageController.Index).user_friends, Persons.persons.get(Online).user_friends,0);
        checkExistance(Persons.persons.get(SearchPageController.Index).restricted_User_friends, Persons.persons.get(Online).user_friends,1);
        checkExistance(Persons.persons.get(SearchPageController.Index).user_friends, Persons.persons.get(Online).restricted_User_friends,2);
        checkExistance(Persons.persons.get(SearchPageController.Index).restricted_User_friends, Persons.persons.get(Online).restricted_User_friends,3);





        if (noUsers[0] && noUsers[1] && noUsers[2] && noUsers[3]){
            Text noMutual=new Text("No mutual friends!");
            noMutual.setStyle("-fx-font-size: 27px; -fx-font-weight: Bold;");
            friendsContainer.getChildren().add(noMutual);
            friendsContainer.setPadding(new Insets(85,0,0,100));
        }
    }

    public void checkExistance(ArrayList<Integer> Friend1,ArrayList<Integer> Friend2, int i) throws IOException{
        for (Integer friendName : Friend1) {


            for (Integer OnlineUserFriends: Friend2) {
                if (friendName.equals(OnlineUserFriends)) {
                    noUsers[i]=false;
                    Label friendText = new Label(Persons.persons.get(OnlineUserFriends).name);
                    friendText.setStyle("-fx-font-weight: Regular; -fx-font-size: 17;" + "-fx-font-weight: bold");

                    Button followButton = new Button("Following");
                    followButton.setId(Persons.persons.get(OnlineUserFriends).id + "");
                    try {
                           followButton.setText("Following");
                            followButton.setStyle(
                                    "-fx-background-color: #2388df; -fx-text-fill: white; " +
                                            "-fx-background-radius: 13; -fx-font-weight: bold; -fx-font-size: 14;"
                              );



                        followButton.setOnAction(event -> {


                            if (followButton.getText().equals("Following")) {
                                int indexOfuser = Persons.persons.get(Online).user_friends.indexOf(Integer.parseInt(followButton.getId()));
                                System.out.println("here");
                                System.out.println(indexOfuser);
                                if (indexOfuser == -1){
                                     indexOfuser = Persons.persons.get(Online).restricted_User_friends.indexOf(Integer.parseInt(followButton.getId()));
                                    Persons.persons.get(Online).restricted_User_friends.remove(indexOfuser);
                                }
                                else {
                                    Persons.persons.get(Online).user_friends.remove(indexOfuser);
                                    //status.put(followButton.getId(), true);
                                }


                                followButton.setText("Follow");
                                followButton.setStyle("-fx-background-color: #4e4f50; -fx-text-fill: white; " +
                                        "-fx-background-radius: 13; -fx-font-weight: bold; -fx-font-size: 14;");
                            } else {
                                //status.put(followButton.getId(), false);
                                Persons.persons.get(Online).user_friends.add(Integer.parseInt(followButton.getId()));
                                followButton.setText("Following");
                                followButton.setStyle("-fx-background-color: #2388df; -fx-text-fill: white; " +
                                        "-fx-background-radius: 13; -fx-font-weight: bold; -fx-font-size: 14;");
                            }
                        });
                    } catch (Exception e) {
                        System.out.println(e.fillInStackTrace());
                        System.out.println(e.getMessage());
                        System.out.println(e.getCause());
                    }

                    HBox row = new HBox();
                    HBox lineContainer = new HBox();


                    friendText.setMaxWidth(Double.MAX_VALUE);
                    HBox.setHgrow(friendText, Priority.SOMETIMES);

                    Separator Hline = new Separator(Orientation.HORIZONTAL);
                    Hline.setTranslateY(0);
                    Hline.setPrefHeight(row.getMaxWidth());
                    HBox.setHgrow(Hline, Priority.ALWAYS);
                    lineContainer.getChildren().add(Hline);
                    row.getChildren().addAll(friendText, followButton);
                    friendsContainer.getChildren().addAll(row, lineContainer);
                    VBox.setMargin(row, new Insets(-2.5));
                    friendsContainer.setPadding(new Insets(15,10,10,18));
                }

            }


        }
    }


    public void setReturnn() throws IOException{
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("afterSearch.fxml")));
        Scene returnToPreviousScene=new Scene(root);
        Facebook.stage.setScene(returnToPreviousScene);
        Facebook.stage.show();

    }
}

