package br.com.teksystem.tekveiculos.vehicle.domain.vehicle.exception;

import br.com.teksystem.tekveiculos.shared.domain.exception.NotFoundException;

public class VehicleNotFoundException extends NotFoundException {
    public VehicleNotFoundException(Long id) {
        super("Vehicle Not Found with id: " + id);
    }

    public VehicleNotFoundException(String message) {
        super(message);
    }
}
