package org.example.snackmachine.domain;


import org.example.snackmachine.infra.controller.SnackMachineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetSnackMachine {

    private final SnackMachineRepository repository;
    public GetSnackMachine(SnackMachineRepository repository) {
        this.repository = repository;
    }

    public SnackMachine get() {
        return repository.get();
    }

    public Amount orderAmount() {
        var order = repository.get().currentOrder();
        var snacks = this.repository.get().snacks();

        return order.values().entrySet().stream().map(u ->
                {
                    var snack = snacks.stream().filter(i -> i.name().equals(u.getKey())).findFirst().orElseThrow();
                    return snack.price().times(u.getValue());
                }
        ).reduce(new Amount(0.0), Amount::plus);
    }

    public Order order() {
        return repository.get().currentOrder();
    }

    public List<Snack> snacks() {
        return repository.get().snacks();
    }
}
