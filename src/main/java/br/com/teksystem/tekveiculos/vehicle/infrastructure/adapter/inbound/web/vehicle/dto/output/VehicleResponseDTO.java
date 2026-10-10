package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.vehicle.dto.output;

import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Color;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Mileage;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Plate;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.dto.output.ModelResponseDTO;

public record VehicleResponseDTO(
        Long id,

        Long numberVehicle,

        Color color,

        Plate plate,

        Mileage currentMileage,

        Boolean active,

        ModelResponseDTO model
) {
}
