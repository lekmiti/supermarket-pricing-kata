package com.zsoft.supermarketpricing.service;

import com.zsoft.supermarketpricing.exception.IllegalConversionException;
import org.junit.Test;

import java.util.function.Function;

import static com.zsoft.supermarketpricing.domain.WeightUnit.OUNCE;
import static com.zsoft.supermarketpricing.domain.WeightUnit.POUND;
import static org.junit.Assert.assertEquals;

public class UnitServiceTest {

    @Test
    public void getConvertor() throws Exception {
        Function<Float, Float> poundToOunce=UnitService.getConvertor(POUND, OUNCE);
        assertEquals("One Pound equals 16 Ounces", 16.0f,poundToOunce.apply(1.0f), Double.MIN_VALUE);

        Function<Float, Float> ounceToPound=UnitService.getConvertor(OUNCE, POUND);
        assertEquals("16 Ounce equals 1 Pound", 1.0f,ounceToPound.apply(16.0f), Double.MIN_VALUE);

        Function<Float, Float> poundToPound=UnitService.getConvertor(POUND, POUND);
        assertEquals("Convert one Pound return one Pound", 1.0f,poundToPound.apply(1.0f), Double.MIN_VALUE);



    }
    @Test(expected = NullPointerException.class)
    public void getConvertorWithException() throws Exception, IllegalConversionException {


        Function<Float, Float> nullToOunce=UnitService.getConvertor(null, OUNCE);
        assertEquals("nullPointerException, the input unit is null", 16.0f,nullToOunce.apply(1.0f), Double.MIN_VALUE);

        Function<Float, Float> poundToNull=UnitService.getConvertor(POUND, null);
        assertEquals("nullPointerException, the output unit is null", 16.0f,poundToNull.apply(1.0f), Double.MIN_VALUE);

    }


}