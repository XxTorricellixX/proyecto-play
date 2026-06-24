package com.about_software.proyecto_play.persistence.crud;

import com.about_software.proyecto_play.persistence.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudMovieEntity extends CrudRepository <MovieEntity, Long> {
    MovieEntity findFirstByTitulo(String titulo);
}
