package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.brand.mapper;

import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.input.CreateBrandCommand;
import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.input.UpdateBrandCommand;
import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.output.BrandResponse;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.brand.dto.input.BrandRequestDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.brand.dto.output.BrandResponseDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.brand.dto.input.BrandUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BrandWebMapper {
    CreateBrandCommand toCreateBrandCommand(BrandRequestDTO brandUpdateDTO);

    @Mapping(target = "name", source = "brandUpdateDTO.name")
    @Mapping(target = "active", source = "brandUpdateDTO.active")
    UpdateBrandCommand toUpdateBrandCommand(Long id, BrandUpdateDTO brandUpdateDTO);

    BrandResponseDTO toBrandResponseDTO(BrandResponse response);

    List<BrandResponseDTO> toBrandResponseDTO(List<BrandResponse> responses);
}
