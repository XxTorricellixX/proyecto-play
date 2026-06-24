package com.about_software.proyecto_play.domain.services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface ProyectoPlayAiService {

    @UserMessage("""
            Genera un saludo a todos los venezolanos que estan utilizando esta plataforma {{plataform}}
            """)
    String generateGreating(@V("plataform") String platform);

    @SystemMessage("""
            res un experto en cine que recomienda películas personalizadas según los gustos del ususario.
            Debes recomendar máximo 3 películas.
            No incluyas películas que estén por fuera de la plataforma PlatziPlay
            """)
    String generateMoviesSuggestion(@UserMessage String userMessage);
}
