package br.com.teksystem.tekveiculos.vehicle.domain.vehicle;

import java.util.Objects;

public record Mileage(
        Long currentMileage
) {
    public Mileage {
        Objects.requireNonNull(currentMileage);

        if (currentMileage < 0)  {
            throw new IllegalArgumentException("Mileage cannot be negative");
        }
    }

    public boolean lessThanCurrentMileage(Mileage otherMileage) {
        return this.currentMileage < otherMileage.currentMileage;
    }
}
