package com.platzi.bdiaz.JanniePlay.persistence.crud;

import com.platzi.bdiaz.JanniePlay.persistence.entitie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CrudMovieEntity extends JpaRepository<Movie, Long> {

    Boolean existsByTitulo(String title);

    @Modifying
    @Query("""
           UPDATE Movie m
           SET m.contentStatus = 'N'
           WHERE m.id = :id
           """)
    void deactivateStatusMovie(@Param("id") Long id);
}
