package org.example.snackmachine.infra.controller;

import org.example.snackmachine.domain.Amount;
import org.example.snackmachine.domain.Snack;
import org.example.snackmachine.domain.SnackMachine;
import org.springframework.stereotype.Repository;

@Repository
public class SnackMachineRepository {

    private SnackMachine machine;

    public SnackMachineRepository() {
        this.machine = new SnackMachine();
        machine.add(new Snack(
                new Snack.Name("Chips"),
                "Crispy salted potato chips.",
                Amount.euros(1.2),
                20,
                "https://images.unsplash.com/photo-1641693148759-843d17ceac24?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ));

        machine.add(new Snack(new Snack.Name("Chocolate"),
                "Delicious dark chocolate bar.",
                Amount.euros(1.8),
                20,
                "https://images.unsplash.com/photo-1614088685112-0a760b71a3c8?q=80&w=3333&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        machine.add(new Snack(new Snack.Name("Cookies"),
                "Buttery chocolate chip cookies.",
                Amount.euros(1.5),
                20,
                "https://images.unsplash.com/photo-1499636136210-6f4ee915583e?q=80&w=2678&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        machine.add(new Snack(new Snack.Name("Soda"),
                "Refreshing sparkling soda.",
                Amount.euros(1.0),
                20,
                "https://images.unsplash.com/photo-1579684971280-0783c9cc00bc?q=80&w=1335&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        machine.add(new Snack(new Snack.Name("Gummies"),
                "Fruity gummy bears.",
                Amount.euros(1.1),
                20,
                "https://plus.unsplash.com/premium_photo-1669547518632-9e50db122033?q=80&w=3687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        machine.add(new Snack(new Snack.Name("Nuts"),
                "Roasted salty peanuts.",
                Amount.euros(1.6),
                20,
                "https://images.unsplash.com/photo-1605024344839-e6e41aea6b23?q=80&w=2274&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        machine.add(new Snack(new Snack.Name("Popcorn"),
                "Light and fluffy popcorn.",
                Amount.euros(1.3),
                20,
                "https://images.unsplash.com/photo-1512149177596-f817c7ef5d4c?q=80&w=1300&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        machine.add(new Snack(new Snack.Name("Granola"),
                "Healthy granola bar.",
                Amount.euros(1.4),
                20,
                "https://images.unsplash.com/photo-1504708706948-13d6cbba4062?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        machine.add(new Snack(new Snack.Name("Water"),
                "Pure spring water.",
                Amount.euros(0.9),
                20,
                "https://images.unsplash.com/photo-1595994195534-d5219f02f99f?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
    }

    public SnackMachine get() {
        return this.machine;
    }
}
