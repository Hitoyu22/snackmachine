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

    String findProduct(Integer id){
        for (Snack snack : snacks) {
            if (Objects.equals(snack.id, id))
                return snack.name;
        }
        throw new IllegalArgumentException("Le produit n'existe pas");
    }

    void getCurrentOrder(){
        for (SnackPack snackpack : order){
            System.out.println(findProduct(snackpack.id));
        }
    }
}
