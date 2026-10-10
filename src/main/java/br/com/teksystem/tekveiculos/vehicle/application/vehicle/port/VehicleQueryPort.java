package br.com.teksystem.tekveiculos.vehicle.application.vehicle.port;

import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.output.VehicleResponse;

import java.util.List;
import java.util.Optional;

public interface VehicleQueryPort {
    List<VehicleResponse> findAllDetailed();

    Optional<VehicleResponse> findDetailedById(Long id);
}
