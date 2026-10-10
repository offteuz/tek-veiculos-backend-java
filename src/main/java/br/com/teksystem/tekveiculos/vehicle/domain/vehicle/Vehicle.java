package br.com.teksystem.tekveiculos.vehicle.domain.vehicle;

import br.com.teksystem.tekveiculos.shared.domain.contracts.Activatable;

import java.util.Objects;

public class Vehicle implements Activatable {
    private Long id;

    private Long numberVehicle;

    private Color color;

    private Plate plate;

    private Mileage currentMileage;

    private Boolean active;

    private Long modelId;

    // Constructors
    public Vehicle() {
    }

    public Vehicle(Long id, Long numberVehicle, Color color, Plate plate, Mileage currentMileage, Boolean active, Long modelId) {
        this.id = id;
        this.numberVehicle = Objects.requireNonNull(numberVehicle);
        this.color = Objects.requireNonNull(color);
        this.plate = Objects.requireNonNull(plate);
        this.currentMileage = currentMileage != null ? currentMileage : new Mileage(0L);
        this.active = active;
        this.modelId = Objects.requireNonNull(modelId);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getNumberVehicle() {
        return numberVehicle;
    }

    public Color getColor() {
        return color;
    }

    public Plate getPlate() {
        return plate;
    }

    public Mileage getCurrentMileage() {
        return currentMileage;
    }

    public Boolean getActive() {
        return active;
    }

    public Long getModelId() {
        return modelId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNumberVehicle(Long numberVehicle) {
        this.numberVehicle = numberVehicle;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public void setCurrentMileage(Mileage currentMileage) {
        this.currentMileage = currentMileage;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Objects.equals(getId(), vehicle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    // Methods
    @Override
    public Boolean active() {
        return this.active;
    }

    @Override
    public void ensureIsActive() {
        Activatable.super.ensureIsActive();
    }

    public static Vehicle newVehicle(Long vehicleNumber, Color color, Plate plate, Mileage currentMileage, Long modelId) {
        return new Vehicle(null, vehicleNumber, color, plate, currentMileage, true, modelId);
    }

    public void changeNumberVehicle(Long newNumberVehicle) {
        ensureIsActive();

        this.numberVehicle = newNumberVehicle;
    }

    public void changeColor(Color newColor) {
        ensureIsActive();

        this.color = newColor;
    }

    public void changePlate(Plate newPlate) {
        ensureIsActive();

        this.plate = newPlate;
    }

    /*
     *  Metodo também usado no "Update".
     *  Diferente dos outros devido a sua regra de negócio específica.
     */
    public void registerMileage(Mileage newMileage) {
        if (newMileage.lessThanCurrentMileage(this.currentMileage)) {
            throw new IllegalArgumentException("The new Mileage doesn't match the actual Mileage");
        }

        this.currentMileage = newMileage;
    }

    public void changeActive(Boolean newActive) {
        if (Objects.equals(newActive, this.active)) {
            return;
        }

        this.active = newActive;
    }

    public void changeModel(Long modelId) {
        ensureIsActive();

        this.modelId = modelId;
    }
}
