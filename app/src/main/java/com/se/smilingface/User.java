package com.se.smilingface;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private int normal;
    private int unhappy;
    private int happy;


    public User() {
    }

    public User(int id, String name, String email, String password, int happy, int normal, int unhappy) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.happy = happy;
        this.normal = normal;
        this.unhappy = unhappy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getUnhappy() {
        return unhappy;
    }

    public void setUnhappy(int unhappy) {
        this.unhappy = unhappy;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", happy=" + happy +
                ", normal=" + normal +
                ", unhappy=" + unhappy +
                '}';
    }
}
