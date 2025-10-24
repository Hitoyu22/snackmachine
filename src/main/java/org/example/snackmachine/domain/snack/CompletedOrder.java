package org.example.snackmachine.domain.snack;

import java.util.List;

public class CompletedOrder {

    private final List<SnackPack> items;
    private final Integer totalCost;

    public CompletedOrder(List<SnackPack> items, Integer totalCost) {
        this.items = List.copyOf(items);
        this.totalCost = totalCost;
    }

    public List<SnackPack> getItems() {
        return items;
    }

    public Integer getTotalCost() {
        return totalCost;
    }
}