package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ModelRequestDTO(
        @NotBlank(message = "The model name is mandatory")
        @Size(min = 2, message = "The model name cannot contain fewer than 2 characters")
        String name,

        @NotNull(message = "The brand ID is mandatory")
        Long brandId
) {
}
