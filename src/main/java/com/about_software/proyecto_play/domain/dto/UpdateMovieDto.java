package com.about_software.proyecto_play.domain.dto;

import com.about_software.proyecto_play.domain.Genre;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateMovieDto(
        @NotBlank(message ="El título es obligatio")
        String title,
        @PastOrPresent(message="La fecha de lanzamiento debe ser anterior a la fecha actual")
        LocalDate releaseDate,
        @Min(value=0,message="El rating no puede ser menor que 0")
        @Max(value=5,message="El rating no puede ser mayor que 5")
        Double rating
) {
}
