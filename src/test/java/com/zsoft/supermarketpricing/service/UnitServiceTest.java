package com.zsoft.supermarketpricing.service;

import com.zsoft.supermarketpricing.exception.IllegalConversionException;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

public class UnitServiceTest {

    @Test
    public void getConvertor() throws Exception, IllegalConversionException {
        Function<Float, Float> poundToOunce=UnitService.getConvertor("pound", "ounce");
        assertEquals("", 16.0f,poundToOunce.apply(1.0f),Double.MIN_VALUE);
    }

}