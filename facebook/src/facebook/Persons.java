
package facebook;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class Persons {
    static ArrayList <Person> persons = new ArrayList <>() ;
   
    // Actual user
    public static int Online;
    //run this method for test
    public static void run () {
        
        
       int counter_int = 0 ;
       File file; 
       Person p = new Person () ;
       Scanner input ; 
      
       while ( new File (Facebook.pathOfFiles+counter_int).exists()){// path/src/+index
       
              
       
               try {
                  file = new File (Facebook.pathOfFiles+counter_int+"/sign.txt");
                  input= new Scanner (file);
                  
                  p.email = input.nextLine(); 
                  p.password = input.nextLine () ; 
                  p.name = input.nextLine () ; 
                  p.gender = input.nextLine () ;
                  p.date = input.nextLine () ;
                 input.close () ; 
                  
                  
                  file = new File (Facebook.pathOfFiles+counter_int+"/posts_comments.txt");
                  input= new Scanner (file);
                  
                  while (input.hasNextLine()){
                    p.posts_comments.add(input.nextLine());
                  
                  }
                  
                  input.close () ; 
                  
                  
                  file = new File (Facebook.pathOfFiles+counter_int+"/restricted.txt");
                  input= new Scanner (file);
                  
                  while (input.hasNextLine()){
                   p.restricted_User_friends.add(Integer.parseInt(input.nextLine()));
                  
                  }
                  
                  input.close () ; 
                  
                  file = new File (Facebook.pathOfFiles+counter_int+"/unrestricted.txt");
                  input= new Scanner (file);
                  
                  while (input.hasNextLine()){
                   p.user_friends.add(Integer.parseInt(input.nextLine()));
                  
                  }
                  
                  input.close () ;
                  
                  
                  
                  
               }
               catch (IOException e){
                   System.out.println(e.getMessage());
               
               }
               
        
        
        
                Persons.persons.add(p);
                counter_int++ ; 
           
       
       }
       
    }


}
