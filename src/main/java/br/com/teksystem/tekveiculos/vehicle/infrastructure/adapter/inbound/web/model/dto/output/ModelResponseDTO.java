package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.dto.output;

import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.brand.dto.output.BrandResponseDTO;

public record ModelResponseDTO(
        Long id,

        String name,

        Boolean active,

        BrandResponseDTO brand
) {
}
