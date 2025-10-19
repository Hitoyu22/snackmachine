package org.example.snackmachine.domain;

import java.util.HashMap;

public record Order(HashMap<Snack.Name, Integer> values) {
    void add(Snack.Name snack) {
        values.compute(snack, (k, v) -> v == null ? 1 : v + 1);
    }

    void remove(Snack.Name snack) {
        values.compute(snack, (k, v) -> v == null ? 0 : v - 1);
    }
}
