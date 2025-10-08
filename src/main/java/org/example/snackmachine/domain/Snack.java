package org.example.snackmachine.domain;

public final class Snack {
    private final Name name;
    private final String description;
    private final String imageURL;
    private int quantity;
    private Amount price;

    public record Name(String value) {}

    public Snack(Name name, String description, Amount price, int quantity, String imageURL) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.price = price;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void refillBy(int quantity) {
        this.quantity += quantity;
    }

    public Name name() {
        return name;
    }

    public String description() {
        return description;
    }

    public int quantity() {
        return quantity;
    }

    public String imageURL() {
        return imageURL;
    }

    public Amount price() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Snack snack = (Snack) o;
        return name.equals(snack.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Snack{" +
                "name=" + name +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
