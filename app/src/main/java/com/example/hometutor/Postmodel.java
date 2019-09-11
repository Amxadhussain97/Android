package com.example.hometutor;

public class Postmodel {
    String poster;
    String location;
    String gender;
    String clas;
    String owner;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    String postid,salry;
    Long posttime;

    public Postmodel(String poster, String location, String gender, String clas,String owner, String salry, Long posttime) {
        this.poster = poster;
        this.location = location;
        this.gender = gender;
        this.clas = clas;
        this.owner = owner;
        this.salry = salry;
        this.posttime = posttime;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSalry() {
        return salry;
    }

    public void setSalry(String salry) {
        this.salry = salry;
    }

    public Long getPosttime() {
        return posttime;
    }
    public String getClas() {
        return clas;
    }

    public void setPosttime(Long posttime) {
        this.posttime = posttime;
    }

    public Postmodel() {
    }


}
