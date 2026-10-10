package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.vehicle.converter;

import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Color;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ColorConverter implements AttributeConverter<Color, String> {

    @Override
    public String convertToDatabaseColumn(Color attribute) {
        return attribute == null ? null : attribute.getDescription();
    }

    @Override
    public Color convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Color.fromDescription(dbData);
    }
}
