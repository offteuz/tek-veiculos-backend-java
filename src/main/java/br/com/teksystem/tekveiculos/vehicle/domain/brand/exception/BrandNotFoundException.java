package br.com.teksystem.tekveiculos.vehicle.domain.brand.exception;

import br.com.teksystem.tekveiculos.shared.domain.exception.NotFoundException;

public class BrandNotFoundException extends NotFoundException {
    public BrandNotFoundException(Long id) {
        super("Brand Not Found with id: " + id);
    }

    public BrandNotFoundException(String message) {
        super(message);
    }
}
