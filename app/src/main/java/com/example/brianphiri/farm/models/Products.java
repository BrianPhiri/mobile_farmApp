package com.example.brianphiri.farm.models;

/**
 * Created by brianphiri on 7/30/17.
 */

public class Products {
    public String product, description ,price, location, farmer;

    public Products() {
    }

    public Products(String product, String description, String price, String location, String farmer) {
        this.product = product;
        this.description = description;
        this.price = price;
        this.location = location;
        this.farmer = farmer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }
}
