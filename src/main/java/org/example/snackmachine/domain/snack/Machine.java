package org.example.snackmachine.domain.snack;

import java.util.List;

public class Machine {

    List<SnackPack> snacks;
    List<SnackPack> order;
    Integer balance;

    public Machine(List<SnackPack> snacks, List<SnackPack> order, Integer balance) {
        this.snacks = snacks;
        this.order = order;
        this.balance = balance;
    }

}
