package com.platzi.bdiaz.JanniePlay.domain.service.logic.rules;

import com.platzi.bdiaz.JanniePlay.domain.dto.MovieRequestDTO;

public interface ValidationCrudMovie {
    void validate(MovieRequestDTO movieRequestDTO);
}
