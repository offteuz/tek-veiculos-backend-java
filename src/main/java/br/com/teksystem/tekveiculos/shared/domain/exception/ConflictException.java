package br.com.teksystem.tekveiculos.shared.domain.exception;

public class ConflictException extends BusinessException {
    public ConflictException(String message) {
        super(message);
    }
}
