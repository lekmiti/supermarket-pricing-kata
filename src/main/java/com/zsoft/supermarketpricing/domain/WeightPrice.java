package com.zsoft.supermarketpricing.domain;

public class WeightPrice extends Price {
    private final String unit;

    public WeightPrice(double value, String unit) {
        super(value);
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WeightPrice that = (WeightPrice) o;

        return unit != null ? unit.equals(that.unit) : that.unit == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeightPrice{" +
                "unit='" + unit + '\'' +
                '}';
    }
}
