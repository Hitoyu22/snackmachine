package org.example.snackmachine.domain.snack;

public class Snack {
    Integer id;
    String name;
    Integer price;
    String description;
    String url;

    public Snack(Integer id,String name, Integer price, String description, String url) {
        this.id = id;
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
}
