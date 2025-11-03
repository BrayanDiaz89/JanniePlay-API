package com.platzi.bdiaz.JanniePlay.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record SuggestRequestDTO(
        @NotBlank(message = "Debes ingresar tú gusto de películas personales, para recibir una recomendación.")
        String userPreferences
) {
}
