package org.example.snackmachine.domain.snack;

public class Snack {
    Integer id;
    Integer quantity;
    String name;
    Integer price;
    String description;
    String url;
    private static final int MAX_STOCK = 20;

    public Snack(Integer id, Integer quantity, String name, Integer price, String description, String url) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.description = description;
        this.url = url;
    }

    Integer getId(){
        return id;
    }
    void setId(Integer id){
        this.id = id;
    }

    String getName(){
        return name;
    }
    void setName(String name){
        this.name = name;
    }

    Integer getPrice(){
        return price;
    }
    void setPrice(Integer price){
        this.price = price;
    }

    String getDescription(){
        return description;
    }
    void setDescription(){
        this.description = description;
    }

    Integer getQuantity(){
        return quantity;
    }
    void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    boolean isAvailable(int needed){
        return quantity - needed >= 0;
    }

    void decreaseQuantity(Integer amount) {
        if (amount <= 0){
            throw new IllegalArgumentException("La quantité doit être positive.");
        }
        if (!isAvailable(amount)){
            throw new IllegalStateException("Stock insuffisant");
        }
        this.quantity = this.quantity - amount;
    }

    void increaseQuantity(Integer amount) {
        if (amount <= 0){
            throw new IllegalArgumentException("La quantité doit être positive.");
        }
        this.quantity = Math.min(this.quantity + amount, MAX_STOCK);
    }
}
