package com.goodzza.cheapeststore.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum UnitType {
    GRAM("gram", 1000),
    KILO_GRAM("kilogram", 100),
    LITER("L", 2000),
    MILLI_LITER("mL", 1000),
    EA("EA", 20),
    ;

    private static final List<UnitType> UNITS = Arrays.stream(UnitType.values()).collect(Collectors.toList());
    private static final Random RAND = new Random();
    @Getter
    private final String unitName;
    @Getter
    private final int upperLimit;

    public static UnitType getRandomUnit() {
        return UNITS.get(RAND.nextInt(UNITS.size()));
    }

}
