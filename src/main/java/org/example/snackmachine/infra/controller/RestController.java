package org.example.snackmachine.infra.controller;

import org.example.snackmachine.domain.GetAccount;
import org.example.snackmachine.domain.GetSnackMachine;
import org.example.snackmachine.domain.Snack;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final GetSnackMachine getSnackMachine;
    private final GetAccount getAccount;

    private final OrderProcessor processor;

    public RestController(GetSnackMachine getter, GetAccount getAccount, OrderProcessor processor) {
        this.getSnackMachine = getter;
        this.getAccount = getAccount;
        this.processor = processor;
    }

    @GetMapping("/snacks")
    public List<RestSnack> snacks() {
        return getSnackMachine.snacks().stream().map(u -> new RestSnack(u.name().hashCode(), u.name().value(), u.description(), u.price().value(), u.imageURL(), u.isAvailable())).collect(Collectors.toList());
    }

    @GetMapping("/balance")
    public Double balance() {
        return this.getAccount.balance().value();
    }

    @PostMapping("/pay")
    public double pay() {
        try {
            this.getAccount.pay();
            this.processor.start();
            return this.getAccount.balance().value();
        } catch (Exception e) {
            processor.fails();
            return this.getAccount.balance().value();
        }
    }

    @GetMapping("/order")
    public Map<String, Integer> order() {
        return this.getSnackMachine.order().values().entrySet().stream().collect(
                Collectors.toMap(
                        k -> k.getKey().value(),
                        Map.Entry::getValue
                )
        );
    }

    @PostMapping("/order")
    public RestCart order(@RequestBody RestOrder order) {
        this.getSnackMachine.get().order(new Snack.Name(order.snack()));

        return new RestCart(this.getSnackMachine.order().values().entrySet().stream().collect(
                Collectors.toMap(
                        k -> k.getKey().value(),
                        Map.Entry::getValue
                )
        ), getSnackMachine.orderAmount().value());
    }

    @DeleteMapping("/order")
    public RestCart remove(@RequestBody RestOrder order) {
        this.getSnackMachine.get().remove(new Snack.Name(order.snack()));

        return new RestCart(this.getSnackMachine.order().values().entrySet().stream().collect(
                Collectors.toMap(
                        k -> k.getKey().value(),
                        Map.Entry::getValue
                )
        ), getSnackMachine.orderAmount().value());
    }

    @GetMapping("/status")
    public Status orderAmount() {
        return this.processor.status();
    }
}