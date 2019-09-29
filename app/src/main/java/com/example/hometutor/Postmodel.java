package com.example.hometutor;

import android.widget.EditText;

public class Postmodel {
    String poster;
    String district;
   // String location;
    String post;
   /* String gender;
    String clas;*/
    String owner;

    public String getPostid() {
        return postid;
    }

    public Postmodel(String district,  String post,String poster, String owner, Long posttime) {
        this.district = district;
        this.poster = poster;
        this.post = post;
        this.owner = owner;
        this.posttime = posttime;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    String postid;
    //salry;
    Long posttime;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

   /* public String getLocation() {
        return location;
    }*/

    public void setPost(String post) {
        this.post = post;
    }
    public String getPost() {
        return post;
    }

   /* public void setLocation(String location) {
        this.location = location;
    }*/

   /* public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }*/

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

  /*  public String getSalry() {
        return salry;
    }

    public void setSalry(String salry) {
        this.salry = salry;
    }*/

    public Long getPosttime() {
        return posttime;
    }
  /*  public String getClas() {
        return clas;
    }*/

    public void setPosttime(Long posttime) {
        this.posttime = posttime;
    }

    public Postmodel() {
    }


}
