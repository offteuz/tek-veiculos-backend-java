package br.com.teksystem.tekveiculos.vehicle.domain.brand;

import br.com.teksystem.tekveiculos.shared.domain.contracts.Activatable;

import java.util.Objects;

public record Brand(Long id, String name, Boolean active) implements Activatable {
    public Brand {
        Objects.requireNonNull(name, "The brand name cannot be null");

        String normalizedName = name.trim().toUpperCase();
        if (normalizedName.isBlank()) {
            throw new IllegalArgumentException("The brand name cannot be blank");
        }

        name = normalizedName;
        active = active == null || active;
    }

    // Methods
    public static Brand newBrand(String name) {
        return new Brand(null, name, true);
    }

    public Brand changeName(String newName) {
        ensureIsActive();

        return new Brand(this.id, newName, this.active);
    }

    public Brand changeActive(Boolean newActive) {
        if (Objects.equals(newActive, this.active)) {
            return this;
        }

        return new Brand(this.id, this.name, newActive);
    }
}