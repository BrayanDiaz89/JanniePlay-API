package com.platzi.bdiaz.JanniePlay.web.controller;

import com.platzi.bdiaz.JanniePlay.domain.service.ai.JanniePlayAIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    private String namePlataform;
    private final JanniePlayAIService aiService;

    public HelloController(@Value("${spring.application.name}") String namePlataform, JanniePlayAIService aiService) {
        this.namePlataform = namePlataform;
        this.aiService = aiService;
    }

    @GetMapping("/ai")
    public String hello(){
        return this.aiService.generateGreeting(namePlataform);
    }

}
