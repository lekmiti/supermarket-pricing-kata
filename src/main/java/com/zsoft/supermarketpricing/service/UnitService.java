package com.zsoft.supermarketpricing.service;

import com.zsoft.supermarketpricing.domain.WeightUnit;
import com.zsoft.supermarketpricing.exception.IllegalConversionException;

import java.util.function.Function;

public class UnitService {

    public static Function<Float, Float> getConvertor(WeightUnit inputUnit, WeightUnit outputUnit) throws IllegalConversionException {
        if (inputUnit == null) {
            throw new NullPointerException("input unit shouldn't be null.");
        }
        if (outputUnit == null) {
            throw new NullPointerException("output unit shouldn't be null.");
        }
        if (outputUnit == inputUnit) {
            return weight -> weight;
        }
        switch (inputUnit) {
            case OUNCE:
                return weight -> weight / 16;
            case POUND:
                return weight -> weight * 16;
            default:
                throw new IllegalConversionException();
        }

    }

}
