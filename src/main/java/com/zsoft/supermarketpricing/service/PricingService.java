package com.zsoft.supermarketpricing.service;


import com.zsoft.supermarketpricing.domain.Discount;
import com.zsoft.supermarketpricing.domain.UnitPrice;
import com.zsoft.supermarketpricing.domain.WeightPrice;
import com.zsoft.supermarketpricing.domain.WeightUnit;
import com.zsoft.supermarketpricing.exception.IllegalConversionException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.zsoft.supermarketpricing.service.PowerSetService.powerSet;
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
    public static float getRawPrice(WeightPrice price, Float quantity, WeightUnit unit) throws IllegalConversionException {
        return price.getValue() * getConvertor(unit, price.getUnit()).apply(quantity);
    }

    /**
     * Computes the unit price;
     *
     * @param price    The unit price
     * @param quantity the Quantity
     * @return the price in dollar currency;
     */
    public static float getRawPrice(UnitPrice price, Integer quantity) {
        return price.getValue() * quantity;
    }


    /**
     * Computes the weight price with applying the given discount;
     *
     * @param price    the unit price
     * @param quantity the Quantity
     * @param unit     the unit of the  the Quantity
     * @param discount the discount to be applied
     * @return the price in dollar currency;
     */

    public static float getDiscountPrice(WeightPrice price, Float quantity, WeightUnit unit, Discount discount) throws IllegalConversionException {
        Float realQuantity = getConvertor(unit, price.getUnit()).apply(quantity);
        if (realQuantity < discount.getQuantity()) {
            return getRawPrice(price, quantity, unit);
        }

        int div = (int) (realQuantity / discount.getQuantity());
        int rest = (int) (realQuantity % discount.getQuantity());
        return div * discount.getPrice() + rest * price.getValue();
    }

    /**
     * Computes the unit price with applying the given discount;
     *
     * @param price    the unit price
     * @param quantity the Quantity
     * @param discount the discount to be applied
     * @return the price in dollar currency;
     */

    public static float getDiscountPrice(UnitPrice price, Integer quantity, Discount discount) {
        if (quantity < discount.getQuantity()) {
            return getRawPrice(price, quantity);
        }
        int div = (int) (quantity / discount.getQuantity());
        int rest = (int) (quantity % discount.getQuantity());
        return div * discount.getPrice() + rest * price.getValue();
    }


    /**
     * Computes the price using the given collection of discounts;
     *
     * @param price    The unit price
     * @param quantity the Quantity
     * @return the minimum price in dollar currency;
     */
    public static float getDiscountsPrice(UnitPrice price, Integer quantity, Collection<Discount> discounts) {

        Float sum = 0f;
        for (Discount discount : discounts) {
            sum = sum + getDiscountPrice(price, quantity, discount);
        }

        return sum;
    }


    /**
     * Computes the price using the given collection of discounts;
     *
     * @param price    The unit price
     * @param quantity the Quantity
     * @param unit     the  unit of the quantity
     * @return the minimum price in dollar currency;
     */

    public static float getDiscountsPrice(WeightPrice price, Float quantity, WeightUnit unit, Collection<Discount> discounts) throws IllegalConversionException {

        Float sum = 0f;
        for (Discount discount : discounts) {
            sum = sum + getDiscountPrice(price, quantity, unit, discount);
        }
        return sum;
    }


    /**
     * Computes the minimum global price by exploring all discounts combinations;
     *
     * @param price    The unit price
     * @param quantity the Quantity
     * @return the minimum price in dollar currency;
     */
    public static Float getTheMinPrice(UnitPrice price, Integer quantity, Collection<Discount> discounts) {

        Set<Discount> discountsSet = new HashSet<Discount>(discounts);
        Set<Set<Discount>> subDiscountSets = powerSet(discountsSet);

        Float minPrice = 10000000000f;

        for (Set<Discount> discountSet : subDiscountSets) {
            Float discountsPrice = getDiscountsPrice(price, quantity, discountSet);
            minPrice = (discountsPrice < minPrice) ? discountsPrice : minPrice;
        }
        return minPrice;

    }


    /**
     * Computes the minimum global price by exploring all discounts combinations;
     *
     * @param price    The unit price
     * @param quantity the Quantity
     * @param unit     the unit of the quantity
     * @return the minimum price in dollar currency;
     */

    public static Float getTheMinPrice(WeightPrice price, Float quantity, WeightUnit unit, Collection<Discount> discounts) throws IllegalConversionException {
        Set<Discount> discountsSet = new HashSet<Discount>(discounts);
        Set<Set<Discount>> subDiscountSets = powerSet(discountsSet);

        Float minPrice = 10000000000f;

        for (Set<Discount> discountSet : subDiscountSets) {
            Float discountsPrice = getDiscountsPrice(price, quantity, unit, discountSet);
            minPrice = (discountsPrice < minPrice) ? discountsPrice : minPrice;
        }
        return minPrice;
    }


}
