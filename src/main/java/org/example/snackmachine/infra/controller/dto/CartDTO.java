package org.example.snackmachine.infra.controller.dto;

import java.util.Map;

public record CartDTO(Map<String, Integer> order, int amount) {
}