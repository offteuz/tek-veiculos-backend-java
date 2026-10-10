package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.vehicle;

import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.output.VehicleResponse;
import br.com.teksystem.tekveiculos.vehicle.application.vehicle.port.VehicleQueryPort;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.vehicle.mapper.VehicleJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VehicleQueryAdapter implements VehicleQueryPort {

    private final SpringDataVehicleRepository springDataVehicleRepository;
    private final VehicleJpaMapper vehicleJpaMapper;

    public VehicleQueryAdapter(SpringDataVehicleRepository springDataVehicleRepository, VehicleJpaMapper vehicleJpaMapper) {
        this.springDataVehicleRepository = springDataVehicleRepository;
        this.vehicleJpaMapper = vehicleJpaMapper;
    }

    @Override
    public List<VehicleResponse> findAllDetailed() {
        return springDataVehicleRepository.findAllDetailed()
                .stream()
                .map(vehicleJpaMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<VehicleResponse> findDetailedById(Long id) {
        return springDataVehicleRepository.findDetailedById(id)
                .map(vehicleJpaMapper::toResponse);
    }
}