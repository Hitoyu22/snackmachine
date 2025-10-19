package org.example.snackmachine.domain;

public class Account {
    private Amount balance;

    public Account(Amount balance) {
        this.balance = balance;
    }

    public Amount balance() {
        return balance;
    }

    public void retrieve(Amount amount) {
        if (balance.compareTo(amount) < 0)
            throw new IllegalArgumentException("Insufficient funds");
        this.balance = balance.minus(amount);
    }
}
