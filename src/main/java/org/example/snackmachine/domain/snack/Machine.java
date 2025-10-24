package org.example.snackmachine.domain.snack;
import java.util.List;
import java.util.Objects;

public class Machine {

    List<Snack> snacks;
    List<SnackPack> order;
    Integer balance;

    public Machine(List<Snack> snacks, List<SnackPack> order, Integer balance) {
        this.snacks = snacks;
        this.order = order;
        this.balance = balance;
    }

    void getAllProduct(){
        for (Snack snack : snacks) {
            System.out.println(snack.name);
        }
    }

    Snack findProduct(Integer id){
        for (Snack snack : snacks) {
            if (Objects.equals(snack.id, id))
                return snack;
        }
        throw new IllegalArgumentException("Le produit n'existe pas");
    }

    void getCurrentOrder(){
        for (SnackPack snackpack : order){
            System.out.println(findProduct(snackpack.id).name);
        }
    }

    void addOrder(Integer id){
        Snack snack = findProduct(id);

        if (snack.isAvailable(1)) {
            for (SnackPack snackpack : order){
                if (Objects.equals(snackpack.id, id)) {
                    snackpack.increaseQuantity(1);
                    snack.decreaseQuantity(1);
                }
            }
        } else {
            throw new IllegalArgumentException("Le produit n'est pas disponible");
        }
    }

    void delOrder(Integer id){
        Snack snack = findProduct(id);

        for (SnackPack snackpack : order){
            if (Objects.equals(snackpack.id, id)) {
                snackpack.decreaseQuantity(1);
                snack.increaseQuantity(1);
            }
        }
    }

}
