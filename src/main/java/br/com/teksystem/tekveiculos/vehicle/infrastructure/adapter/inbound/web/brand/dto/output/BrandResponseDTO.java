package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.brand.dto.output;

public record BrandResponseDTO(
        Long id,

        String name,

        Boolean active
) {
}
