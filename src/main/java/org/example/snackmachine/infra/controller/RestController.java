package org.example.snackmachine.infra.controller;

import org.example.snackmachine.domain.service.SnackMachineService;
import org.example.snackmachine.domain.snack.Snack;
import org.example.snackmachine.domain.snack.SnackPack;
import org.example.snackmachine.infra.controller.dto.CartDTO;
import org.example.snackmachine.infra.controller.dto.SnackRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final SnackMachineService service;

    public RestController(SnackMachineService service) {
        this.service = service;
    }

    @GetMapping("/snacks")
    public List<Snack> snacks() {
        return service.getSnacks();
    }

    @GetMapping("/balance")
    public Integer balance() {
        return service.getBalance();
    }

    @PostMapping("/pay")
    public ResponseEntity<?> pay() {
        try {
            int newBalance = service.pay();
            return ResponseEntity.ok(newBalance);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/order")
    public List<SnackPack> order() {
        return service.getOrder();
    }

    @PostMapping("/order")
    public ResponseEntity<?> postOrder(@RequestBody SnackRequestDTO request) {
        try {
            CartDTO cart = service.addSnackToCart(request.snack());
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/order")
    public ResponseEntity<?> remove(@RequestBody SnackRequestDTO request) {
        try {
            CartDTO cart = service.removeSnackFromCart(request.snack());
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("\"" + service.getStatus() + "\"");
    }
}