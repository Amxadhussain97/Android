package com.example.hometutor;

public class SearchTeacherModel {
    String Name;
    String Email;
    String Id;
    public String getPosstid() {
        return Posstid;
    }

    public void setPosstid(String posstid) {
        Posstid = posstid;
    }

    String Posstid;

    public SearchTeacherModel() {
    }

    public String getName() {
        return Name;
    }
    public String getId() {
        return Id;
    }

    public void setName(String name) {
        Name = name;
    }
    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
