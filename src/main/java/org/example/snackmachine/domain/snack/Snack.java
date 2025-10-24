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

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Integer getPrice(){
        return price;
    }
    public void setPrice(Integer price){
        this.price = price;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(){
        this.description = description;
    }

    public Integer getQuantity(){
        return quantity;
    }
    void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public boolean isAvailable(int needed){
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
