package br.com.teksystem.tekveiculos.vehicle.application.vehicle.mapper;

import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.input.CreateVehicleCommand;
import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.output.VehicleResponse;
import br.com.teksystem.tekveiculos.vehicle.domain.model.Model;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Mileage;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Plate;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Vehicle;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface VehicleMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "active", constant = "true"),
            @Mapping(target = "color", source = "color"),
            @Mapping(target = "numberVehicle", source = "numberVehicle"),
            @Mapping(target = "plate", source = "plate"),
            @Mapping(target = "currentMileage", source = "currentMileage"),
            @Mapping(target = "modelId", source = "modelId")
    })
    Vehicle toDomain(CreateVehicleCommand command);

    @Mappings({
            @Mapping(target = "plate", source = "plate"),
            @Mapping(target = "currentMileage", source = "currentMileage"),
    })
    CreateVehicleCommand toCommand(Vehicle vehicle);

    @Mappings({
            @Mapping(target = "id", source = "vehicle.id"),
            @Mapping(target = "numberVehicle", source = "vehicle.numberVehicle"),
            @Mapping(target = "color", source = "vehicle.color"),
            @Mapping(target = "plate", source = "vehicle.plate.plate"),
            @Mapping(target = "currentMileage", source = "vehicle.currentMileage.currentMileage"),
            @Mapping(target = "active", source = "vehicle.active"),
            @Mapping(target = "model", source = "model")
    })
    VehicleResponse toResponse(Vehicle vehicle, Model model);

    // Conversions
    default String mapPlate(Plate plate) {
        return plate == null ? null : plate.plate();
    }

    default Plate toPlate(String plate) {
        return plate == null ? null : new Plate(plate);
    }

    default Long mapMileage(Mileage currentMileage) {
        return currentMileage == null ? null : currentMileage.currentMileage();
    }

    default Mileage toMileage(Long currentMileage) {
        return currentMileage == null ? null : new Mileage(currentMileage);
    }
}
