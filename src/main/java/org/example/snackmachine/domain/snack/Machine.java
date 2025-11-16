package org.example.snackmachine.domain.snack;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Machine {

    List<Snack> snacks;
    List<SnackPack> order;
    Integer balance;
    List<CompletedOrder> orderHistory;
    private Status status;

    public Machine(List<Snack> snacks, List<SnackPack> order, Integer balance) {
        this.snacks = snacks;
        this.order = order;
        this.balance = balance;
        this.orderHistory = new ArrayList<>();
        this.status = Status.Nothing;
    }

    public List<Snack> getAllSnacks(){
        return this.snacks;
    }

    public Snack findSnackById(Integer id){
        for (Snack snack : snacks) {
            if (Objects.equals(snack.id, id))
                return snack;
        }
        throw new IllegalArgumentException("Le produit n'existe pas");
    }

    public Snack findSnackByName(String name) {
        for (Snack snack : snacks) {
            if (Objects.equals(snack.getName(), name))
                return snack;
        }
        throw new IllegalArgumentException("Le produit n'existe pas");
    }

    private Optional<SnackPack> findSnackPackInOrder(Integer snackId) {
        return order.stream().filter(sp -> sp.getId().equals(snackId)).findFirst();
    }

    public List<SnackPack> getCurrentOrder(){
        return this.order;
    }

    public Integer getBalance() {
        return balance;
    }

    public List<CompletedOrder> getOrderHistory() {
        return orderHistory;
    }

    public Status getStatus() {
        Status currentStatus = this.status;
        if (currentStatus == Status.Done || currentStatus == Status.Failed) {
            this.status = Status.Nothing;
        }
        return currentStatus;
    }

    public int calculateOrderTotal() {
        int total = 0;
        for (SnackPack item : order) {
            Snack snack = findSnackById(item.getId());
            total += snack.getPrice() * item.getQuantity();
        }
        return total;
    }

    private int calculateTotalItems() {
        int totalItems = 0;
        for (SnackPack item : order) {
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    public void addToOrder(Integer snackId, Integer amount) {
        Snack snack = findSnackById(snackId);
        if (snack.isAvailable(amount)) {
            throw new IllegalStateException("Snack non disponible en quantité suffisante.");
        }

        snack.decreaseQuantity(amount);

        Optional<SnackPack> existingPack = findSnackPackInOrder(snackId);

        try {
            if (existingPack.isPresent()) {
                existingPack.get().increaseQuantity(amount);
            } else {
                order.add(new SnackPack(snackId, amount));
            }
        } catch (IllegalArgumentException e) {
            snack.increaseQuantity(amount);
            throw e;
        }
    }

    public void removeFromOrder(Integer snackId, Integer amount) {
        Optional<SnackPack> existingPack = findSnackPackInOrder(snackId);

        if (existingPack.isEmpty()) {
            return;
        }

        SnackPack pack = existingPack.get();
        Snack snack = findSnackById(snackId);

        int actualAmountToRemove = Math.min(amount, pack.getQuantity());

        pack.decreaseQuantity(actualAmountToRemove);

        snack.increaseQuantity(actualAmountToRemove);

        if (pack.getQuantity() <= 0) {
            order.remove(pack);
        }
    }

    public void payOrder() {
        this.status = Status.Pending;
        int totalCost = calculateOrderTotal();

        if (totalCost > this.balance) {
            this.status = Status.Failed;
            throw new IllegalStateException("Solde insuffisant");
        }

        if (totalCost == 0) {
            this.status = Status.Nothing;
            return;
        }

        try {
            this.balance -= totalCost;
            int totalItems = calculateTotalItems();
            long delay = 3000L + (totalItems * 1000L);
            Thread.sleep(delay);

            this.orderHistory.add(new CompletedOrder(this.order, totalCost));
            this.order = new ArrayList<>();
            this.status = Status.Done;
        } catch (InterruptedException e) {
            this.status = Status.Failed;
            Thread.currentThread().interrupt();
        }
    }

    public void addOrder(Integer id){
        Snack snack = findSnackById(id);

        if (snack.isAvailable(1)) {
            throw new IllegalArgumentException("Le produit n'est pas disponible");
        }

        Optional<SnackPack> existingPack = findSnackPackInOrder(id);
        try {
            if (existingPack.isPresent()) {
                existingPack.get().increaseQuantity(1);
            } else {
                order.add(new SnackPack(id, 1));
            }
            snack.decreaseQuantity(1);
        } catch (IllegalArgumentException e) {
            snack.increaseQuantity(1);
            throw e;
        }
    }

    public void delOrder(Integer id){
        Optional<SnackPack> existingPack = findSnackPackInOrder(id);

        if (existingPack.isPresent()) {
            SnackPack pack = existingPack.get();
            Snack snack = findSnackById(id);

            pack.decreaseQuantity(1);
            snack.increaseQuantity(1);

            if (pack.getQuantity() <= 0) {
                order.remove(pack);
            }
        }
    }
}