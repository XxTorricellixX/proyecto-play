package com.about_software.proyecto_play.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "proyecto_play_peliculas")
@Data
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length = 150, unique = true)
    private String titulo;

    @Column(nullable=false, precision = 3)
    private Integer duracion;

    @Column(nullable=false, length = 40)
    private String genero;

    @Column(name="fecha_estreno")
    private LocalDate fechaEstreno;

    @Column(precision = 3, scale= 2)
    private BigDecimal clasificacion;

    @Column(nullable=false, length = 1)
    private String estado;
}
