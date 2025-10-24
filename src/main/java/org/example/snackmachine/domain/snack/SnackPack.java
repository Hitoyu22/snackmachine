package org.example.snackmachine.domain.snack;

public class SnackPack {

    public final Integer id;
    public Integer quantity;

    public SnackPack(Integer id, Integer quantity) {
        this.id = id;
        if (quantity <= 0){
            throw new IllegalArgumentException("La quantité doit être supérieure à 1");
        } else if (quantity > 20) {
            throw new IllegalArgumentException("La quantité doit être inférieure à 20");
        } else {
            this.quantity = quantity;
        }
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void increaseQuantity(Integer amount) {
        if (amount <= 0){
            throw new IllegalArgumentException("La quantité ajoutée doit être supérieure à 0");
        }
        if (quantity + amount > 20){
            throw new IllegalArgumentException("La quantité ajoutée doit être inférieur à ce qu'il faut pour atteindre 20");
        }
        quantity = quantity + 1;
    }

    public void decreaseQuantity(Integer amount) {
        if (amount <= 0){
            throw new IllegalArgumentException("La quantité retirée doit être supérieure à 0");
        } else if (amount > this.quantity) {
            throw new IllegalArgumentException("Il n'est pas posssible de retiré plus que disponible dans le panier.");
        }
    }

}
