package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.brand.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BrandRequestDTO(
        @NotBlank(message = "The brand name is mandatory")
        @Size(min = 2, message = "The brand name cannot contain fewer than 2 characters")
        String name
) {
}
