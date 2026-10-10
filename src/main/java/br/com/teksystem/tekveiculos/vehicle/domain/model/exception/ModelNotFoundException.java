package br.com.teksystem.tekveiculos.vehicle.domain.model.exception;

import br.com.teksystem.tekveiculos.shared.domain.exception.NotFoundException;

public class ModelNotFoundException extends NotFoundException {
    public ModelNotFoundException(Long id) {
        super("Model not found with id " + id);
    }

    public ModelNotFoundException(String message) {
        super(message);
    }
}
