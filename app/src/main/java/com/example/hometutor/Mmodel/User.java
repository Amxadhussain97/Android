package com.example.hometutor.Mmodel;

public class User {
    private  String Name;
    private  String id;

    public User(String name, String id) {
        Name = name;
        this.id = id;
    }
    public User() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
