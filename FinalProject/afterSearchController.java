package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

import static com.example.finalproject.Facebook.scene;
import static com.example.finalproject.Persons.Online;


public class afterSearchController {
    @FXML
    private Text userName;
    @FXML
    private Text followersText;
    @FXML
    private Text followingText;
    @FXML
    private Button followButton;
    @FXML
    private MenuButton followMenu;
    @FXML
    private MenuItem followItem;
    @FXML
    private MenuItem restrictedItem;

    private static boolean isFriend;
    private static Boolean remove;
    private static Boolean checkRemove=false;
    private static Integer user_Id;

    public void initialize() throws IOException {
        System.out.println(Persons.persons.get(SearchPageController.Index).name);
        userName.setText(Persons.persons.get(SearchPageController.Index).name);
        int normalFriendsCounter=0;
        int restrictedFriendsCounter=0;
        followingText.setText((Persons.persons.get(SearchPageController.Index).user_friends.size()+Persons.persons.get(SearchPageController.Index).restricted_User_friends.size())+ " following");


        for (int i=0;i<Persons.persons.size(); i++){
            for (Integer userFriends: Persons.persons.get(i).user_friends){
                if (Persons.persons.get(SearchPageController.Index).id==userFriends){
                    normalFriendsCounter++;
                }
            }
            for (Integer RestrictedUserFriends: Persons.persons.get(i).restricted_User_friends){
                if (Persons.persons.get(SearchPageController.Index).id==RestrictedUserFriends){
                    restrictedFriendsCounter++;
                }
            }

        }
        followersText.setText((normalFriendsCounter+restrictedFriendsCounter)+ " followers");
        setFollowMenuEvent();
        setIsFriend();

    }

    private void setIsFriend(){
        boolean is_friend=false;
        for (Integer ids:Persons.persons.get(Online).user_friends){
            if (ids.equals(Persons.persons.get(SearchPageController.Index).id)){
                followButton.setText("Following");
                followButton.setVisible(true);
                followMenu.setVisible(false);
                is_friend=true;
                break;
            }
        }

        for (Integer ids:Persons.persons.get(Online).restricted_User_friends){
            if (ids.equals(Persons.persons.get(SearchPageController.Index).id)){
                followButton.setText("Following");
                followButton.setVisible(true);
                followMenu.setVisible(false);
                is_friend=true;
                break;
            }
        }
        if (!is_friend){

            followMenu.setText("Follow");
            followMenu.setVisible(true);
            followButton.setVisible(false);

        }
    }

    public void setFollowMenuEvent() throws IOException{
        followItem.setOnAction(event -> {


            boolean ifFound=false;
            for (Integer checkId: Persons.persons.get(Online).restricted_User_friends){
                if(checkId.equals(Persons.persons.get(SearchPageController.Index).id)){
                    ifFound=true;
                    break;
                }

            }
            if (ifFound){
                Persons.persons.get(Online).restricted_User_friends.remove((Integer) Persons.persons.get(SearchPageController.Index).id);
            }
            if (event.getSource().equals(followItem)){
                followMenu.setText("Follow");
                Persons.persons.get(Online).user_friends.add(Persons.persons.get(SearchPageController.Index).id);
                followMenu.setVisible(false);
                followButton.setVisible(true);
                followButton.setText("Following");
            }
        });
        restrictedItem.setOnAction(event -> {
            boolean isFound=false;
            for (Integer checkId: Persons.persons.get(Online).user_friends){
                if(checkId.equals(Persons.persons.get(SearchPageController.Index).id)){
                    isFound=true;
                    break;
                }
            }
            if (isFound){
                Persons.persons.get(Online).user_friends.remove((Integer) Persons.persons.get(SearchPageController.Index).id);
            }
            if (event.getSource().equals(restrictedItem)){
                followMenu.setText("Follow");
                Persons.persons.get(Online).restricted_User_friends.add(Persons.persons.get(SearchPageController.Index).id);
                followMenu.setVisible(false);
                followButton.setVisible(true);
                followButton.setText("Following");
            }
        });
        if (followMenu.getText().equals("Following")) {


            followMenu.setOnAction(event -> {
                if (followMenu.getText().equals("Following")) {
                    followMenu.setText("Follow");
                    event.consume();
                }
            });
        }

    }

    public void followEvent() throws IOException{
        isFriend=false;
        for (Integer removeId: Persons.persons.get(Online).user_friends){
            if (removeId.equals(Persons.persons.get(SearchPageController.Index).id)) {
                isFriend = true;
                break;
            }
        }
        if (isFriend){
            Persons.persons.get(Online).user_friends.remove((Integer) Persons.persons.get(SearchPageController.Index).id);

        }
        else {
            Persons.persons.get(Online).restricted_User_friends.remove((Integer) Persons.persons.get(SearchPageController.Index).id);
        }
        followButton.setVisible(false);
        followMenu.setVisible(true);

    }

    public void setMutualScene() throws IOException {
        //Parent root=new FXMLLoader().load(Objects.requireNonNull(getClass().getResource("afterSearch.fxml")));
        Parent roott=new FXMLLoader().load(Objects.requireNonNull(getClass().getResource("mutualFriendsPage.fxml")));
        Scene nextScene=new Scene(roott);
        Facebook.stage.setScene(nextScene);
        Facebook.stage.show();
    }

    public void returnToSearch() throws IOException {
        Parent roott=new FXMLLoader().load(Objects.requireNonNull(getClass().getResource("SearchPage.fxml")));
        Scene nextScene=new Scene(roott);
        Facebook.stage.setScene(nextScene);
        Facebook.stage.show();
//         Facebook.root=new FXMLLoader().load(Objects.requireNonNull(getClass().getResource("SearchPage.fxml")));
//         Facebook.scene=new Scene(Facebook.root,Facebook.width,Facebook.height);
//
    }
}
