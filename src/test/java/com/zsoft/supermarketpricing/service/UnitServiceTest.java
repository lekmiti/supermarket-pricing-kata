package com.zsoft.supermarketpricing.service;

import com.zsoft.supermarketpricing.exception.IllegalConversionException;
import org.junit.Test;

import java.util.function.Function;

import static com.zsoft.supermarketpricing.domain.WeightUnit.OUNCE;
import static com.zsoft.supermarketpricing.domain.WeightUnit.POUND;
import static org.junit.Assert.assertEquals;

public class UnitServiceTest {

    @Test
    public void getConvertor() throws Exception, IllegalConversionException {
        Function<Float, Float> poundToOunce=UnitService.getConvertor(POUND, OUNCE);
        assertEquals("", 16.0f,poundToOunce.apply(1.0f), Double.MIN_VALUE);
    }

}