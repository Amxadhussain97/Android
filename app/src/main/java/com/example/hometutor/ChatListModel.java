package com.example.hometutor;

public class ChatListModel {
    String Name;
    String Id;

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
        Id = id;
    }

    public ChatListModel(String name, String id) {
        Name = name;
        Id = id;
    }

    public ChatListModel() {
    }
}