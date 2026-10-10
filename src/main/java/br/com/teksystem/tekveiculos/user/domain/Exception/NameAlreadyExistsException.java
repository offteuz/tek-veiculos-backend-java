package br.com.teksystem.tekveiculos.user.domain.Exception;

import br.com.teksystem.tekveiculos.shared.domain.exception.ConflictException;

public class NameAlreadyExistsException extends ConflictException {
    public NameAlreadyExistsException(String name) {
        super("There is already a user with that name: " + name);
    }
}
