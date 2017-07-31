package com.example.brianphiri.farm.models;

/**
 * Created by brianphiri on 7/31/17.
 */

public class Users {
    String name, email, type;

    public Users() {
    }

    public Users(String name, String email, String type) {
        this.name = name;
        this.email = email;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
