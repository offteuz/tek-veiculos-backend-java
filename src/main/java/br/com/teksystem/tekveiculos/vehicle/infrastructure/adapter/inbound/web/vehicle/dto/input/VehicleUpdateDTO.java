package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.vehicle.dto.input;

import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Color;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.dto.output.ModelResponseDTO;

public record VehicleUpdateDTO(
        Long numberVehicle,

        Color color,

        String plate,

        Long currentMileage,

        Boolean active,

        Long modelId
) {
}
