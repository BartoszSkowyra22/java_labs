package com.pollub.lab.model;

public class LuxuryCar extends Car {
    private boolean hasMassageSeats;

    public LuxuryCar(String brand, String model, int productionYear, int mileage, int rentalPrice, boolean hasMassageSeats) {
        super(brand, model, productionYear, mileage, rentalPrice);
        this.hasMassageSeats = hasMassageSeats;
    }

    public boolean hasMassageSeats() {
        return hasMassageSeats;
    }
}
