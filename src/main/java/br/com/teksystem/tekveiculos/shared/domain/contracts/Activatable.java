package br.com.teksystem.tekveiculos.shared.domain.contracts;

public interface Activatable {
    Boolean active();

    default void ensureIsActive() {
        if (!Boolean.TRUE.equals(active())) {
            throw new IllegalArgumentException(getClass().getSimpleName() + " is inactive.");
        }
    }
}
