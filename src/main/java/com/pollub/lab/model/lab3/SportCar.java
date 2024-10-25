package com.pollub.lab.model.lab3;

public class SportCar extends Car {
    private int topSpeed;

    public SportCar(String brand, String model, int productionYear, int mileage, int rentalPrice, int topSpeed) {
        super(brand, model, productionYear, mileage, rentalPrice);
        this.topSpeed = topSpeed;
    }

    public int getTopSpeed() {
        return topSpeed;
    }
}

