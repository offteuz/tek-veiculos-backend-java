package br.com.teksystem.tekveiculos.vehicle.domain.vehicle.exception;

import br.com.teksystem.tekveiculos.shared.domain.exception.ConflictException;

public class PlateAlreadyExistsException extends ConflictException {
    public PlateAlreadyExistsException(String plate) {
        super("There is already a vehicle with this plate: " + plate);
    }
}
