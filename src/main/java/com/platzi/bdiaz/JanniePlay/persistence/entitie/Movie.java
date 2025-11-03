package com.platzi.bdiaz.JanniePlay.persistence.entitie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "peliculas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150, unique = true)
    private String titulo;
    @Column(nullable = false, precision = 3) //3 caracteres máximo
    private Integer duracion;
    @Column(nullable = false, length = 40)
    private String genero;
    @Column(precision = 3, scale = 2) //3 caracteres y max 2 decimales
    private BigDecimal calificacion;
    @Column(name = "fecha_estreno")
    private LocalDate fechaEstreno;
    @Column(name = "estado", nullable = false, length = 1)
    private String contentStatus;

}
