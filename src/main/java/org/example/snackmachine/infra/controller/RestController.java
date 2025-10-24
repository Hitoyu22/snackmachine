package org.example.snackmachine.infra.controller;

import org.example.snackmachine.domain.snack.Machine;
import org.example.snackmachine.domain.snack.Snack;
import org.example.snackmachine.domain.snack.SnackPack;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final Machine machine;

    public RestController() {
        List<Snack> snacks = new ArrayList<>();
        snacks.add(new Snack(1, 15, "Chips", 120, "Crispy salted potato chips.", "https://images.unsplash.com/photo-1641693148759-843d17ceac24?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        snacks.add(new Snack(2, 10, "Chocolate", 180, "Delicious dark chocolate bar.", "https://images.unsplash.com/photo-1614088685112-0a760b71a3c8?q=80&w=3333&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        snacks.add(new Snack(3, 20, "Cookies", 150, "Buttery chocolate chip cookies.", "https://images.unsplash.com/photo-1499636136210-6f4ee915583e?q=80&w=2678&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        snacks.add(new Snack(4, 5, "Soda", 100, "Refreshing sparkling soda.", "https://images.unsplash.com/photo-1579684971280-0783c9cc00bc?q=80&w=1335&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));

        this.machine = new Machine(snacks, new ArrayList<>(), 2500);
    }

    @GetMapping("/snacks")
    public List<Snack> snacks() {
        return machine.getAllSnacks();
    }

    @GetMapping("/balance")
    public Integer balance() {
        return machine.getBalance();
    }

    @PostMapping("/pay")
    public ResponseEntity<?> pay() {
        try {
            machine.payOrder();
            return ResponseEntity.ok(machine.getBalance());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/order")
    public List<SnackPack> order() {
        return machine.getCurrentOrder();
    }

    @PostMapping("/order")
    public ResponseEntity<?> postOrder(@RequestParam Integer snackId, @RequestParam Integer amount) {
        try {
            machine.addToOrder(snackId, amount);
            return ResponseEntity.ok(machine.getCurrentOrder());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/order")
    public ResponseEntity<?> remove(@RequestParam Integer snackId, @RequestParam Integer amount) {
        try {
            machine.removeFromOrder(snackId, amount);
            return ResponseEntity.ok(machine.getCurrentOrder());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/status")
    public String status() {
        return machine.getStatus().name();
    }
}