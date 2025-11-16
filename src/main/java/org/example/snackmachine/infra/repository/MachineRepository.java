package org.example.snackmachine.infra.repository;

import org.example.snackmachine.domain.snack.Machine;
import org.example.snackmachine.domain.snack.Snack;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MachineRepository {

    private final Machine machine;

    public MachineRepository() {
        List<Snack> snacks = new ArrayList<>();
        snacks.add(new Snack(1, 15, "Chips", 120, "Délicieuses chips", "https://images.unsplash.com/photo-1641693148759-843d17ceac24?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        snacks.add(new Snack(2, 10, "Chocolats", 180, "Délicieuses barres de chocolat", "https://images.unsplash.com/photo-1614088685112-0a760b71a3c8?q=80&w=3333&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        snacks.add(new Snack(3, 20, "Cookies", 150, "Délicieux cookies", "https://images.unsplash.com/photo-1499636136210-6f4ee915583e?q=80&w=2678&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        snacks.add(new Snack(4, 5, "Boissons", 100, "Boissons rafraichissantes", "https://images.unsplash.com/photo-1579684971280-0783c9cc00bc?q=80&w=1335&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));

        this.machine = new Machine(snacks, new ArrayList<>(), 2500);
    }

    public Machine getMachine() {
        return machine;
    }

    public Snack findSnackByName(String name) {
        return machine.getAllSnacks().stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Snack non trouvé: " + name));
    }
}