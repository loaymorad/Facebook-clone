
package com.example.final_project;


import java.util.ArrayList;

import java.util.List;


public class Persons {
    static ArrayList <Person> persons = new ArrayList <>() ;

    // Actual user
    public static Person Online;
    //run this method for test
    public static void run (){




        Person p = new Person (
                new ArrayList<>(List.of(
                        "1.1|like:12|security:private[2,3]tag:yes[2]{WHO AM I @?",
                        "1.1.1|like:3{you are Hisham",
                        "1.2|like:5|security:public{I like coffee",
                        "1.2.1|like:2{and me !"

                )), "ahmed Ali", 1 , "12", "Novamber" , "2004","male" ,"samy_Ali@gmail.com" , "12345678",new ArrayList<>(List.of(1,2,3)));

        persons.add(p);


        p = new Person (
                new ArrayList<>(List.of(
                        "1.1|like:12|security:private[2,3]tag:yes[2]{WHO AM I @?",
                        "1.1.1|like:3{you are Hisham",
                        "1.2|like:5|security:public{I like coffee",
                        "1.2.1|like:2{and me !"

                )), "Mohamed Ali", 2 , "12", "jane" , "2004","male" ,"Mohamed_Ali@gmail.com" , "12345678",new ArrayList<>(List.of(1,2,3)));

        persons.add(p);



        p = new Person (
                new ArrayList<>(List.of(
                        "1.1|like:12|security:private[2,3]tag:yes[2]{WHO AM I @?",
                        "1.1.1|like:3{you are Hisham",
                        "1.2|like:5|security:public{I like coffee",
                        "1.2.1|like:2{and me !"

                )), "samir Ali", 3 , "12", "jane" , "2004","male" ,"ahmed_Ali@gmail.com" , "12345678",new ArrayList<>(List.of(1,2,3)));

        persons.add(p);

        //---- Fetch actual user data
        Online=new Person(persons.get(0));

//



    }


}
