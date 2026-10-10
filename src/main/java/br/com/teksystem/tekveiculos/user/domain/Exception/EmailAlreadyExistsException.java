package br.com.teksystem.tekveiculos.user.domain.Exception;

import br.com.teksystem.tekveiculos.shared.domain.exception.ConflictException;

public class EmailAlreadyExistsException extends ConflictException {
    public EmailAlreadyExistsException(String email) {
        super("There is already a user with that e-mail: " + email);
    }
}
