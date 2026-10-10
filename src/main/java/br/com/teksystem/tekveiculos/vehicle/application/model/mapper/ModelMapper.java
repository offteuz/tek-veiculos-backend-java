package br.com.teksystem.tekveiculos.vehicle.application.model.mapper;

import br.com.teksystem.tekveiculos.vehicle.application.model.dto.input.CreateModelCommand;
import br.com.teksystem.tekveiculos.vehicle.application.model.dto.output.ModelResponse;
import br.com.teksystem.tekveiculos.vehicle.domain.brand.Brand;
import br.com.teksystem.tekveiculos.vehicle.domain.model.Model;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ModelMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "active", constant = "true")
    })
    Model toDomain(CreateModelCommand createModelCommand);

    CreateModelCommand toCommand(Model model);

    @Mappings({
            @Mapping(target = "id", source = "model.id"),
            @Mapping(target = "name", source = "model.name"),
            @Mapping(target = "active", source = "model.active"),
            @Mapping(target = "brand", source = "brand")
    })
    ModelResponse toResponse(Model model, Brand brand);
}
