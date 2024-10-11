package com.pollub.lab.service.lab1;

public class Car {
    private String brand;
    private String model;
    private int production_year;
    private int mileage;
    private int rental_price;

    public Car(String brand, String model, int production_year, int mileage, int rental_price) {
        this.brand = brand;
        this.model = model;
        this.production_year = production_year;
        this.mileage = mileage;
        this.rental_price = rental_price;
    }

    public String getBrand() {
        return brand;
    }


    public String getModel() {
        return model;
    }


    public int getProduction_year() {
        return production_year;
    }


    public int getMileage() {
        return mileage;
    }


    public int getRental_price() {
        return rental_price;
    }


}
