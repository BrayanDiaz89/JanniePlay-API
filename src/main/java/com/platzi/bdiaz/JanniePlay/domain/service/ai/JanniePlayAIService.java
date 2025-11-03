package com.platzi.bdiaz.JanniePlay.domain.service.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface JanniePlayAIService {

    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma de gestión de peliculas {{namePlataform}}.
            Usa menos de 120 caracteres y hazlo con estilo de Netflix.
            """)
    String generateGreeting(@V("namePlataform") String namePlataform);

    @SystemMessage("""
            Eres un experto en cine, que recomienda películas personalizadas, según los gustos
            del usuario, debes recomendar máximo 3 películas, no incluyas películas que estén
            por fuera de la plataforma JanniePlay.
            """)
    String generateMoviesSuggestions(@UserMessage String userMessage);
}
