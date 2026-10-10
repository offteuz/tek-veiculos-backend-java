package br.com.teksystem.tekveiculos.vehicle.application.model.dto.input;

public record UpdateModelCommand(
        String name,

        Boolean active,

        Long brandId
) {
}
