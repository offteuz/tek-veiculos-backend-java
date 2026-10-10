package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.mapper;

import br.com.teksystem.tekveiculos.vehicle.application.model.dto.input.CreateModelCommand;
import br.com.teksystem.tekveiculos.vehicle.application.model.dto.input.UpdateModelCommand;
import br.com.teksystem.tekveiculos.vehicle.application.model.dto.output.ModelResponse;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.dto.input.ModelRequestDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.dto.output.ModelResponseDTO;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.dto.input.ModelUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ModelWebMapper {
    CreateModelCommand toCreateModelCommand(ModelRequestDTO modelRequestDTO);

    @Mappings({
            @Mapping(target = "name", source = "modelUpdateDTO.name"),
            @Mapping(target = "active", source = "modelUpdateDTO.active"),
            @Mapping(target = "brandId", source = "modelUpdateDTO.brandId")
    })
    UpdateModelCommand toUpdateModelCommand(Long id, ModelUpdateDTO modelUpdateDTO);

    ModelResponseDTO toModelResponseDTO(ModelResponse modelResponse);

    List<ModelResponseDTO> toListModelResponseDTO(List<ModelResponse> modelResponseList);
}
