package br.com.teksystem.tekveiculos.shared.domain.exception;

public abstract class BusinessException extends RuntimeException {
    // Desativa stacktrace para ganhos de performance em validações previsíveis
    protected BusinessException(String message) {
        super(message, null, false, false);
    }

    protected BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
