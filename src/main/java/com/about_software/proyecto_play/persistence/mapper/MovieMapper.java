package com.about_software.proyecto_play.persistence.mapper;

import com.about_software.proyecto_play.domain.dto.MovieDto;
import com.about_software.proyecto_play.domain.dto.UpdateMovieDto;

import com.about_software.proyecto_play.persistence.entity.MovieEntity;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses= {GenreMapper.class})
public interface MovieMapper {

    @Mapping(source = "titulo", target="title")
    @Mapping(source = "duracion", target="duration")
    @Mapping(source = "genero", target="genre", qualifiedByName="stringToGenre")
    @Mapping(source = "fechaEstreno", target="releaseDate")
    @Mapping(source = "clasificacion", target="rating")
    MovieDto toDto(MovieEntity entity);

    List<MovieDto> toDto(Iterable<MovieEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "genre", target="genero", qualifiedByName="genreToString")
    MovieEntity toEntity(MovieDto dto);

    @Mapping(source = "title", target="titulo" , ignore = true)
    @Mapping(source = "releaseDate", target="fechaEstreno" , ignore = true)
    @Mapping(source = "rating", target="clasificacion" , ignore = true)
    void updateEntityFromDto (UpdateMovieDto updateMovieDto, @MappingTarget  MovieEntity entity);
}
