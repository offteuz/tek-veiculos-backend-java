package br.com.teksystem.tekveiculos.user.domain;

import java.util.Objects;

public record Name(
        String name
) {
    public Name {
        Objects.requireNonNull(name);

        String normalizedName = name.trim().toUpperCase();

        if (!name.matches("^[A-Z]{3} [A-Z]{3}$")) {
            throw new IllegalArgumentException("Invalid Name Format: " + name);
        }

        name = normalizedName;
    }
}
