package com.example.persondataapp;

import java.util.ArrayList;

public class Person {
    private String name;
    private int age;
    private String school;

    public Person (String aName, int anAge, String aSchool) {
        name = aName;
        age = anAge;
        school = aSchool;
    }

    public String getName(){
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSchool() { return school; }

    public void setName(String name) { this.name = name;}
    public void setAge (int age) { this.age = age; }
    public void setSchool (String school) { this.school = school;}

}
