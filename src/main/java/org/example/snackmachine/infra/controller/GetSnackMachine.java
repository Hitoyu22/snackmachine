package org.example.snackmachine.infra.controller;


import org.example.snackmachine.domain.SnackMachine;
import org.springframework.stereotype.Service;

@Service
public class GetSnackMachine {

    private SnackMachineRepository repository;
    public GetSnackMachine(SnackMachineRepository repository) {
        this.repository = repository;
    }

    public SnackMachine get() {
        return repository.get();
    }
}
