package br.com.teksystem.tekveiculos.vehicle.domain.vehicle;

import java.util.Objects;

public record Plate(
        String plate
) {
    public Plate {
        Objects.requireNonNull(plate);

        String normalizedPlate = plate.trim().toUpperCase();

        if (!plate.matches("^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$")) {
            throw new IllegalArgumentException("Invalid Plate: " + plate);
        }

        plate = normalizedPlate;
    }
}
