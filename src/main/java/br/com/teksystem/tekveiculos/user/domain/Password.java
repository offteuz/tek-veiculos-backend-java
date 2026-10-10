package br.com.teksystem.tekveiculos.user.domain;

import java.util.Objects;

public record Password(
        String password
) {
    public Password {
        Objects.requireNonNull(password);

        if (!password.matches("\"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$\"\n")) {
            throw new IllegalArgumentException("Invalid Password: " + password);
        }
    }
}
