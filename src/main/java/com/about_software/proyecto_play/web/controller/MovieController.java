package com.about_software.proyecto_play.web.controller;

import com.about_software.proyecto_play.domain.dto.MovieDto;
import com.about_software.proyecto_play.domain.dto.SuggestResquestDto;
import com.about_software.proyecto_play.domain.dto.UpdateMovieDto;
import com.about_software.proyecto_play.domain.services.MovieService;
import com.about_software.proyecto_play.domain.services.ProyectoPlayAiService;
import com.about_software.proyecto_play.persistence.crud.CrudMovieEntity;
import com.about_software.proyecto_play.persistence.entity.MovieEntity;
import dev.langchain4j.service.spring.AiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name="Movies",description = "Operations about movies of ProyectoPlay")
public class MovieController {

    private final MovieService movieService;
    private final ProyectoPlayAiService aiService;

    public MovieController(MovieService movieService, ProyectoPlayAiService aiService) {
        this.movieService = movieService;
        this.aiService = aiService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll(){
        return ResponseEntity.ok(this.movieService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener una pelicula por su identificador",
            description= "Retorna la pelicula que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pelicula encontrada"),
                    @ApiResponse(responseCode = "404", description = "Pelicula No encontrada" , content = @Content)
            }
    )
    public ResponseEntity<MovieDto> getById(@PathVariable @Parameter(description = "Identificador de la pelicula a recuperar ") long id){
        MovieDto movieDto = this.movieService.getById(id);
        if(movieDto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping
    public ResponseEntity<MovieDto> add(@RequestBody MovieDto movieDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.add(movieDto));
    }

    @PostMapping("/suggest")
    public ResponseEntity<String> generateMoviesSuggestion(@RequestBody SuggestResquestDto suggestResquestDto){
        return ResponseEntity.ok(this.aiService.generateMoviesSuggestion(suggestResquestDto.userReferences()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable long id, @RequestBody @Valid UpdateMovieDto updateMovieDto){
        return ResponseEntity.ok(this.movieService.update(id,updateMovieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        this.movieService.delete(id);
        return ResponseEntity.ok().build();
    }


}
