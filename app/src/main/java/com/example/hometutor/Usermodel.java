package com.example.hometutor;

public class Usermodel {
    private  String Name,District;
    private  String Id;

    public Usermodel(String name, String district, String id) {
        Name = name;
        District = district;
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}