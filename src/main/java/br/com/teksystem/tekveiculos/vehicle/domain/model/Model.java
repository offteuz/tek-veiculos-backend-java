package br.com.teksystem.tekveiculos.vehicle.domain.model;

import br.com.teksystem.tekveiculos.shared.domain.contracts.Activatable;

import java.util.Objects;

public record Model(Long id, String name, Boolean active, Long brandId) implements Activatable {
    public Model {
        Objects.requireNonNull(name, "The name of model cannot be null");
        Objects.requireNonNull(brandId, "The id of brand cannot be null");

        String normalizedName = name.trim().toUpperCase();
        if (normalizedName.isBlank()) {
            throw new IllegalArgumentException("The name of brand cannot be blank");
        }

        name = normalizedName;
        active = active == null || active;
    }

    // Methods
    public static Model newModel(String name, Long brandId) {
        return new Model(null, name, true, brandId);
    }

    public Model changeName(String newName) {
        ensureIsActive();

        return new Model(this.id, newName, this.active, this.brandId);
    }

    public Model changeActive(Boolean newActive) {
        if (Objects.equals(newActive, this.active)) {
            return this;
        }

        return new Model(this.id, this.name, newActive, this.brandId);
    }

    public Model changeBrand(Long brandId) {
        ensureIsActive();

        return new Model(this.id, this.name, this.active, brandId);
    }
}
