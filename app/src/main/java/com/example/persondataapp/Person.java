package com.example.persondataapp;

import java.util.ArrayList;
import java.util.Comparator;

public class Person{
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


    public static Comparator<Person> personName = new Comparator<Person>() {
        @Override
        public int compare(Person person1, Person person2) {
            String name1 = person1.getName();
            String name2 = person2.getName();

            return name1.compareTo(name2);
        }
    };

    public static Comparator<Person> personAge = new Comparator<Person>() {
        @Override
        public int compare(Person person1, Person person2) {
            int age1 = person1.getAge();
            int age2 = person2.getAge();

            return age1 - age2;
        }
    };

}
