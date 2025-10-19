package org.example.snackmachine.domain;


public record Amount(Double value) implements Comparable<Amount> {
    @Override
    public int compareTo(Amount amount) {
        return this.value.compareTo(amount.value);
    }

    public Amount minus(Amount other) {
        return new Amount(this.value - other.value);
    }
    public static Amount euros(double value) {
        return new Amount(value);
    }

    public Amount times(Integer value) {
        return new Amount(this.value * value);
    }

    public Amount plus(Amount v) {
        return new Amount(this.value + v.value);
    }
}
