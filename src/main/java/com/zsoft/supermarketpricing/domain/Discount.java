package com.zsoft.supermarketpricing.domain;

public class Discount {

    private final Float price;

    private final Float quantity;

    public Discount(Float price, Float quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public Float getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (price != null ? !price.equals(discount.price) : discount.price != null) return false;
        return quantity != null ? quantity.equals(discount.quantity) : discount.quantity == null;
    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "price=" + price +
                ", quantity=" + quantity +
                '}';
    }

}
