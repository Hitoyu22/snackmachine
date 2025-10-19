package org.example.snackmachine.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class SnackMachine {

    private Order currentOrder;

    public SnackMachine() {
        currentOrder = new Order(new HashMap<>());
    }
    private final List<Snack> snacks = new ArrayList<>();

    public void add(Snack snack) {
        snacks.add(snack);
    }

    public List<Snack> snacks() {
        return snacks.stream().map(u -> {
          var ordered = currentOrder.values().getOrDefault(u.name(), 0);
          if (ordered > 0) {
              return new Snack(u.name(), u.description(), u.price(), u.quantity() - ordered, u.imageURL());
          }
          return u;
        }).collect(Collectors.toList());
    }

    public void order(Snack.Name snack) {
        currentOrder.add(snack);
    }

    public Order currentOrder() {
        return currentOrder;
    }

    public void remove(Snack.Name snack) {
        currentOrder.remove(snack);
    }
}
