package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.inbound.web.model.dto.input;

public record ModelUpdateDTO(
        String name,

        Boolean active,

        Long brandId
) {
}
