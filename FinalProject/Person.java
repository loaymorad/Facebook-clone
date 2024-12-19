package com.example.finalproject;

import java.util.ArrayList;


public class Person {


    protected String name ;
    protected int id ;
    protected String date ;
    protected String gender ;
    protected String email ;
    protected String password ;

    // Here its all posts and all its replies  for same person
    protected ArrayList <String> posts_comments = new ArrayList <> ();
    protected ArrayList <Integer> user_friends =new ArrayList<>();
    protected ArrayList <Integer> restricted_User_friends =new ArrayList<>();


    public Person () {}

    public Person(ArrayList <String> posts_comments, String name, int id, String date, String gender, String email, String password, ArrayList <Integer> user_friends,  ArrayList <Integer> restricted_User_friends) {
        this.posts_comments = posts_comments;
        this.name = name;
        this.id = id;
        this.date = date;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.user_friends=user_friends;
        this.restricted_User_friends=restricted_User_friends;

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

    public void setPosts_comments(ArrayList<String> posts_comments) {
        this.posts_comments = posts_comments;
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

    public ArrayList<String> getPosts_comments() {
        return posts_comments;
    }

    public ArrayList<Integer> getUser_friends(){
        return user_friends;
    }

    public void setUser_friends(ArrayList<Integer> user_friends) {
        this.user_friends=user_friends;
    }









}