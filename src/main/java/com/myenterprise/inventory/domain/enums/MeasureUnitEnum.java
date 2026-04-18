package com.myenterprise.inventory.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MeasureUnitEnum {
    PCS("Pieces", "pcs"),
    KILOGRAM("Kilograms", "kg"),
    LITER("Liters", "l");

    private final String description;
    private final String abbreviation;

    @JsonCreator
    public static MeasureUnitEnum fromValue(String value) {
        if (value == null) {
            return null;
        }

        for (MeasureUnitEnum unit : values()) {
            if (unit.getAbbreviation().equalsIgnoreCase(value)
                    || unit.name().equalsIgnoreCase(value)
                    || unit.getDescription().equalsIgnoreCase(value)) {
                return unit;
            }
        }

        throw new IllegalArgumentException("Unknown measure unit: " + value);
    }

    @JsonValue
    public String toJsonValue() {
        return abbreviation;
    }

}
