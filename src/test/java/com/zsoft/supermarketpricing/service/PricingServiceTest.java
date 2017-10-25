package com.zsoft.supermarketpricing.service;

import com.zsoft.supermarketpricing.domain.UnitPrice;
import com.zsoft.supermarketpricing.domain.WeightPrice;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PricingServiceTest {
    private UnitPrice unitPrice;
    private WeightPrice weightPrice;


    @Before
    public void setUp() throws Exception {
        unitPrice = new UnitPrice(0.65);
        weightPrice = new WeightPrice(1.99, "pound");
    }

    @Test
    public void getRawPrice() throws Exception {
        assertEquals("a unit item costs $0.65", 0.65, PricingService.getRawPrice(unitPrice, 1), Double.MIN_VALUE);
        assertEquals("$1.99/pound (so what does 4 ounces cost?)", 1.99 / 4, PricingService.getRawPrice(weightPrice, 4.0f, "ounce"), Double.MIN_VALUE);
    }

}