package com.example.supermrcket.model;

public class User {

    private String uid;
    private String name;
    private String Adders;
    private String Prodect;
    private String age;

    public User() {
    }

    public User(String uid, String name, String age,String Adders,String Prodect) {
        this.uid = uid;
        this.name = name;
        this.Adders = Adders;
        this.age = age;
        this.Prodect = Prodect;
    }

    public String getProdect() {
        return Prodect;
    }

    public void setProdect(String prodect) {
        Prodect = prodect;
    }

    public User(String name, String age, String Adders, String Prodect) {
        this.name = name;
        this.age = age;
        this.Adders = Adders;
        this.Prodect = Prodect;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdders() {
        return Adders;
    }

    public void setAdders(String adders) {
        Adders = adders;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            User user= (User) obj;
            return this.uid.equals(user.getUid());
        }
        else
            return  false;
    }
}