
package com.mycompany.facebook;

import java.util.ArrayList;


public class Person {
    
    
      protected String name ; 
      protected int id ; 
      protected String date ;
      protected String gender ; 
      protected String email ; 
      protected String password ; 
    
    
      // Here its all posts and all its replies  for same person
      protected ArrayList <String> posts_comments_likes = new ArrayList <> ();
    
    
  

    public Person(ArrayList <String> posts_comments_likes, String name, int id, String date, String gender, String email, String password) {
        this.posts_comments_likes = posts_comments_likes;
        this.name = name;
        this.id = id;
        this.date = date;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPosts_comments_likes(ArrayList<String> posts_comments_likes) {
        this.posts_comments_likes = posts_comments_likes;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getPosts_comments_likes() {
        return posts_comments_likes;
    }
   
       
   

 
   
    
    
}
