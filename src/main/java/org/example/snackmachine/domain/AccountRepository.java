package org.example.snackmachine.domain;

import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    private  Account account;

    public AccountRepository() {
        this.account = new Account(Amount.euros(25));
    }

    public Account get() {
        return account;
    }

    public void save(Account account) {
        this.account = account;
    }
}
