package org.example.snackmachine.infra.controller;

import java.util.Map;

public record RestCart(Map<String, Integer> order, Double amount) {}
