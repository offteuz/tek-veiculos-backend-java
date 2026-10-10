package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.vehicle.mapper;

import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.input.CreateVehicleCommand;
import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.input.UpdateVehicleCommand;
import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.output.VehicleResponse;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Mileage;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Plate;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.vehicle.dto.input.VehicleRequestDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.vehicle.dto.input.VehicleUpdateDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.vehicle.dto.output.VehicleResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface VehicleWebMapper {
    @Mappings({
            @Mapping(target = "plate", source = "plate"),
            @Mapping(target = "currentMileage", source = "currentMileage"),
    })
    CreateVehicleCommand toCreateVehicleCommand(VehicleRequestDTO vehicleRequestDTO);

    @Mappings({
            @Mapping(target = "plate", source = "plate"),
            @Mapping(target = "currentMileage", source = "currentMileage"),
    })
    UpdateVehicleCommand toUpdateVehicleCommand(VehicleUpdateDTO vehicleUpdateDTO);

    @Mappings({
            @Mapping(target = "plate", source = "plate"),
            @Mapping(target = "currentMileage", source = "currentMileage"),
    })
    VehicleResponseDTO toVehicleResponseDTO(VehicleResponse vehicleResponse);

    @Mappings({
            @Mapping(target = "plate", source = "plate"),
            @Mapping(target = "currentMileage", source = "currentMileage"),
    })
    List<VehicleResponseDTO> toListVehicleResponseDTO(List<VehicleResponse> vehicleResponseList);

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
