package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.vehicle.dto.input;

import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Color;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleRequestDTO(
        @NotNull(message = "The number vehicle is mandatory")
        Long numberVehicle,

        @NotNull(message = "The vehicle color is mandatory")
        Color color,

        @NotBlank(message = "The vehicle plate is mandatory")
        String plate,

        @NotNull(message = "The vehicle's current mileage is mandatory")
        Long currentMileage,

        @NotNull(message = "The model ID is mandatory")
        Long modelId
) {
}
