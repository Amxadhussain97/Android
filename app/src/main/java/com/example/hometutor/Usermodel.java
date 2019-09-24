package com.example.hometutor;

public class Usermodel {
    private  String Name;
    private  String Id;

    public Usermodel(String name, String id) {
        Name = name;
        this.Id = id;
    }
    public Usermodel() {

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