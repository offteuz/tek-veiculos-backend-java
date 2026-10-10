package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.brand.mapper;

import br.com.teksystem.tekveiculos.vehicle.domain.brand.Brand;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.brand.BrandJpaEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BrandJpaMapper {
    Brand toModel(BrandJpaEntity brandJpaEntity);

    BrandJpaEntity toEntity(Brand brand);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDomain(Brand brand, @MappingTarget BrandJpaEntity brandJpaEntity);
}
