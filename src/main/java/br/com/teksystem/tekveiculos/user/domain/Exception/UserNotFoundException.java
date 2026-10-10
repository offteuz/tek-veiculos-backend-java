package br.com.teksystem.tekveiculos.user.domain.Exception;

import br.com.teksystem.tekveiculos.shared.domain.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
