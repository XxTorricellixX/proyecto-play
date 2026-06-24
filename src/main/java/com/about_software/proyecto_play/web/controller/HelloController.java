package com.about_software.proyecto_play.web.controller;

import com.about_software.proyecto_play.domain.services.ProyectoPlayAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final String platform;
    private final ProyectoPlayAiService aiService;


    public HelloController(@Value("${spring.application.name}") String platform, ProyectoPlayAiService aiService) {
        this.platform = platform;
        this.aiService = aiService;
    }

    @GetMapping("/hello")
    public String hello(){
        return this.aiService.generateGreating(platform);
    }
}
