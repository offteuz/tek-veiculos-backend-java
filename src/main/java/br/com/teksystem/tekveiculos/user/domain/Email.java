package br.com.teksystem.tekveiculos.user.domain;

import java.util.Objects;

public record Email(
        String email
) {
    public Email {
        Objects.requireNonNull(email);

        if (!email.matches("/^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.([a-z]+)?$/i\n")) {
            throw new IllegalArgumentException("Invalid Email Address: " + email);
        }
    }
}
