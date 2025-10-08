package org.example.snackmachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Snack Unit Test")
public class SnackTest {
    @DisplayName("A snack is available when its quantity is > 0")
    @Test
    void test0() {
        Snack snack = new Snack(
                new Snack.Name("Cookie"),
                "Buttery chocolate chip cookies",
                12
        );

        assertThat(snack.isAvailable()).isTrue();
    }

    @DisplayName("A snack is not available when its quantity is equal to 0")
    @Test
    void test1() {
        Snack snack = new Snack(
                new Snack.Name("Cookie"),
                "Buttery chocolate chip cookies",
                0
        );

        assertThat(snack.isAvailable()).isFalse();
    }

    @DisplayName("Refill the stock of a snack")
    @Test
    void test3() {
        Snack snack = new Snack(
                new Snack.Name("Cookie"),
                "Buttery chocolate chip cookies",
                3
        );

        snack.refillBy(12);

        assertThat(snack.quantity()).isEqualTo(15);
    }
}
