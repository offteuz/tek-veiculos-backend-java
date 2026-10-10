package br.com.teksystem.tekveiculos.vehicle.application.model.dto.input;

public record CreateModelCommand(
        String name,

        Long brandId
) {}
