package com.pollub.lab.model.lab3;

public class Car {
    private String brand;
    private String model;
    private int productionYear;
    private int mileage;
    private int rentalPrice;

    public Car(String brand, String model, int productionYear, int mileage, int rentalPrice) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.mileage = mileage;
        this.rentalPrice = rentalPrice;
    }

    public String getBrand() {
        return brand;
    }


    public String getModel() {
        return model;
    }


    public int getProductionYear() {
        return productionYear;
    }


    public int getMileage() {
        return mileage;
    }


    public int getRentalPrice() {
        return rentalPrice;
    }
}
