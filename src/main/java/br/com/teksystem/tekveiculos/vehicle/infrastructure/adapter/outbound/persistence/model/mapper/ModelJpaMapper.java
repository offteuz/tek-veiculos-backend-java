package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.model.mapper;

import br.com.teksystem.tekveiculos.vehicle.domain.model.Model;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.brand.BrandJpaEntity;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.model.ModelJpaEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ModelJpaMapper {
    @Mapping(source = "brand.id", target = "brandId")
    Model toModel(ModelJpaEntity modelJpaEntity);

    @Mapping(target = "brand", source = "brandId")
    ModelJpaEntity toEntity(Model model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "brand", source = "brandId")
    })
    void updateEntityFromModel(Model model, @MappingTarget ModelJpaEntity modelJpaEntity);

    // Conversions
    default BrandJpaEntity mapBrand(Long brandId) {
        if (brandId == null) {
            return null;
        }
        BrandJpaEntity brandJpaEntity = new BrandJpaEntity();
        brandJpaEntity.setId(brandId);
        return brandJpaEntity;
    }
}