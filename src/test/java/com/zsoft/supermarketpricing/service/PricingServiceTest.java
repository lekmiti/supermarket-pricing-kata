package com.zsoft.supermarketpricing.service;

import com.zsoft.supermarketpricing.domain.Discount;
import com.zsoft.supermarketpricing.domain.UnitPrice;
import com.zsoft.supermarketpricing.domain.WeightPrice;
import com.zsoft.supermarketpricing.domain.WeightUnit;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class PricingServiceTest {

    private UnitPrice unitPrice;
    private WeightPrice weightPrice;


    @Before
    public void setUp() throws Exception {
        unitPrice = new UnitPrice(0.65f);
        weightPrice = new WeightPrice(1.99f, WeightUnit.POUND);
    }

    @Test
    public void getRawPrice() throws Exception {
        assertEquals("a unit item costs $0.65", 0.65, PricingService.getRawPrice(unitPrice, 1), Double.MIN_VALUE);
        assertEquals("$1.99/pound (so what does 4 ounces cost?)", 1.99 / 4, PricingService.getRawPrice(weightPrice, 4.0f, WeightUnit.OUNCE), Double.MIN_VALUE);
    }


    @Test
    public void getDiscountPrice() throws Exception {
        Discount treeForOne = new Discount(1.0f, 3f);
        Discount sixForOneAndHalf = new Discount(1.5f, 6f);
        List<Discount> discounts = asList(treeForOne, sixForOneAndHalf);


        assertEquals("Five units costs 2 Dollars: 1 Dollar (treeForOne discount) + 1 Dollar ( 2 units) ", 2f, PricingService.getDiscountPrice(new UnitPrice(0.5f), 5.0f, discounts), Double.MIN_VALUE);
        assertEquals("Three units costs 1 Dollar: treeForOne discount selected", 1f, PricingService.getDiscountPrice(new UnitPrice(0.5f), 3.0f, discounts), Double.MIN_VALUE);
        assertEquals("Six units costs 1.5 Dollars: sixForOneAndHalf discount selected", 1.5f, PricingService.getDiscountPrice(new UnitPrice(0.5f), 6.0f, discounts), Double.MIN_VALUE);


        assertEquals("32 Ounce costs 2 Dollars: 32 Ounce equals 2 Pounds, each costs 1 Dollar (No discount selected)", 2f, PricingService.getDiscountPrice(new WeightPrice(1f, WeightUnit.POUND), 32f, WeightUnit.OUNCE, discounts), Double.MIN_VALUE);
        assertEquals("48 Ounce costs 1 Dollar: 48 Ounce equals 3 Pounds, the treeForOne discount selected", 1f, PricingService.getDiscountPrice(new WeightPrice(1f, WeightUnit.POUND), 48f, WeightUnit.OUNCE, discounts), Double.MIN_VALUE);
        assertEquals("96 Ounce costs 1.5 Dollar: 96 Ounce equals 6 Pounds, the sixForOneAndHalf discount selected", 1.5f, PricingService.getDiscountPrice(new WeightPrice(1f, WeightUnit.POUND), 96f, WeightUnit.OUNCE, discounts), Double.MIN_VALUE);

    }
}