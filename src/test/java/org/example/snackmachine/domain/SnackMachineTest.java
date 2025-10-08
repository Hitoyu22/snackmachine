package org.example.snackmachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.Map.entry;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("Snack Machine Unit Test")
public class SnackMachineTest {

    private Snack cookies = new Snack(new Snack.Name("Cookie"), "Buttery chocolate chip cookies", 12);
    private Snack popcorn = new Snack(new Snack.Name("Popcorn"), "Light and fluffy popcorn.", 12);

    @DisplayName("Snack machine should list all snacks")
    @Test
    public void test0() {
        var machine = new SnackMachine();
        machine.add(cookies);
        machine.add(popcorn);

        assertThat(machine.snacks()).containsExactlyInAnyOrder(
                cookies, popcorn
        );
    }

    @DisplayName("Should order a snack")
    @Test
    public void test1() {
        var machine = new SnackMachine();
        machine.add(cookies);
        machine.add(popcorn);

        machine.order(cookies.name());
        machine.order(cookies.name());

        var order = machine.currentOrder();

        assertThat(order.values()).containsAnyOf(entry(cookies.name(), 2));
    }

    @DisplayName("Order should reserve the desired quantity of a snack")
    @Test
    public void test2() {
        var machine = new SnackMachine();
        machine.add(cookies);
        machine.add(popcorn);

        machine.order(cookies.name());
        machine.order(cookies.name());
        machine.order(popcorn.name());

        assertThat(machine.snacks()).containsExactlyInAnyOrder(
                new Snack(
                        cookies.name(), cookies.description(), cookies.quantity() - 2
                ),
                new Snack(
                        popcorn.name(), popcorn.description(), popcorn.quantity() - 1
                )
        );

    }
}
