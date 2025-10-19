package org.example.snackmachine.infra.controller;

public record RestSnack(int id, String name, String description, double price, String imageURL, boolean available) {

}

