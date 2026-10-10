package br.com.teksystem.tekveiculos.vehicle.application.brand.mapper;

import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.input.CreateBrandCommand;
import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.output.BrandResponse;
import br.com.teksystem.tekveiculos.vehicle.domain.brand.Brand;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BrandMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "active", constant = "true")
    })
    Brand toDomain(CreateBrandCommand createBrandCommand);

    CreateBrandCommand toCommand(Brand brand);

    BrandResponse toResponse(Brand brand);
}