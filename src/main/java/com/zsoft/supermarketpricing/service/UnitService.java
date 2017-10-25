package com.zsoft.supermarketpricing.service;

import com.zsoft.supermarketpricing.exception.IllegalConversionException;

import java.util.function.Function;

public class UnitService {

    public static Function<Float, Float> getConvertor(String inputUnit, String outputUnit) throws IllegalConversionException {
        throw new IllegalConversionException();


    }

}
