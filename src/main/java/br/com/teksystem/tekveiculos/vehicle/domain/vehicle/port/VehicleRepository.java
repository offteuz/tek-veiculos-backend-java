package br.com.teksystem.tekveiculos.vehicle.domain.vehicle.port;

import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);

    List<Vehicle> findAll();

    Optional<Vehicle> findById(Long id);

    boolean existsByPlate(String plate);

    Vehicle update(Vehicle vehicle);

    void deleteById(Long id);
}
