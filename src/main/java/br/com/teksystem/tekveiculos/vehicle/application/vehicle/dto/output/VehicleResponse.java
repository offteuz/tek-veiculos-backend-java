package br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.output;

import br.com.teksystem.tekveiculos.vehicle.application.model.dto.output.ModelResponse;
import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Color;

public record VehicleResponse(
        Long id,

        Long numberVehicle,

        Color color,

        String plate,

        Long currentMileage,

        Boolean active,

        ModelResponse model
) {
}
