package com.zsoft.supermarketpricing.service;

import com.zsoft.supermarketpricing.domain.UnitPrice;
import com.zsoft.supermarketpricing.domain.WeightPrice;

public class PricingService {

    /**
     * Computes the weight price;
     *
     * @param price    The weight price
     * @param quantity the Quantity
     * @param unit     the unit of the quantity
     * @return the price in dollar currency;
     */
    public static double getRawPrice(WeightPrice price, Float quantity, String unit) {
        return 0;
    }

    /**
     * Computes the unit price;
     *
     * @param price    The unit price
     * @param quantity the Quantity
     * @return the price in dollar currency;
     */
    public static double getRawPrice(UnitPrice price, Integer quantity) {
        return 0;
    }


}
