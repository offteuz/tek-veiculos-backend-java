package br.com.teksystem.tekveiculos.vehicle.application.model.dto.output;

import br.com.teksystem.tekveiculos.vehicle.application.brand.dto.output.BrandResponse;

public record ModelResponse(
        Long id,

        String name,

        Boolean active,

        BrandResponse brand
) {
}