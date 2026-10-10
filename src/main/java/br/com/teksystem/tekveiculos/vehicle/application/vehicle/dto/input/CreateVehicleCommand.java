package br.com.teksystem.tekveiculos.vehicle.application.vehicle.dto.input;

import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Color;

public record CreateVehicleCommand(
        Long numberVehicle,

        Color color,

        String plate,

        Long currentMileage,

        Long modelId
) {
}
