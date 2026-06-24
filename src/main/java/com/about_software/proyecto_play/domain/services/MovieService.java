package com.about_software.proyecto_play.domain.services;

import com.about_software.proyecto_play.domain.dto.MovieDto;
import com.about_software.proyecto_play.domain.dto.UpdateMovieDto;
import com.about_software.proyecto_play.domain.repository.MovieRepository;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository  movieRespository;

    public MovieService(MovieRepository movieRespository) {
        this.movieRespository = movieRespository;
    }

    @Tool("Busca todas las peliculas que existan dentro de la plataforma")
    public List<MovieDto> getAll(){
        return this.movieRespository.getAll();
    }

    public MovieDto getById(long id){
        return this.movieRespository.getById(id);
    }

    public MovieDto add(MovieDto movieDto){
        return this.movieRespository.save(movieDto);
    }

    public MovieDto update(long id,UpdateMovieDto updateMovieDto){
        return this.movieRespository.update(id,updateMovieDto);
    }

    public void delete(long id){
        this.movieRespository.delete(id);
    }

}
