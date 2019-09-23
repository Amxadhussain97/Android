package com.example.hometutor.Mmodel;

public class User {
    private  String Name;
    private  String Id;

    public User(String name, String id) {
        Name = name;
        this.Id = id;
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
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }
}
