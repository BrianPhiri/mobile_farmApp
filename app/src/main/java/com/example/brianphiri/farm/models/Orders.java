package com.example.brianphiri.farm.models;

/**
 * Created by brianphiri on 7/30/17.
 */

public class Orders {
    public String product, description, farmer, user;

    public Orders() {
    }

    public Orders(String product, String description, String farmer, String user) {
        this.product = product;
        this.description = description;
        this.farmer = farmer;
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
