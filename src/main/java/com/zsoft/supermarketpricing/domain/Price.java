package com.zsoft.supermarketpricing.domain;

public abstract class Price {

    /**
     * The price value in dollars
     */
    private final Float value;

    public Price(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        return Double.compare(price.value, value) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "Price{" +
                "value=" + value +
                '}';
    }
}
