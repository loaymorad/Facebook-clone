package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommentController implements Initializable {

    private final ArrayList<String> strComments = new ArrayList<>();
    private ArrayList<Comment> comments =new ArrayList<>(){};
    @FXML private Button commentButton;
    @FXML private ScrollPane id_sp_main;
    @FXML private TextField id_tf_comment;
    @FXML private VBox id_vbox_comments;
    private String mentionedPerson="";
    private static final String userName = "youssef";

    private List<String> filterPeople(List<String> people, String query) {
        if (query.isEmpty()) {
            return new ArrayList<>(people);
        }
        List<String> matches = new ArrayList<>();
        for (String person : people) {
            if (person.toLowerCase().startsWith(query)) {
                matches.add(person);
            }
        }
        return matches;
    }
    private VBox commentStyle(Comment comment ){
    Label NoLike = new Label("like: " + comment.getNumOfLike() );
    NoLike.setPadding(new Insets(5,5,5,10));
    Text senderNam = new Text(comment.getUserName());
    senderNam.setFont(Font.font("Laurel", FontWeight.EXTRA_BOLD, 12));
    TextFlow senderName = new TextFlow(senderNam);
    senderName.setPadding(new Insets(0,5,0,5));
    VBox content;
    if(!(comment.getMention()==null)){
        String s= comment.getContent().replace("@"+comment.getMention(),"");
        Text mention = new Text(comment.getMention());
        mention.setFill(Color.BLUE);
        mention.setUnderline(true);

        mention.setOnMouseClicked(_ -> System.out.println(mention.getText()));
        content = new VBox(senderName,new TextFlow( mention,new Text(s)));
    }else {
        content = new VBox(senderName, new TextFlow(new Text(comment.getContent())));
    }
    content.setPadding(new Insets(2,5,0,5));
    Button likeButton = new Button("Like");
    Button replyButton = new Button("Reply");
    ImageView likeImageView =new ImageView(new Image("like_.png"));
    likeImageView.setFitHeight(17);
    likeImageView.setFitWidth(17);
    likeButton.setGraphic(likeImageView);
    ImageView replyImageView =new ImageView(new Image("reply.png"));
    replyImageView.setFitHeight(18);
    replyImageView.setFitWidth(18);
    replyButton.setGraphic(replyImageView);
    HBox hx_like_rely = new HBox(5, likeButton, replyButton, NoLike);
    content.setStyle("-fx-color: rgb(239,242,255);" + "-fx-background-color: rgb(219,233,244);" + "-fx-background-radius: 20px;");
    return new VBox(5, content, hx_like_rely);
}
    private VBox replyStyle( Reply reply ,String commentUserName){
        Text senderName = new Text(reply.getUserName());
        senderName.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 10));
        Text commentUsName = new Text(commentUserName + " ");
        commentUsName.setFill(Color.BLUE);
        commentUsName.setUnderline(true);

        commentUsName.setOnMouseClicked(_ -> System.out.println(commentUserName));
        Button likeButton = new Button("Like");
        Button replyButton = new Button("Reply");
        Label NoLike = new Label("like: " + reply.getLikes());
        NoLike.setPadding(new Insets(5,5,5,10));

        VBox box = new VBox(2,senderName,new TextFlow(commentUsName , new Text(reply.getContent())));
        box.setPadding(new Insets(5,5,0,5));
        HBox hx_like_rely = new HBox(5,likeButton,replyButton,NoLike);
        VBox vxAllReply = new VBox(box,hx_like_rely);
        box.setStyle("-fx-color: rgb(239,242,255);" + "-fx-background-color: rgb(219,233,244);" + "-fx-background-radius: 20px;");
        vxAllReply.setPadding(new Insets(5, 1, 5, 20));
        return vxAllReply;
    }
    private void displayComment(Comment comment,VBox vxCommentReply) {

        VBox vxAllComment = commentStyle(comment);
        HBox retrieveHBox = (HBox) vxAllComment.getChildren().get(1);
        Button likeButton = (Button)retrieveHBox.getChildren().get(0);
        Button replyButton = (Button)retrieveHBox.getChildren().get(1);
        Label NoLike = (Label)retrieveHBox.getChildren().get(2);

        for (int i = 0 ;i<comment.getReplies().size();i++)
        {
            VBox vBox2 = new VBox();
            //Reply x = ;
            displayReply(comment.getUserName(),comment.getReplies().get(i),vxAllComment);
            vxCommentReply.getChildren().add(vBox2);
        }
        likeButton.setOnAction(_ -> {
            comment.add_RemoveReaction(userName);
            NoLike.setText("like: " + comment.getNumOfLike() );

        });
        replyButton.setOnAction(_ -> {

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Reply");
            dialog.setHeaderText("Enter your Reply...");
            dialog.showAndWait().ifPresent(replyCont -> {
                comment.addReply(userName,replyCont);
                displayReply(comment.getUserName(),comment.getReplies().getLast(),vxAllComment);


            });
        });
        vxCommentReply.getChildren().add(vxAllComment);
    }
    private void displayReply(String commentUserName,Reply reply,VBox vxAllComment ){
        VBox vxAllReply = replyStyle(reply,commentUserName);
        HBox retrieveHBox = (HBox) vxAllReply.getChildren().get(1);
        Button likeButton = (Button)retrieveHBox.getChildren().get(0);
        Button replyButton = (Button)retrieveHBox.getChildren().get(1);
        Label NoLike = (Label)retrieveHBox.getChildren().get(2);
        for (int i = 0 ;i<reply.getNestedReplies().size();i++) {
            VBox vBox3 = new VBox();
            Reply x = reply.getNestedReplies().get(i);
            displayReply(reply.getUserName(),x,vxAllReply);
            vxAllReply.getChildren().add(vBox3);
        }
        likeButton.setOnAction(_ -> {
            reply.add_RemoveReaction(userName);
            NoLike.setText("like: " + reply.getLikes());
        });
        replyButton.setOnAction(_ ->{
            TextInputDialog replyDialog =new TextInputDialog();
            replyDialog.setTitle("Reply");
            replyDialog.setHeaderText("Enter your Reply...");
            replyDialog.showAndWait().ifPresent(content->{
               // Reply nestedReply = new Reply(userName,reply.getReplyId(), );
                //reply.addNestedReply(userName,content);
                displayReply( reply.getUserName(),reply.getNestedReplies().getLast(), vxAllReply);

            });
        });
        vxAllComment.getChildren().add(vxAllReply);
    }
    private String mention (){
        ContextMenu mentionMenu = new ContextMenu();
        ArrayList<String> people = new ArrayList<>();
        for (Comment comment: comments){
            people.add(comment.getUserName());
        }
        id_tf_comment.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains("@")) {
                int atIndex = newValue.lastIndexOf("@");
                String query = newValue.substring(atIndex + 1).toLowerCase();
                List<String> matches = filterPeople(people, query);
                mentionMenu.getItems().clear();
                for (String match : matches) {
                    MenuItem item = new MenuItem(match);
                    item.setOnAction(_ -> {

                        id_tf_comment.setText(newValue.substring(0, atIndex + 1) + match + " ");
                        id_tf_comment.positionCaret(id_tf_comment.getText().length());
                        mentionMenu.hide();
                        mentionedPerson = match;
                    });
                    mentionMenu.getItems().add(item);
                }
                if (!matches.isEmpty()) {
                    mentionMenu.show(id_tf_comment, id_tf_comment.getLayoutX(), id_tf_comment.getLayoutY() + id_tf_comment.getHeight());
                } else {
                    mentionMenu.hide();
                }
            } else {
                mentionMenu.hide();
            }
        });
        return mentionedPerson;
    }
    public void parseComments(String strComments) {
        String[] elements = strComments.split("\\|");
        Comment currentComment = null;
        Reply currentReply = null;
        for (int i = 0; i < elements.length; i += 4) {
            String id = elements[i];
            String userName = elements[i + 1];
            String content = elements[i + 2];
            int likes = Integer.parseInt(elements[i + 3]);
            int depth = id.split("\\.").length; // Determine depth based on the number of '.' in id
            if (depth == 2) { // Top-level comment
                currentComment = new Comment(userName,"7", content, likes);
                comments.add(currentComment);
                currentReply = null;
            } else if (depth == 3) { // Reply to comment
                if (currentComment != null) {
                    currentComment.addReply(userName, content, likes);
                    currentReply = currentComment.getReplies().getLast();
                }
            } else if (depth >= 4) { // Nested reply
                if (currentReply != null) {
                    currentReply.addNestedReply( userName, content, likes);
                }
            }
        }
    }

    @Override public void initialize (URL url, ResourceBundle resourceBundle) throws RuntimeException {
        strComments.add("14.0|Hisham|i like coffee|5|14.0.0|Ahmed|hi|0|14.0.1|loay|me too|0|14.1|loay|Basha el_balad|7|14.1.0|elza3im|Hello i am Here|5|14.1.0.0|joe|A7A|5|14.1.0.0.0|haha|a7a|10|");

        for (String y : strComments) {
            parseComments(y);
        }

        for (Comment comm : comments) {
            VBox vBox = new VBox();
            displayComment(comm, vBox);
            id_vbox_comments.getChildren().add(vBox);
        }

        commentButton.setOnAction(_ -> {

            String commentToSend = id_tf_comment.getText();
            if (!commentToSend.trim().isEmpty()) {
                Comment comment = new Comment(userName, "123", commentToSend);
                String mention = mention().replace("@","");
                comment.setMention(mention);
                VBox vxCommentReply = new VBox();
                displayComment(comment, vxCommentReply);
                vxCommentReply.heightProperty().addListener((_, _, t1) -> id_sp_main.setVvalue((Double) t1));   //  autoScrollDown
                comments.add(comment);
                id_vbox_comments.getChildren().add(vxCommentReply);
                id_tf_comment.clear();
            }
            //ArrayList<String>saveComment =new ArrayList<>();
            String saveComment= "";
            for(Comment c:comments){
                saveComment +=c.toString().replaceAll("[\\[\\]\n]", "");
            }
            System.out.println(saveComment.replaceAll(",",""));
        });
        }

}
