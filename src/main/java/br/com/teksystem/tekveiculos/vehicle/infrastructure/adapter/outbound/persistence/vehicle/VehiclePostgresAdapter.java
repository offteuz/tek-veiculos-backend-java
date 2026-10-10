package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.vehicle;

import br.com.teksystem.tekveiculos.vehicle.domain.model.exception.ModelNotFoundException;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Vehicle;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.exception.VehicleNotFoundException;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.port.VehicleRepository;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.model.ModelJpaEntity;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.model.SpringDataModelRepository;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.vehicle.mapper.VehicleJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VehiclePostgresAdapter implements VehicleRepository {
    private final VehicleJpaMapper vehicleJpaMapper;
    private final SpringDataVehicleRepository springDataVehicleRepository;
    private final SpringDataModelRepository springDataModelRepository;

    public VehiclePostgresAdapter(
            VehicleJpaMapper vehicleJpaMapper,
            SpringDataVehicleRepository springDataVehicleRepository,
            SpringDataModelRepository springDataModelRepository
    ) {
        this.vehicleJpaMapper = vehicleJpaMapper;
        this.springDataVehicleRepository = springDataVehicleRepository;
        this.springDataModelRepository = springDataModelRepository;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        ModelJpaEntity modelJpaEntity = springDataModelRepository.findById(vehicle.getModelId())
                .orElseThrow(() -> new ModelNotFoundException(vehicle.getModelId()));

        VehicleJpaEntity vehicleJpaEntity = vehicleJpaMapper.toEntity(vehicle);
        vehicleJpaEntity.setModel(modelJpaEntity);

        VehicleJpaEntity vehicleJpaEntitySaved = springDataVehicleRepository.save(vehicleJpaEntity);

        return vehicleJpaMapper.toModel(vehicleJpaEntitySaved);
    }

    @Override
    public List<Vehicle> findAll() {
        return springDataVehicleRepository.findAll()
                .stream()
                .map(vehicleJpaMapper::toModel)
                .toList();
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return springDataVehicleRepository.findById(id).map(vehicleJpaMapper::toModel);
    }

    @Override
    public boolean existsByPlate(String plate) {
        return springDataVehicleRepository.existsByPlate(plate);
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        VehicleJpaEntity vehicleJpaEntity = springDataVehicleRepository.findById(vehicle.getId())
                .orElseThrow(() -> new VehicleNotFoundException(vehicle.getId()));

        vehicleJpaMapper.updateEntityFromModel(vehicle, vehicleJpaEntity);

        if (vehicle.getModelId() == null) {
            ModelJpaEntity modelJpaEntity = springDataModelRepository.findById(vehicle.getModelId())
                    .orElseThrow(() -> new ModelNotFoundException(vehicle.getModelId()));

            vehicleJpaEntity.setModel(modelJpaEntity);
        }

        VehicleJpaEntity vehicleJpaEntitySaved = springDataVehicleRepository.save(vehicleJpaEntity);

        return vehicleJpaMapper.toModel(vehicleJpaEntitySaved);
    }

    @Override
    public void deleteById(Long id) {
        springDataVehicleRepository.deleteById(id);
    }
}
