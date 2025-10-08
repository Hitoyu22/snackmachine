package org.example.snackmachine.infra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FrontendController {
    @GetMapping("/game")
    public String front() {
        return "index";
    }
}