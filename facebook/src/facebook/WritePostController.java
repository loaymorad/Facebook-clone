package facebook; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.ToggleButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ContextMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;



public class WritePostController extends MainController {
    

@FXML
private TextArea textArea;

private ContextMenu mentionMenu;




private  ArrayList<String> allNames  = new ArrayList <> ();



public void initialize() {
  
  
    addFriends();
    addOtherPeoples();
   
      
    mentionMenu = new ContextMenu();

    // Listener for key events in the TextArea
    textArea.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            String currentText = textArea.getText();
            int caretPosition = textArea.getCaretPosition();

             
            
            // Check if user typed '@'
            if (event.getCharacter().equals("@")) {
              
                showMentionMenu();
                
            }
            // If menu is open, update dynamically based on the text
            else if (mentionMenu.isShowing()  ) {
                String substring = getTextAfterAt(currentText, caretPosition);
                
                updateMentionMenu(substring);
            }
        }
    });
}




public void addFriends (){
    for (int id_friend : Persons.persons.get(Persons.Online).user_friends){
    
        for (Person p  : Persons.persons )
        {
        
            if (p.id == id_friend){
            
                allNames.add(p.name+" ["+p.id+"]");
                 
                break; 
            }
        
        }
    
    
    }



}
public void addOtherPeoples ()  {
    boolean flag;
    for (Person p : Persons.persons){
     flag = true; 
    
        for (int id : Persons.persons.get(Persons.Online).user_friends ){
        
            if (p.id == id){
            
                flag = false;
                break;
            }
            
           
        }
    
            if (flag){
                
                    allNames.add(p.name+" ["+p.id+"]");
            
                }


}

}



private void showMentionMenu() {
 
    mentionMenu.getItems().clear();
    for (String name : allNames) {
        mentionMenu.getItems().add(createMenuItem(name));
    }
    mentionMenu.show(textArea, textArea.getScene().getWindow().getX() + textArea.getLayoutX()  ,
            textArea.getScene().getWindow().getY() + textArea.getLayoutY() + textArea.getCaretPosition());
}

private void updateMentionMenu(String textAfterAt) {
    mentionMenu.getItems().clear();
    if (textAfterAt.isEmpty()) {
        // If no text after '@', show all names
        for (String name : allNames) {
            mentionMenu.getItems().add(createMenuItem(name));
        }
    } else {
        // Filter names based on the text after '@'
        for (String name : allNames) {
            if (name.toLowerCase().startsWith(textAfterAt.toLowerCase())) {
                mentionMenu.getItems().add(createMenuItem(name));
            }
        }
    }

    // Hide the menu if no items are left
    if (mentionMenu.getItems().isEmpty()) {
        mentionMenu.hide();
    }
}

private MenuItem createMenuItem(String name) {
    MenuItem item = new MenuItem(name);
    item.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
            insertMention(name);
        }
    });
    return item;
}

private void insertMention(String name) {
    String edit_name = name.substring(0, name.indexOf(" "))+"_"+name.substring(name.indexOf("[")+1, name.indexOf("]"));
    String currentText = textArea.getText();
    int caretPosition = textArea.getCaretPosition();
    int atIndex = currentText.lastIndexOf("@", caretPosition - 1);

    // Replace text starting with '@' with the selected name
    String beforeAt = currentText.substring(0, atIndex + 1);
    String afterAt = currentText.substring(caretPosition);
    textArea.setText(beforeAt + edit_name + " " + afterAt);
    textArea.positionCaret(beforeAt.length() + edit_name.length() + 1); // Set caret position
    mentionMenu.hide();
}

private String getTextAfterAt(String text, int caretPosition) {
    int atIndex = text.lastIndexOf("@", caretPosition - 1);
    if (atIndex != -1) {
        return text.substring(atIndex + 1, caretPosition).trim();
    }
    return "";
}

    
    
    
    @FXML
    private ToggleButton privateToggle;
    @FXML
    private void togglePrivate() {
        if (privateToggle.isSelected()) {
            privateToggle.setStyle("-fx-background-color: #3b5998; -fx-text-fill: white;");
            privateToggle.setText("ON");
        } else {
            privateToggle.setStyle("-fx-background-color: lightgray; -fx-text-fill: black;");
            privateToggle.setText("OFF");
        }
        
        
    }

    
   @Override
   public void write_post (  ) {
       
         try {
             
            Parent root = FXMLLoader.load(getClass().getResource("post.fxml"));
            
            //privateToggle.setText(toggle2.getText());// for save
            
            Facebook.scene = new Scene(root,Facebook.width , Facebook.height); 
            
            Facebook.stage.setScene(Facebook.scene );
            
            Facebook.stage.show(); 
            
            
            
        } catch (IOException e) {
             System.out.println(e);
        }
        
    }
   
   
   public void toRight () {
   
    textArea.setNodeOrientation(javafx.geometry.NodeOrientation.RIGHT_TO_LEFT);
    
    if (textArea.getText().equals("")){
   
      textArea.setPromptText("أكتب هنا..."); 
    
    }
   
   }
   public void toLeft () {
   
   
    textArea.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
    if (textArea.getText().equals("")){
   
      textArea.setPromptText("Write here..."); 
    
    }
   
   } 
   
   
   public void send () {
        int maxId = 0 ; 
        int numOfDot  ;
        boolean flag ; 
        for (String string : Persons.persons.get(Persons.Online).posts_comments){
            numOfDot = 0 ; 
            flag = true ; 
            String sub = string.substring(0,string.indexOf("|")+1);
            for (int i = 0 ; i <sub.length() ; i ++ ){
                
                if (sub.charAt(i) =='.'){
                
                    numOfDot++;
                    
                    if (numOfDot > 1 ) {
                    flag = false ; 
                    break; 
                    
                    }
            
                }
            
            }
            
            if (flag){
                
            int sub_int = Integer.parseInt(sub.substring(sub.indexOf(".")+1, sub.indexOf("|")));
            if ( sub_int>maxId){ maxId = sub_int; }
            
            }
            
                        
        
        }
        
        maxId++; 
        String sec; 
        if ( privateToggle.getText().equals("OFF" )) sec = "public"; 
        else sec  = "private" ; 


        String final_string =maxId+"|like:0|"+sec+"{"+textArea.getText();
       
        Persons.persons.get(Persons.Online).posts_comments.add(final_string);
        all_posts () ;
        
   
   }
   
  
    
}
