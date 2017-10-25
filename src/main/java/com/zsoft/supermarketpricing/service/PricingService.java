package com.zsoft.supermarketpricing.service;

import com.zsoft.supermarketpricing.domain.UnitPrice;
import com.zsoft.supermarketpricing.domain.WeightPrice;
import com.zsoft.supermarketpricing.domain.WeightUnit;
import com.zsoft.supermarketpricing.exception.IllegalConversionException;

import static com.zsoft.supermarketpricing.service.UnitService.getConvertor;

public class PricingService {

    /**
     * Computes the weight price;
     *
     * @param price    The weight price
     * @param quantity the Quantity
     * @param unit     the unit of the quantity
     * @return the price in dollar currency;
     */
    public static double getRawPrice(WeightPrice price, Float quantity, WeightUnit unit) throws IllegalConversionException {
        return price.getValue() * getConvertor(unit, price.getUnit()).apply(quantity);
    }

    /**
     * Computes the unit price;
     *
     * @param price    The unit price
     * @param quantity the Quantity
     * @return the price in dollar currency;
     */
    public static double getRawPrice(UnitPrice price, Integer quantity) {
        return price.getValue() * quantity;
    }


}
