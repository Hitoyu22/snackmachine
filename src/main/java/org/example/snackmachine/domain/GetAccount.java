package org.example.snackmachine.domain;

import org.example.snackmachine.infra.controller.SnackMachineRepository;
import org.springframework.stereotype.Service;

@Service
public class GetAccount {
    private AccountRepository repository;
    private GetSnackMachine getSnackMachine;

    public GetAccount(AccountRepository repository, GetSnackMachine getSnackMachine) {
        this.repository = repository;
        this.getSnackMachine = getSnackMachine;
    }

    public void pay() {
        var amount = this.getSnackMachine.orderAmount();

        var account = repository.get();
        account.retrieve(amount);
        repository.save(account);
    }

    public Amount balance() {
        return repository.get().balance();
    }
}



