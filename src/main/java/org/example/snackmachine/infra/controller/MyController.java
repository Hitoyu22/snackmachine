package org.example.snackmachine.infra.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MyController {

    private GetSnackMachine getSnackMachine;

    public MyController(GetSnackMachine getter) {
        this.getSnackMachine = getter;
    }

    @GetMapping("/snacks")
    public List<RestSnack> snacks() {
        return getSnackMachine.get().snacks().stream().map(u -> new RestSnack(u.name().hashCode(), u.name().value(), u.description(), u.price().value(), u.imageURL(), u.isAvailable())).collect(Collectors.toList());
    }
}
