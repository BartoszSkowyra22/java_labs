package com.pollub.lab.model.lab3;

public class FamilyCar extends Car {
    private int seatingCapacity;

    public FamilyCar(String brand, String model, int productionYear, int mileage, int rentalPrice, int seatingCapacity) {
        super(brand, model, productionYear, mileage, rentalPrice);
        this.seatingCapacity = seatingCapacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }
}

