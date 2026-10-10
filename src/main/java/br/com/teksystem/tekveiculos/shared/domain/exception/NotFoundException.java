package br.com.teksystem.tekveiculos.shared.domain.exception;

public abstract class NotFoundException extends BusinessException {
    protected NotFoundException(String message) {
        super(message);
    }
}
