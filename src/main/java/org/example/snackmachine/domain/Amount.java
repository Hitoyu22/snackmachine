package org.example.snackmachine.domain;


public record Amount(Double value, Currency currency) {

    enum Currency {
        EURO
    }

    public static Amount euros(double value) {
        return new Amount(value, Currency.EURO);
    }
}
