package org.example.snackmachine.domain.service;

import org.example.snackmachine.domain.snack.Machine;
import org.example.snackmachine.domain.snack.Snack;
import org.example.snackmachine.domain.snack.SnackPack;
import org.example.snackmachine.domain.snack.Status;
import org.example.snackmachine.infra.controller.dto.CartDTO;
import org.example.snackmachine.infra.repository.MachineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SnackMachineService {

    private final MachineRepository repository;

    public SnackMachineService(MachineRepository repository) {
        this.repository = repository;
    }

    public List<Snack> getSnacks() {
        return repository.getMachine().getAllSnacks();
    }

    public Integer getBalance() {
        return repository.getMachine().getBalance();
    }

    public List<SnackPack> getOrder() {
        return repository.getMachine().getCurrentOrder();
    }

    public String getStatus() {
        return repository.getMachine().getStatus().name();
    }

    public CartDTO addSnackToCart(String snackName) {
        Machine machine = repository.getMachine();
        Snack snack = machine.findSnackByName(snackName);
        machine.addOrder(snack.getId());
        return buildCartDTO(machine);
    }

    public CartDTO removeSnackFromCart(String snackName) {
        Machine machine = repository.getMachine();
        Snack snack = machine.findSnackByName(snackName);
        machine.delOrder(snack.getId());
        return buildCartDTO(machine);
    }

    public int pay() {
        repository.getMachine().payOrder();
        return repository.getMachine().getBalance();
    }

    private CartDTO buildCartDTO(Machine machine) {
        Map<String, Integer> orderMap = machine.getCurrentOrder().stream()
                .collect(Collectors.toMap(
                        pack -> machine.findSnackById(pack.getId()).getName(),
                        SnackPack::getQuantity
                ));

        int totalAmount = machine.calculateOrderTotal();
        return new CartDTO(orderMap, totalAmount);
    }
}