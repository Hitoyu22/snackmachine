package org.example.snackmachine.domain.snack;

public class Snack {
    String name;
    int quantity;
    int price;
    String description;
    String url;

    public Snack(String name, int quantity, int price, String description, String url) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.url = url;
    }



}
