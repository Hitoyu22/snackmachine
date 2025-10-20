package org.example.snackmachine.infra.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    public RestController() {
    }

    @GetMapping("/snacks")
    public String snacks() {
        return "[{\"id\":65078663,\"name\":\"Chips\",\"description\":\"Crispy salted potato chips.\",\"price\":1.2,\"imageURL\":\"https://images.unsplash.com/photo-1641693148759-843d17ceac24?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\",\"available\":true},{\"id\":-1602911716,\"name\":\"Chocolate\",\"description\":\"Delicious dark chocolate bar.\",\"price\":1.8,\"imageURL\":\"https://images.unsplash.com/photo-1614088685112-0a760b71a3c8?q=80&w=3333&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\",\"available\":true},{\"id\":-1678124433,\"name\":\"Cookies\",\"description\":\"Buttery chocolate chip cookies.\",\"price\":1.5,\"imageURL\":\"https://images.unsplash.com/photo-1499636136210-6f4ee915583e?q=80&w=2678&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\",\"available\":true},{\"id\":2582521,\"name\":\"Soda\",\"description\":\"Refreshing sparkling soda.\",\"price\":1.0,\"imageURL\":\"https://images.unsplash.com/photo-1579684971280-0783c9cc00bc?q=80&w=1335&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\",\"available\":true},{\"id\":2041877737,\"name\":\"Gummies\",\"description\":\"Fruity gummy bears.\",\"price\":1.1,\"imageURL\":\"https://plus.unsplash.com/premium_photo-1669547518632-9e50db122033?q=80&w=3687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\",\"available\":true},{\"id\":2439846,\"name\":\"Nuts\",\"description\":\"Roasted salty peanuts.\",\"price\":1.6,\"imageURL\":\"https://images.unsplash.com/photo-1605024344839-e6e41aea6b23?q=80&w=2274&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\",\"available\":true},{\"id\":1270180185,\"name\":\"Popcorn\",\"description\":\"Light and fluffy popcorn.\",\"price\":1.3,\"imageURL\":\"https://images.unsplash.com/photo-1512149177596-f817c7ef5d4c?q=80&w=1300&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\",\"available\":true},{\"id\":1944943788,\"name\":\"Granola\",\"description\":\"Healthy granola bar.\",\"price\":1.4,\"imageURL\":\"https://images.unsplash.com/photo-1504708706948-13d6cbba4062?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\",\"available\":true},{\"id\":83350775,\"name\":\"Water\",\"description\":\"Pure spring water.\",\"price\":0.9,\"imageURL\":\"https://images.unsplash.com/photo-1595994195534-d5219f02f99f?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\",\"available\":true}]";
    }

    @GetMapping("/balance")
    public Double balance() {
        return 25.0;
    }

    @PostMapping("/pay")
    public double pay() {
        return 15.0;
    }

    @GetMapping("/order")
    public String order() {
        return "{\"order\":{\"Soda\":1, \"Chips\":3},\"amount\":2.0}";
    }

    @PostMapping("/order")
    public String postOrder() {
        return "{\"order\":{\"Soda\":1, \"Chips\":3},\"amount\":2.0}";
    }

    @DeleteMapping("/order")
    public String remove() {
        return "{\"order\":{\"Soda\":1, \"Chips\":3},\"amount\":2.0}";
    }

    @GetMapping("/status")
    public String orderAmount() {
        return "Nothing";
    }
}