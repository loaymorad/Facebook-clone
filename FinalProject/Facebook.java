package com.example.finalproject;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Facebook extends Application{
    public static String pathOfFiles = System.getProperty("user.dir")+"/src/";
    
    
    public static Stage stage ;
    public static Scene scene; 
    public static Parent root ;
    
    
    static Dimension screenSize ; 
    static double width ; 
    static double height ; 
     
    @Override
    public void start(Stage s) {
        try {

            stage=new Stage();
            
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
           
            scene = new Scene(root, width, height); 
            stage.setTitle("Facebook"); 
            stage.setScene(scene );
            scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            stage.show();
  
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    @Override 
    public void stop () {// save data
    
        
    /*
      if true -> new user
      if false -> old user   
    */


    
    
    if (Register_cont.new_user){
    
        try{
        
        File dir_fil = new File (pathOfFiles+(Persons.persons.size()-1));
        dir_fil.mkdir();
        
        dir_fil = new File (pathOfFiles+(Persons.persons.size()-1)+"/sign.txt");
        dir_fil.createNewFile(); 
        
        dir_fil = new File (pathOfFiles+(Persons.persons.size()-1)+"/posts_comments.txt");
        dir_fil.createNewFile(); 
        
        dir_fil = new File (pathOfFiles+(Persons.persons.size()-1)+"/restricted.txt");
        dir_fil.createNewFile(); 
        
        dir_fil = new File (pathOfFiles+(Persons.persons.size()-1)+"/unrestricted.txt");
        dir_fil.createNewFile(); 
        
        }
        catch (IOException e){
        
            System.out.println(e.getMessage());
        }
        
        save_files();
        
    }
    
    else {
    
        save_files();
    
    }
    
    
    
    
    
    }

    
    private void save_files () {



        int counter = 0 ;

        FileWriter myFile ;

        for (Person p : Persons.persons){

            try {

                myFile = new FileWriter (pathOfFiles +counter + "/sign.txt");
                myFile.write(p.email+'\n');
                myFile.write(p.password+'\n');
                myFile.write(p.name+'\n');
                myFile.write(p.gender+'\n');
                myFile.write(p.date);

                myFile.close();


                int handle = 0;
                myFile = new FileWriter (pathOfFiles +counter + "/posts_comments.txt");

                for (String str : p.posts_comments){
                    str =  str.replace("\n", "457484123252454111521321125");
                    if (handle == (p.posts_comments.size()-1))myFile.write(str);
                    else myFile.write(str+'\n');
                    handle  ++;
                }

                myFile.close();

                handle  = 0;

                myFile = new FileWriter (pathOfFiles +counter + "/restricted.txt");

                for (Integer num: p.restricted_User_friends){

                    if (handle == (p.restricted_User_friends.size()-1))myFile.write(num.toString());
                    else myFile.write(num.toString()+'\n');
                    handle  ++;
                }

                myFile.close();

                handle  = 0;

                myFile = new FileWriter (pathOfFiles +counter + "/unrestricted.txt");

                for (Integer num : p.user_friends){

                    if (handle == (p.user_friends.size()-1))myFile.write(num.toString());
                    else myFile.write(num.toString()+'\n');
                    handle  ++;
                }

                myFile.close();


            }catch (IOException e){
                System.out.println(e.getMessage());}

            counter++;

        }



    }

    
    
    }
    
    
    
    
    public static void main(String[] args) {
       
        Persons.run(); 
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth()/2;
        height = screenSize.getHeight()-140;
        launch(args);
        for(Person wepif: Persons.persons){
            System.out.println(wepif.name);
        }
    }
}
