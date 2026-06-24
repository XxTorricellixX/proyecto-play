package com.about_software.proyecto_play.domain.repository;

import com.about_software.proyecto_play.domain.dto.MovieDto;
import com.about_software.proyecto_play.domain.dto.UpdateMovieDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {
    List<MovieDto> getAll();
    MovieDto getById(long id);
    MovieDto save(MovieDto movieDto);
    MovieDto update(long id,UpdateMovieDto updateMovieDto);
    void delete(long id);

}
