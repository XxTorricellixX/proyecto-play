package com.about_software.proyecto_play.persistence;

import com.about_software.proyecto_play.domain.dto.MovieDto;
import com.about_software.proyecto_play.domain.dto.UpdateMovieDto;
import com.about_software.proyecto_play.domain.exception.MovieAlreadyExistsException;
import com.about_software.proyecto_play.domain.repository.MovieRepository;
import com.about_software.proyecto_play.persistence.crud.CrudMovieEntity;
import com.about_software.proyecto_play.persistence.entity.MovieEntity;
import com.about_software.proyecto_play.persistence.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class MovieEntityRepository implements MovieRepository {

    private final CrudMovieEntity crudMovieEntity;
    private final MovieMapper movieMapper;

    public MovieEntityRepository(CrudMovieEntity crudMovieEntity, MovieMapper movieMapper) {
        this.crudMovieEntity = crudMovieEntity;
        this.movieMapper = movieMapper;
    }


    @Override
    public List<MovieDto> getAll() {
        return this.movieMapper.toDto(this.crudMovieEntity.findAll());
    }

    @Override
    public MovieDto getById(long id) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        return this.movieMapper.toDto(movieEntity);
    }

    @Override
    public MovieDto save(MovieDto movieDto) {
        if(this.crudMovieEntity.findFirstByTitulo(movieDto.title())!=null){
            throw new MovieAlreadyExistsException(movieDto.title());

        }
        MovieEntity movieEntity = this.movieMapper.toEntity(movieDto);
        movieEntity.setEstado("D");
        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public MovieDto update(long id,UpdateMovieDto updateMovieDto) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        if(movieEntity==null) return null;
        movieMapper.updateEntityFromDto(updateMovieDto,movieEntity);
        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));

    }

    @Override
    public void delete(long id) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        if(movieEntity==null) return ;
        this.crudMovieEntity.delete(movieEntity);
    }

}