package br.com.teksystem.tekveiculos.vehicle.application.vehicle.service;

import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.input.CreateVehicleCommand;
import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.input.UpdateVehicleCommand;
import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.output.VehicleResponse;
import br.com.teksystem.tekveiculos.vehicle.application.vehicle.mapper.VehicleMapper;
import br.com.teksystem.tekveiculos.vehicle.application.vehicle.port.VehicleQueryPort;
import br.com.teksystem.tekveiculos.vehicle.domain.model.exception.ModelNotFoundException;
import br.com.teksystem.tekveiculos.vehicle.domain.model.port.ModelRepository;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Mileage;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Plate;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Vehicle;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.exception.PlateAlreadyExistsException;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.exception.VehicleNotFoundException;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.port.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleQueryPort vehicleQueryPort;
    private final ModelRepository modelRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleService(
            VehicleRepository vehicleRepository,
            VehicleQueryPort vehicleQueryPort,
            ModelRepository modelRepository,
            VehicleMapper vehicleMapper
    ) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleQueryPort = vehicleQueryPort;
        this.modelRepository = modelRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Transactional
    public VehicleResponse save(CreateVehicleCommand command) {
        if (!modelRepository.existsById(command.modelId())) {
            throw new ModelNotFoundException(command.modelId());
        }

        Vehicle vehicle = vehicleMapper.toDomain(command);
        System.out.println("color: " + vehicle.getColor());
        System.out.println("number vehicle: " + vehicle.getNumberVehicle());

        if (vehicleRepository.existsByPlate(vehicle.getPlate().plate())) {
            throw new PlateAlreadyExistsException(vehicle.getPlate().plate());
        }

        Vehicle saved = vehicleRepository.save(vehicle);

        return vehicleQueryPort.findDetailedById(saved.getId())
                .orElseThrow(() -> new VehicleNotFoundException(saved.getId()));
    }

    public List<VehicleResponse> findAll() {
        return vehicleQueryPort.findAllDetailed();
    }

    public VehicleResponse findById(Long id) {
        return vehicleQueryPort.findDetailedById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
    }

    @Transactional
    public VehicleResponse update(Long id, UpdateVehicleCommand updateVehicleCommand) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));

        if (updateVehicleCommand.numberVehicle() != null) {
            vehicle.changeNumberVehicle(updateVehicleCommand.numberVehicle());
        }

        if (updateVehicleCommand.color() != null) {
            vehicle.changeColor(updateVehicleCommand.color());
        }

        if (updateVehicleCommand.plate() != null) {
            vehicle.changePlate(new Plate(updateVehicleCommand.plate()));
        }

        if (updateVehicleCommand.currentMileage() != null) {
            vehicle.registerMileage(new Mileage(updateVehicleCommand.currentMileage()));
        }

        if (updateVehicleCommand.active() != null) {
            vehicle.changeActive(updateVehicleCommand.active());
        }

        if (updateVehicleCommand.modelId() != null) {
            vehicle.changeModel(updateVehicleCommand.modelId());
        }

        if (modelRepository.findById(vehicle.getModelId()).isEmpty())
            throw new ModelNotFoundException(vehicle.getModelId());

        vehicleRepository.save(vehicle);

        return vehicleQueryPort.findDetailedById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
    }

    @Transactional
    public void deleteById(Long id) {
        vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));

        vehicleRepository.deleteById(id);
    }
}
