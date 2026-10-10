package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.vehicle.mapper;

import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.output.BrandResponse;
import br.com.teksystem.tekveiculos.vehicle.application.model.dto.output.ModelResponse;
import br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.output.VehicleResponse;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Mileage;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Plate;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Vehicle;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.brand.BrandJpaEntity;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.model.ModelJpaEntity;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.vehicle.VehicleJpaEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface VehicleJpaMapper {
    // Domain <-> JPA
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "model.id", target = "modelId")
    })
    Vehicle toModel(VehicleJpaEntity vehicleJpaEntity);

    @Mapping(target = "model", source = "modelId")
    VehicleJpaEntity toEntity(Vehicle vehicle);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "model", source = "modelId")
    })
    void updateEntityFromModel(Vehicle vehicle, @MappingTarget VehicleJpaEntity vehicleJpaEntity);

    // JPA <-> Application Response
    @Mappings({
            @Mapping(target = "plate", source = "plate"),
            @Mapping(target = "currentMileage", source = "currentMileage"),
            @Mapping(target = "model", source = "model")
    })
    VehicleResponse toResponse(VehicleJpaEntity vehicleJpaEntity);

    // Conversions
    default ModelJpaEntity mapModel(Long modelId) {
        if (modelId == null) {
            return null;
        }
        ModelJpaEntity modelJpaEntity = new ModelJpaEntity();
        modelJpaEntity.setId(modelId);
        return modelJpaEntity;
    }

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
